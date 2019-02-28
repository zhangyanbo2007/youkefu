var title = "UCKeFu-全渠道客服系统" ;
var tipagent = '' , tipagenttitle = '', tipagenticon = '' ;
var socket  , newuser = [] , newmessage = [] , ring = [];
newuser['mp3'] = '/images/new.mp3'; 
newmessage['mp3'] = '/images/message.mp3';
ring['mp3'] = '/images/ring.mp3';
$(document).ready(function(){
    socket = io.connect(schema+'://'+hostname+':'+port+'/im/agent?orgi='+orgi+"&userid="+userid+"&session="+session+"&admin="+adminuser );
    socket.on('connect',function() {
		console.log("连接初始化成功");
		//请求服务端记录 当前用户在线事件
    }).on('disconnect',function() {
		console.log("连接已断开");       
		//请求服务端记录，当前用户离线
    });
	
    socket.on('chatevent', function(data) {
		console.log(data.messageType + " .....  message:"+data.message);          
    }).on('task', function(data) {
		
    }).on('new', function(data) {
    	if($('#multiMediaDialogWin').length > 0 && multiMediaDialogWin.$ &&multiMediaDialogWin.$('#agentusers').length > 0){
    		multiMediaDialogWin.Proxy.newAgentUserService(data);
    	}else{
    		//来电弹屏
    		$('#agentdesktop').attr('data-href' , '/agent/index.html?userid='+data.userid).click();
    	}
    	WebIM.audioplayer('audioplane', newuser, false); // 播放
    }).on('status', function(data) {
    	if(orgi == data.orgi){
    		$('#agents_status').html("服务人数："+data.users+"人，排队数："+data.inquene+"人，在线坐席："+data.agents+"人，坐席忙："+data.busy+"人");	        	
    	}
    }).on('message', function(data) {
    	if($('#multiMediaDialogWin').length > 0 && multiMediaDialogWin != null && multiMediaDialogWin.$ && multiMediaDialogWin.$('#agentusers').length > 0){
    		multiMediaDialogWin.Proxy.newAgentUserMessage(data);
    		if(data.type == 'message'){
    			if(tipagent && tipagent == 'true'){
    				WebIM.showNotice(tipagenttitle , data.message , tipagenticon);
    			}
        		WebIM.audioplayer('audioplane', newmessage, false); // 播放
        	}
    	}else{
    		//来电弹屏
    		$('#agentdesktop').attr('data-href' , '/agent/index.html?userid='+data.userid).click();
    	}
    }).on('workorder', function(data) {
        
    }).on('end', function(data) {
    	if($('#multiMediaDialogWin').length > 0){
    		if(multiMediaDialogWin.document.getElementById('agentusers') != null){
    			multiMediaDialogWin.Proxy.endAgentUserService(data);
    		}
    	}else{
    		//来电弹屏
    		$('#agentdesktop').attr('data-href', '/agent/index.html?userid='+data.userid).click();
    	}
    });	
	/****每分钟执行一次，与服务器交互，保持会话****/
	setInterval(function(){
		WebIM.ping();	
	} , 30000);				
}) ;

var WebIM = {
	sendMessage:function(message , userid , appid , session , orgi , touser , agentstatus){
		WebIM.sendTypeMessage(message, userid, appid, session, orgi, touser, agentstatus, null , null) ;
	},
	sendTypeMessage:function(message , userid , appid , session , orgi , touser , agentstatus , msgtype , attachmentid){
		socket.emit('message', {
			appid : appid ,
			userid:userid,
			sign:session,
			touser:touser,
			session: session ,
			orgi:orgi,
			username:agentstatus,
			nickname:agentstatus,
			message : message,
			msgtype:msgtype,
			attachmentid:attachmentid
        });
	},
	showNotice:function (title , content , icon) {   
	    Notification.requestPermission(function (perm) {  
	        if (perm == "granted") {  
	            var notification = new Notification(title, {  
	                dir: "auto",  
	                lang: "zh",  
	                tag: "UCKeFu", 
	                icon:icon,
	                body: content
	            });  
	            setTimeout(function(){
	            	notification.close();
	            },5000);    
	        }  
	    });  
	}, 
	ping : function(){
		loadURL("/message/ping.html",null,function(data){
			if(data!=''  && data.indexOf('login') >=0){
				window.location.href = "/login.html" ;
			}
		}) ;	
	},
	audioplayer:function(id, file, loop) {
	    var audioplayer = document.getElementById(id);
	    if (audioplayer != null) {
	        document.body.removeChild(audioplayer);
	    }

	    if (typeof(file) != 'undefined') {
	        if (navigator.userAgent.indexOf("MSIE") > 0) { // IE 
	            var player = document.createElement('bgsound');
	            player.id = id;
	            player.src = file['mp3'];
	            player.setAttribute('autostart', 'true');
	            if (loop) {
	                player.setAttribute('loop', 'infinite');
	            }
	            document.body.appendChild(player);

	        } else { // Other FF Chome Safari Opera 
	            var player = document.createElement('audio');
	            player.id = id;
	            player.setAttribute('autoplay', 'autoplay');
	            if (loop) {
	                player.setAttribute('loop', 'loop');
	            }
	            document.body.appendChild(player);

	            var mp3 = document.createElement('source');
	            mp3.src = file['mp3'];
	            mp3.type = 'audio/mpeg';
	            player.appendChild(mp3);
	        }
	    }
	},
	audiostop:function(id){
		var player = document.getElementById(id);
		if(player!=null){
			player.pause();
			player.currentTime = 0;
			if($('#'+id).length > 0){
				$('#'+id).remove();
			}
		}
	}
}



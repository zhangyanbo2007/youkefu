var recorder = null , recduration = 0 ;
var UCKeFuVoice = {
	init:function(platform){
		var timeout ;
		if(platform == "mobile"){
			$(document).on("touchstart" , '#above', function() {
				timeout = setTimeout(function() {
					if(true){
						var height = $('#bottom').height();
						var width =  $('#bottom').width();
						if($('#uckefu-voice-record').length == 0){
							$("#bottom").append("<div class='uckefu-voice-record uckefu-voice-mobile' id='uckefu-voice-record'><span><i class='iconfont'>&#xe709;</i></span> <span class='title' id='uckefu-rec-title'>00:00:00</span></div>");
						}
						UCKeFuVoice.start();
					}else{
						output('<span id="connect-message">发送语音消息需要启用HTTS</span>' , 'message connect-message' , false);
					}
				}, 1000);
				
			});
			 
			$(document).on("touchend" , '#above' , function() {
				if(true){
				    clearTimeout(timeout);
				    if($('#uckefu-voice-record').length != 0){
				    	$('#uckefu-voice-record').remove();
				    	UCKeFuVoice.stop(upload);
			    	}
				}
			});
		}else{
			$(document).on("mousedown" , '#above', function() {
				timeout = setTimeout(function() {
					if(true){
						var height = $('#bottom').height();
						var width =  $('#bottom').width();
						if($('#uckefu-voice-record').length == 0){
							$("#bottom").append("<div class='uckefu-voice-record' id='uckefu-voice-record'><div class='title' id='uckefu-rec-title'>00:00:00</div><div class='ukefu-mic'><i class='iconfont'>&#xe709;</i></div></div>");
						}
						UCKeFuVoice.start();
					}else{
						output('<span id="connect-message">发送语音消息需要启用HTTS</span>' , 'message connect-message' , false);
					}
				}, 1000);
			});
			 
			$(document).on("mouseup" , '#above' , function() {
				if(true){
				    clearTimeout(timeout);
				    if($('#uckefu-voice-record').length != 0){
				    	$('#uckefu-voice-record').remove();
				    	UCKeFuVoice.stop(upload);
			    	}
				}
			});
		}
	},
	start:function(){
		if(recorder == null){
			recorder = new MP3Recorder({
				debug:false,
				funOk: function () {
					recorder.start();
				},
				funCancel: function (msg) {
					recorder = null;
				},
				onProcess:function(duration , powerlevel){
					recduration = duration ;
					if($("#uckefu-rec-title").length > 0){
						var time = parseInt(duration/1000);
						var hour = parseInt(time/3600) ;
						var min = parseInt(time%3600 / 60) ;
						var sec = time%60 ;
						$("#uckefu-rec-title").text((hour>9?hour : "0"+ hour) + ":"+ (min>9?min:"0"+min) + ":" + (sec>9?sec:"0"+sec));
					}
				}
			});
		}else{
			recorder.start();	
		}
	},
	stop:function(url){
		if(recorder!=null){
			recorder.stop();
	        recorder.getMp3Blob(function (blob) {
	        	var fd = new FormData();
	            var mp3Name = encodeURIComponent('audio_recording_' + new Date().getTime() + '.mp3');
	            fd.append('mp3Name', mp3Name);
	            fd.append('voice', blob);
	            fd.append('duration', recduration);
	            
	            $.ajax({
	                url: url,
	                type: 'POST',
	                processData: false,
	                contentType: false,
	                data: fd,
	                success: function () {
	                	output('<span id="connect-message">语音已经发送</span>' , 'connect-message' , false);
	                	recorder = null ;
	                }
	            });
	        });
		}
	}
}
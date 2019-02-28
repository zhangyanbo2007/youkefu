layui.use(['layer', 'form'], function(){
	var layer = layui.layer, form = layui.form;
});
$(document).ready(function(){
	var initHeight = $("#init-height").val();
	if(initHeight > 0){
		$("#canvas").height(parseInt(initHeight) + 200);
	}
	var initWidth = $("#init-width").val();
	if(initWidth > 0){
		$("#canvas").width(parseInt(initWidth) + 200);
	}
	//获取所有的group-container
	var groups = [];
	
	$(".group-container-ques").each(function(i,val){
		groups.push($(this).attr("id"));
	});
	  jsPlumb.ready(function () {
		  var isinit = false;
		    var j = window.j = jsPlumb.getInstance({Endpoint: ["Dot", {radius: 2}],
		        Connector:"StateMachine",
		        HoverPaintStyle: {stroke: "#32c24d", strokeWidth: 2 },
		        ConnectionOverlays: [
		            [ "Arrow", {
		                location: 1,
		                id: "arrow",
		                length: 14,
		                foldback: 0.8
		            } ]
		        ],
		        Container: "canvas"});

		    j.draggable("canvas");
		    
		    var connectorPaintStyle = {
		            strokeWidth: 2,
		            stroke: "#32c24d",
		            joinstyle: "round",
		            outlineStroke: "white",
		            outlineWidth: 2
		        },
		    // .. and this is the hover style.
		        connectorHoverStyle = {
		            strokeWidth: 2,
		            stroke: "#32c24d",
		            outlineWidth: 2,
		            outlineStroke: "white"
		        },
		        endpointHoverStyle = {
		            fill: "#32c24d",
		            stroke: "#32c24d"
		        },
		        sourceEndpoint = {
		                endpoint: "Dot",
		                paintStyle: {
		                    stroke: "#32c24d",
		                    fill: "transparent",
		                    radius: 2,
		                    strokeWidth: 1
		                },
		                isSource: true,
		                connector: [ "StateMachine", { stub: [40, 60], gap: 10, cornerRadius: 5, alwaysRespectStubs: true } ],
		                connectorStyle: connectorPaintStyle,
		                hoverPaintStyle: endpointHoverStyle,
		                connectorHoverStyle: connectorHoverStyle,
		                dragOptions: {},
		                overlays: [
		                    [ "Label", {
		                        location: [0.5, 0.5],
		                        label: "Drag",
		                        cssClass: "endpointSourceLabel",
		                        visible:false
		                    } ]
		                ]
		            },
		            targetEndpoint = {
		                endpoint: "Dot",
		                paintStyle: { fill: "#32c24d", radius: 7 },
		                hoverPaintStyle: endpointHoverStyle,
		                maxConnections: -1,
		                dropOptions: { hoverClass: "hover", activeClass: "active" },
		                isTarget: true,
		                overlays: [
		                    [ "Label", { location: [0.5, -0.5], label: "Drop", cssClass: "endpointTargetLabel", visible:false } ]
		                ]
		            };
		    var sourceAnchors = [
		        [ 0, 1, 0, 1 ],
		        [ 0.25, 1, 0, 1 ],
		        [ 0.5, 1, 0, 1 ],
		        [ 0.75, 1, 0, 1 ],
		        [ 1, 1, 0, 1 ]
		    ];
		    
		    var connections = [];
		    groups.forEach(function( val, index ) {
		    	//渲染组（问题）
		    	j.addGroup({
			        el:eval(val),
			        id:val,
			        dropOverride:true,
			        endpoint:[ [ "Dot", { radius:11 } ], [ "Dot", { radius:11 } ] ],
			        dragOptions:{ 
			        	stop:function(e) { 
			        		//移动更新位置
				        		if(e.el){
				        			//更新位置
						              $.ajax({
							             type: "get",
							             url: "/apps/salespatter/question/position",
							             data: {quesid:e.el.id.substr(1,e.el.id.length),offsety:e.el.offsetLeft,offsetx:e.el.offsetTop},
							             dataType: "json",
							             success: function(data){
						                     }
							         });
				        		}
				            } 
					    	,
					        drag:function(e){
					        	var boxheight = $("#canvas").height();
					        	var boxwidth = $("#canvas").width();
					        	var elmheight = e.el.offsetTop;
					        	var elmwidth = e.el.offsetLeft;
					        	if(boxheight < (elmheight + 200)){
					        		$("#canvas").height(elmheight + 200);
					        		 $(".console-box").animate(
					        			        {scrollTop:elmheight + 200},0 /*scroll实现定位滚动*/
					        			        );
					        	}
					        	if(boxwidth < (elmwidth + 200)){
					        		$("#canvas").width(elmwidth + 200);
					        		 $(".console-box").animate(
					        			        {scrollLeft:elmwidth + 200},0 /*scroll实现定位滚动*/
					        			        );
					        	}
					        }
				        } 
			    });
			    j.makeTarget(eval(val), {
			        dropOptions: { hoverClass: "hover" },
			        /*anchor:"Continuous",*/
			        anchor:"Top",
			        endpoint:[ "Dot", { radius: 2, cssClass:"large-green" } ]
			    });
		    	//渲染底下元素（答案）
		    	var groupson = $("#"+val+" .groupson");
		    	$.each(groupson, function(i2, val2) {
		    		var nextId = $(val2).attr("next-id");
		    		var conn = {};
		    		if(nextId){
		    			conn.source = val2.id;
		    			conn.target = nextId;
		    			connections.push(conn);
		    		}
		    		j.addToGroup(val,eval(val2.id));
		    		j.makeSource(eval(val2.id), {
				        filter:"a",
				        filterExclude:true,
				        maxConnections: 1,
				        endpoint:[ "Dot", { radius: 1, cssClass:"small-blue" } ],
				        anchor:sourceAnchors
				    });
		    	});  
		    });
		    //监听连接线事件
		    j.bind("connection", function(p) {
		    	if(false/*$(p.source).attr("parent-id") == $(p.target).attr("id")*/){
		    		//j.deleteConnection(p.connection);
		    	}else{
		    		p.connection.bind("click", function() {
		    			var obj = this;
		    			top.layer.confirm("确认删除该连接吗？", {icon: 3, title:'提示'}, function(index){
		        			top.layer.close(index);
		        			if(confirm){
		        				j.deleteConnection(obj);
		  			          //删除答案连接
		  			              $.ajax({
		  				             type: "get",
		  				             url: "/apps/salespatter/question/answer/edit",
		  				             data: {answerid:obj.sourceId.substr(1),queid:''},
		  				             dataType: "json",
		  				             success: function(data){
		  			                     }
		  				         });
		        			}else{
		        				
		        			}
		        		});
			            
			        });
		    		//绑定跳转下一个问题
		    		if(p.source && p.targetId && isinit){
	        			//更新@Valid String answerid, @Valid String queid
			              $.ajax({
				             type: "get",
				             url: "/apps/salespatter/question/answer/edit",
				             data: {answerid:p.sourceId.substr(1),queid:p.targetId.substr(1)},
				             dataType: "json",
				             success: function(data){
			                     }
				         });
	        		}
		    	}
		    });
		    
		    //初始化连接线
		    connections.forEach(function( val, index ) {
		    	j.connect({
		    		  source:val.source,
		    		  target:val.target
		    		})
		    });
		    isinit = true;
		    
		    // 删除节点
		    j.on(canvas, "click", ".del", function() {
		    	var g = this.parentNode.getAttribute("id");
        		top.layer.confirm("确认删除该节点吗？", {icon: 3, title:'提示'}, function(index){
        			top.layer.close(index);
        			if(confirm){
        		        j.removeGroup(g, true);
        		        $.ajax({
				             type: "get",
				             url: "/apps/salespatter/question/deleteajax",
				             data: {quesid:g.substr(1)},
				             dataType: "json",
				             success: function(data){
			                     }
				         });
        			}else{
        				
        			}
        		});
		    });
		    
		    jsPlumb.fire("jsPlumbDemoLoaded", j);
	});
});

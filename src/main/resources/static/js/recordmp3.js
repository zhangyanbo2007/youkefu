(function (exports) {

    var MP3Recorder = function (config) {

        var recorder = this;
        config = config || {};
        config.sampleRate = config.sampleRate || 16000;
        config.bitRate = config.bitRate || 16;
		var recsize = 0 ;
        navigator.getUserMedia = navigator.getUserMedia ||
                                 navigator.webkitGetUserMedia ||
                                 navigator.mozGetUserMedia ||
                                 navigator.msGetUserMedia;

        if (navigator.getUserMedia) {
            navigator.getUserMedia({
                audio: true
            },
            function (stream) {
                var context = new AudioContext(),
                    microphone = context.createMediaStreamSource(stream),
                    processor = context.createScriptProcessor(16384, 1, 1),//bufferSize大小，输入channel数，输出channel数
                    mp3ReceiveSuccess, currentErrorCallback;

                config.sampleRate = context.sampleRate;
                processor.onaudioprocess = function (event) {
                    //边录音边转换
                    var array = event.inputBuffer.getChannelData(0);
					var size=array.length;
					recsize += size;
					var power=0;
					for(var j=0;j<size;j++){//floatTo16BitPCM 
						var s=Math.max(-1,Math.min(1,array[j]*4));//PCM 音量直接放大8倍，失真还能接受
						s=s<0?s*0x8000:s*0x7FFF;
						power += Math.abs(s);
					};
                    realTimeWorker.postMessage({ cmd: 'encode', buf: array });
					
					
					
					power/=size;
					var powerLevel=0;
					if(power>0){
						//https://blog.csdn.net/jody1989/article/details/73480259
						powerLevel=Math.round(Math.max(0,(20*Math.log10(power/0x7fff)+34)*100/34));
					};
					var duration=Math.round(recsize/config.sampleRate*1000);
					config.onProcess(duration , powerLevel);
					
                };

                var realTimeWorker = new Worker('/js/worker-realtime.js');
                realTimeWorker.onmessage = function (e) {
                    switch (e.data.cmd) {
                        case 'init':
                            log('初始化成功');
                            if (config.funOk) {
                                config.funOk();
                            }
                            break;
                        case 'end':
                            log('MP3大小：', e.data.buf.length);
                            if (mp3ReceiveSuccess) {
                                mp3ReceiveSuccess(new Blob(e.data.buf, { type: 'audio/mp3' }));
                            }
                            break;
                        case 'error':
                            log('错误信息：' + e.data.error);
                            if (currentErrorCallback) {
                                currentErrorCallback(e.data.error);
                            }
                            break;
                        default:
                            log('未知信息：', e.data);
                    }
                };

                recorder.getMp3Blob = function (onSuccess, onError) {
                    currentErrorCallback = onError;
                    mp3ReceiveSuccess = onSuccess;
                    realTimeWorker.postMessage({ cmd: 'finish' });
                };

                recorder.start = function () {
                    if (processor && microphone) {
                        microphone.connect(processor);
                        processor.connect(context.destination);
                        log('开始录音');
                    }
                }

                recorder.stop = function () {
                    if (processor && microphone) {
                        microphone.disconnect();
                        processor.disconnect();
                        log('录音结束');
                    }
                }

                realTimeWorker.postMessage({
                    cmd: 'init',
                    config: {
                        sampleRate: config.sampleRate,
                        bitRate: config.bitRate
                    }
                });
            },
            function (error) {
                var msg;
                switch (error.code || error.name) {
                    case 'PERMISSION_DENIED':
                    case 'PermissionDeniedError':
                        msg = '用户拒绝访问麦客风';
                        break;
                    case 'NOT_SUPPORTED_ERROR':
                    case 'NotSupportedError':
                        msg = '浏览器不支持麦客风';
                        break;
                    case 'MANDATORY_UNSATISFIED_ERROR':
                    case 'MandatoryUnsatisfiedError':
                        msg = '找不到麦客风设备';
                        break;
                    default:
                        msg = '无法打开麦克风，异常信息:' + (error.code || error.name);
                        break;
                }
                if (config.funCancel) {
                    config.funCancel(msg);
                }
            });
        } else {
            if (config.funCancel) {
                config.funCancel('当前浏览器不支持录音功能');
            }
        }

        function log(str) {
            if (config.debug) {
                console.log(str);
            }
        }
    }

    exports.MP3Recorder = MP3Recorder;
})(window);
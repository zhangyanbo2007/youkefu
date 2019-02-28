package com.ukefu.webim.web.model;

public class VoiceResult implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String voicetype;
	private String tts ;
	private String voice ;
	
	public VoiceResult(String voicetype , String tts , String voice) {
		this.voicetype = voicetype ;
		this.tts = tts; 
		this.voice = voice ;
	}
	
	public String getVoicetype() {
		return voicetype;
	}
	public void setVoicetype(String voicetype) {
		this.voicetype = voicetype;
	}
	public String getTts() {
		return tts;
	}
	public void setTts(String tts) {
		this.tts = tts;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	
}

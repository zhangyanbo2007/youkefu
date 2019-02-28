package com.ukefu.webim.web.model;

public class UploadStatus {
	private String error ;
	private String url ;
	private String message ;
	
	public UploadStatus(String error , String url){
		this.error = error ;
		this.url = url ;
	}
	
	public UploadStatus(String message){
		this.error = "1" ;
		this.message = message ;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

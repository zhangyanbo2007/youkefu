package com.ukefu.webim.web.model;

public interface AiCallOutProcess extends java.io.Serializable{
	public String getId();
	public String getWelword();
	public String getWelvoice();
	public String getWeltype();
	public String getQuestionid();
	
	public String getEndword();
	public String getEndvoice() ;
	public String getEndtype();
	
	public String getScore();
}

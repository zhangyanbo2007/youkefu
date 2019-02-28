package com.ukefu.webim.web.model;

public class FormFilterRequest {
	private String[] field;
	private String[] cond;
	private String[] value;
	private String[] comp ;	
	
	private String[] hanguptype ;
	private String[] hangupmsg ;
	private String[] hangupvoice ;
	
	public String[] getField() {
		return field;
	}

	public void setField(String[] field) {
		this.field = field;
	}

	public String[] getValue() {
		return value;
	}

	public void setValue(String[] value) {
		this.value = value;
	}

	public String[] getCond() {
		return cond;
	}

	public void setCond(String[] cond) {
		this.cond = cond;
	}

	public String[] getComp() {
		return comp;
	}

	public void setComp(String[] comp) {
		this.comp = comp;
	}

	public String[] getHanguptype() {
		return hanguptype;
	}

	public void setHanguptype(String[] hanguptype) {
		this.hanguptype = hanguptype;
	}

	public String[] getHangupmsg() {
		return hangupmsg;
	}

	public void setHangupmsg(String[] hangupmsg) {
		this.hangupmsg = hangupmsg;
	}

	public String[] getHangupvoice() {
		return hangupvoice;
	}

	public void setHangupvoice(String[] hangupvoice) {
		this.hangupvoice = hangupvoice;
	}
	
}

package com.ukefu.webim.util;

public class RestResult implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 783408637220961119L;
	private RestResultType status ;
	
	public RestResult(RestResultType status , Object data){
		this.status = status ;
		this.data = data ;
	}
	
	public RestResult(RestResultType status){
		this.status = status ;
	}
	
	public Object data ;

	public RestResultType getStatus() {
		return status;
	}

	public void setStatus(RestResultType status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}

package com.ukefu.core;

/**
 * 记录集群状态信息
 * @author iceworld
 *
 */
public class ClusterContext implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ClusterContext instance = new ClusterContext();
	private boolean master = false ;
	private String host ;
	private int port ;
	private String id ;
	private long start ;
	
	
	public static ClusterContext getInstance() {
		return instance ;
	}
	
	public void setMaster(boolean master) {
		this.master = master ;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isMaster() {
		return master;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}
}

package com.ukefu.util.bi.model;

public class FirstTitle implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7568308944311251560L;
	private String name;
	private int depth = 0;
	private String rename ;
	private String level ;
	private String description;//标题描述
	public FirstTitle(){}
	public FirstTitle(String name , int depth , boolean measure){
		this.name = name ;
		this.depth = depth ;
	}
	public FirstTitle(String name ){
		this.name = name ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String toString(){
		return this.name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRename() {
		return rename;
	}
	public void setRename(String rename) {
		this.rename = rename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

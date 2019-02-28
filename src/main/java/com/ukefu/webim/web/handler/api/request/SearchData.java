package com.ukefu.webim.web.handler.api.request;

import java.util.List;

import org.springframework.data.domain.Page;

public class SearchData<T> {
	private List<T> topicList ;
	private long totalPages ;
	private long totalElements ;
	private long size ;
	private int numberOfElements ;
	private int number ;
	private long notprocess ;
	
	
	public SearchData(Page<T> page){
		this.topicList = page.getContent() ;
		this.totalPages = page.getTotalPages();
		this.totalElements = page.getTotalElements();
		this.size = page.getSize();
		this.number= page.getNumber();
		this.numberOfElements = page.getNumberOfElements();
	}


	public List<T> getTopicList() {
		return topicList;
	}


	public void setTopicList(List<T> topicList) {
		this.topicList = topicList;
	}


	public long getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}


	public long getTotalElements() {
		return totalElements;
	}


	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}


	public long getSize() {
		return size;
	}


	public void setSize(long size) {
		this.size = size;
	}


	public int getNumberOfElements() {
		return numberOfElements;
	}


	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public long getNotprocess() {
		return notprocess;
	}


	public void setNotprocess(long notprocess) {
		this.notprocess = notprocess;
	}
	
	
}

package com.ukefu.webim.web.model;

public class QcFormFilterRequest {

	private String[] itemid;
	private String[] itemscore;
	private String[] itemremarks;
	private String[] tabooid ;	
	private boolean[] tabooscore ;
	private String[] tabooremarks ;
	private String remarks ;//质检备注
	private String adcom ;//优点评语
	private String qacom ;//QA评语
	private String imcom ;//改进评语
	private int totalscore ;//总分
	
	public String[] getItemid() {
		return itemid;
	}
	public void setItemid(String[] itemid) {
		this.itemid = itemid;
	}
	public String[] getItemscore() {
		return itemscore;
	}
	public void setItemscore(String[] itemscore) {
		this.itemscore = itemscore;
	}
	public String[] getItemremarks() {
		return itemremarks;
	}
	public void setItemremarks(String[] itemremarks) {
		this.itemremarks = itemremarks;
	}
	public String[] getTabooid() {
		return tabooid;
	}
	public void setTabooid(String[] tabooid) {
		this.tabooid = tabooid;
	}
	public boolean[] getTabooscore() {
		return tabooscore;
	}
	public void setTabooscore(boolean[] tabooscore) {
		this.tabooscore = tabooscore;
	}
	public String[] getTabooremarks() {
		return tabooremarks;
	}
	public void setTabooremarks(String[] tabooremarks) {
		this.tabooremarks = tabooremarks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAdcom() {
		return adcom;
	}
	public void setAdcom(String adcom) {
		this.adcom = adcom;
	}
	public String getQacom() {
		return qacom;
	}
	public void setQacom(String qacom) {
		this.qacom = qacom;
	}
	public String getImcom() {
		return imcom;
	}
	public void setImcom(String imcom) {
		this.imcom = imcom;
	}
	public int getTotalscore() {
		return totalscore;
	}
	public void setTotalscore(int totalscore) {
		this.totalscore = totalscore;
	}
	
	
}

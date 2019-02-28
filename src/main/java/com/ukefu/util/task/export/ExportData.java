package com.ukefu.util.task.export;

import java.util.List;

import com.ukefu.webim.web.model.TableProperties;

public class ExportData {
	private TableProperties tp ;
	private int maxcols ;
	private List<String> values ;
	
	public ExportData(TableProperties tp , List<String> values) {
		this.tp = tp ;
		this.values = values ;
	}
	
	public TableProperties getTp() {
		return tp;
	}
	public void setTp(TableProperties tp) {
		this.tp = tp;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}

	public int getMaxcols() {
		return maxcols;
	}

	public void setMaxcols(int maxcols) {
		this.maxcols = maxcols;
	}
}

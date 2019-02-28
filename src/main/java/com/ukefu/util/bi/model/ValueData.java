package com.ukefu.util.bi.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ukefu.util.UKTools;
import com.ukefu.webim.web.model.ColumnProperties;

public class ValueData implements java.io.Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4732877738059927547L;
	private String name ;
	private Object value ;
	private ValueData mergevalue ;
	private int rowspan =1;
	private int colspan =1;
	private boolean merge = false ;
	private String foramatValue ;
	private String formatStr ;
	private Level row = null;
	private Level col = null ;
	private String valueType ;
	private boolean canbedrill ;
	private String cellmergeid ;
	private String drillthroughsql ;
	private Object tempValue ;
	private String url ;			//钻取的时候保存的URL，在ReportDataFactor中设置
	private String target ;			//钻取的时候保存的target，在ReportDataFactor中设置
	private String vtclass ;		//指标数据类型 对应的  html style class
	private String valueStyle;		//预警一块的格式化样式，背景色，字体什么的
	
	private String style ;
	public ValueData(Object value , String foramatValue, String valueType , boolean canbedrill , String sql , String name , String formatStr , List<ColumnProperties> cols){
		this.foramatValue = foramatValue ;
		this.value = value ;
		if(!StringUtils.isBlank(foramatValue) && foramatValue.toLowerCase().indexOf("nan") >= 0) {
			this.foramatValue = "" ;
			this.value = null ;
		}
		this.valueType = valueType ;
		this.drillthroughsql = sql ;
		this.canbedrill = canbedrill ;
		this.name = name ;
		this.formatStr = formatStr ;
		if(cols!=null) {
			for(ColumnProperties col : cols) {
				if(col.getDataname().equals(name)) {
					this.name = col.getTitle();
				}
			}
		}
	}
	public ValueData(String name , Object value, String foramatValue , String valueType){
		this.name = name;
		this.value = value ; 
		this.valueType = valueType ;
		this.foramatValue = foramatValue ;
	}
	
	public ValueData(String name , Object value, String foramatValue , String valueType , String coltype){
		this.name = name;
		this.value = value ; 
		this.valueType = valueType ;
		this.foramatValue = foramatValue ;
	}
	
	public Object getValue() {
		return this.mergevalue!=null && this.mergevalue!=this ? mergevalue.getValue() : value;
	}


	public void setValue(Object value) {
		this.value = value;
	}


	public String getForamatValue() {
		return foramatValue;
	}


	public void setForamatValue(String foramatValue) {
		this.foramatValue = foramatValue;
	}

	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public String getDrillthroughsql() {
		return drillthroughsql;
	}
	public void setDrillthroughsql(String drillthroughsql) {
		this.drillthroughsql = drillthroughsql;
	}
	public String toString(){
		return this.foramatValue ;
	}

	public boolean isCanbedrill() {
		return canbedrill;
	}

	public void setCanbedrill(boolean canbedrill) {
		this.canbedrill = canbedrill;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Level getRow() {
		return row;
	}
	public void setRow(Level row) {
		this.row = row;
	}
	public Level getCol() {
		return col;
	}
	public void setCol(Level col) {
		this.col = col;
	}
	public Level getRowData(String title){
		Level level = this.row ;
		if(level!=null && level.getDimname()!=null){
			while(!level.getDimname().equals(title)){
				level = level.getParent();
				if(level!=null && level.getParent()==null){
					level = null ;
					break ;
				}
			}
		}
		return level;
	}
	public int getRowspan() {
		return rowspan == 0 ? 1 : rowspan;
	}
	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}
	public int getColspan() {
		return colspan == 0 ? 1 : colspan;
	}
	public void setColspan(int colspan) {
		this.colspan = colspan;
	}
	public boolean isMerge() {
		return merge;
	}
	public void setMerge(boolean merge) {
		this.merge = merge;
	}
	public String getCellmergeid() {
		return cellmergeid;
	}
	public void setCellmergeid(String cellmergeid) {
		this.cellmergeid = cellmergeid;
	}
	public String getFormatStr() {
		return formatStr;
	}
	public void setFormatStr(String formatStr) {
		this.formatStr = formatStr;
	}
	public Object getTempValue() {
		return tempValue;
	}
	public void setTempValue(Object tempValue) {
		this.tempValue = tempValue;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getVtclass() {
		return vtclass;
	}
	public void setVtclass(String vtclass) {
		this.vtclass = vtclass;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	/**
	 * 页面控制CSS使用 ，在 table.html
	 * 
	 * @return
	 */
	public String getDataid(){
		return UKTools.md5(this.name!=null ? this.name : "") ;
	}
	public String getValueStyle() {
		return valueStyle;
	}
	public void setValueStyle(String valueStyle) {
		this.valueStyle = valueStyle;
	}
	public ValueData getMergevalue() {
		return mergevalue;
	}
	public void setMergevalue(ValueData mergevalue) {
		this.mergevalue = mergevalue;
	}
}

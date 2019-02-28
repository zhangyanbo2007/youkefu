package com.ukefu.util.mobile;

import org.apache.commons.lang.StringUtils;

public class MobileAddress {
	private String id ;			//ID
	private String code ;		//号段编码
	private String country ;	//国家
	private String province ;	//省份
	private String city ;		//城市
	private String isp ;		//运营商
	
	private String areacode ;	//区号
	private String zipcode ;	//邮编
	
	public MobileAddress(String code , String areacode , String province , String city , String isp){
		this.code = code ;
		if(!StringUtils.isBlank(areacode)){
			if(areacode.startsWith("0")){
				this.areacode = areacode ;
			}else{
				this.areacode = "0"+areacode ;
			}
		}
		this.province = province ;
		this.city = city ;
		this.isp = isp ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}

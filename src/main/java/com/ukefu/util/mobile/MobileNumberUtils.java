package com.ukefu.util.mobile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class MobileNumberUtils {
	
	private static Map<String , MobileAddress> mobileAddressMap  = new HashMap<String ,MobileAddress>();
	
	public static int init() throws IOException{
		File file = new File( MobileNumberUtils.class.getResource("/config/mobile.data").getFile());
		if(file.exists()){
			FileInputStream reader = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(reader , "UTF-8");
			BufferedReader bf = new BufferedReader(isr);
			try{
				String data = null ;
				while((data = bf.readLine()) != null){
					String[] group = data.split("[\t ]") ;
					MobileAddress address = null ;
					if(group.length == 5){
						address = new MobileAddress(group[0], group[1], group[2], group[3],group[4]) ;
					}else if(group.length == 4){
						address = new MobileAddress(group[0], group[1], group[2], group[2],group[3]) ;
					}
					if(address!=null){
						if(mobileAddressMap.get(address.getCode()) == null){
							mobileAddressMap.put(address.getCode(), address) ;
						}
						if(mobileAddressMap.get(address.getAreacode()) == null){
							mobileAddressMap.put(address.getAreacode(), address) ;
						}
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				bf.close();
				isr.close();
				reader.close();
			}
		}
		return mobileAddressMap.size() ;
	}
	/**
	 * 根据呼入号码 找到对应 城市 , 需要传入的号码是 手机号 或者 固话号码，位数为 11位
	 * @param phonenumber
	 * @return
	 */
	public static MobileAddress getAddress(String phonenumber){
		String code = "";
		if(!StringUtils.isBlank(phonenumber) && phonenumber.length() > 10){
			if(phonenumber.startsWith("0")){
				code = phonenumber.substring(0 ,  4) ;
				if(mobileAddressMap.get(code) == null) {
					code = phonenumber.substring(0 ,  3) ;
				}
			}else if(phonenumber.startsWith("1")){
				code = phonenumber.substring(0 , 7) ;
			}
		}
		return mobileAddressMap.get(code) ;
	}
}

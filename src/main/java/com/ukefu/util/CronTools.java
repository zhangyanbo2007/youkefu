package com.ukefu.util;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronExpression;


public class CronTools {
	/**
	 * 
	 * @param crontabExp
	 * @return
	 * @throws ParseException
	 */
	
	public static CronExpression getFireTime(String crontabExp) throws ParseException{
		return new CronExpression(crontabExp);
		
	}
	/**
	 * 
	 * @param crontabExp
	 * @return
	 * @throws ParseException
	 */
	
	public static Date getFinalFireTime(String crontabExp , Date date) throws ParseException{
		CronExpression expression = new CronExpression(crontabExp) ;
		return expression.getNextValidTimeAfter(date!=null ? date:new Date());
		
	}
	public static void main(String[] args) throws Exception{
		
		System.out.println(UKTools.dateFormate.format(CronTools.getFinalFireTime("0 0/40 0/1 * * ?",new Date())));
	}
}

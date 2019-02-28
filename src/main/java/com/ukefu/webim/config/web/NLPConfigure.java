package com.ukefu.webim.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.ukefu.util.ai.DicSegment;

@Component
public class NLPConfigure {
	
	@Bean(name="segment")   
    public DicSegment getDicSegment() {   
		return new DicSegment();   
    } 
}

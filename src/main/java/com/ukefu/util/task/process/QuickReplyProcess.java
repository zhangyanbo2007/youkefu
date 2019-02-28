package com.ukefu.util.task.process;

import com.ukefu.webim.service.es.QuickReplyRepository;
import com.ukefu.webim.web.model.QuickReply;

public class QuickReplyProcess implements JPAProcess{
	
	private QuickReplyRepository quickReplyRes ;
	
	public QuickReplyProcess(QuickReplyRepository quickReplyRes){
		this.quickReplyRes = quickReplyRes ;
	}

	@Override
	public void process(Object data) {
		quickReplyRes.save((QuickReply)data) ;
	}

	@Override
	public void end() {
		
	}

}

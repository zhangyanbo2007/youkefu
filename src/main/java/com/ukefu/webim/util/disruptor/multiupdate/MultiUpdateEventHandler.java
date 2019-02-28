package com.ukefu.webim.util.disruptor.multiupdate;

import com.lmax.disruptor.EventHandler;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.event.MultiUpdateEvent;
import com.ukefu.util.event.UserDataEvent;
import com.ukefu.util.task.ESData;

@SuppressWarnings("rawtypes")
public class MultiUpdateEventHandler implements EventHandler<UserDataEvent>{

	@Override
	public void onEvent(UserDataEvent event, long arg1, boolean arg2)
			throws Exception {
		if(event.getEvent()!=null){
			MultiUpdateEvent multiEventEvent = (MultiUpdateEvent)event.getEvent() ;
			if(multiEventEvent.getData() instanceof ESData){
				//只存储到 ES，不存储到数据库。目前只有名单数据 
			}else{
				if(UKDataContext.MultiUpdateType.SAVE.toString().equals(multiEventEvent.getEventype())){
					multiEventEvent.getCrudRes().delete(multiEventEvent.getData()) ;
					multiEventEvent.getCrudRes().save(multiEventEvent.getData()) ;
				}else if(UKDataContext.MultiUpdateType.DELETE.toString().equals(multiEventEvent.getEventype())){
					multiEventEvent.getCrudRes().delete(multiEventEvent.getData()) ;
				}
			}
		}
	}

}

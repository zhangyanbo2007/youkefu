package com.ukefu.webim.util.disruptor.multiupdate;

import com.lmax.disruptor.EventFactory;
import com.ukefu.util.event.UserDataEvent;

public class MultiUpdateEventFactory implements EventFactory<UserDataEvent>{

	@Override
	public UserDataEvent newInstance() {
		return new UserDataEvent();
	}
}

package com.ukefu.webim.util.disruptor;

import com.lmax.disruptor.EventFactory;
import com.ukefu.util.event.UserDataEvent;

public class UserDataEventFactory implements EventFactory<UserDataEvent>{

	@Override
	public UserDataEvent newInstance() {
		return new UserDataEvent();
	}
}

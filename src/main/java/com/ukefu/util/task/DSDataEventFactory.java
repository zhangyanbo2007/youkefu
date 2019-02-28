package com.ukefu.util.task;

import com.lmax.disruptor.EventFactory;

public class DSDataEventFactory implements EventFactory<DSDataEvent>
{
    public DSDataEvent newInstance()
    {
        return new DSDataEvent();
    }
}

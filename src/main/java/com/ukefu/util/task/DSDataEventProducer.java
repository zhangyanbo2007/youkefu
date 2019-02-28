package com.ukefu.util.task;

import com.lmax.disruptor.RingBuffer;

public class DSDataEventProducer
{
    private final RingBuffer<DSDataEvent> ringBuffer;

    public DSDataEventProducer(RingBuffer<DSDataEvent> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

    public void onData(DSData dsData)
    {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try
        {
        	DSDataEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
        	event.setDSData(dsData) ;
        }finally{
            ringBuffer.publish(sequence);
        }
    }
}
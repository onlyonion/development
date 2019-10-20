package com.onion.test.common.distruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {

    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Event:  " + event.getValue() + " sequence:+" + sequence + "  endOfBatch:" + endOfBatch);
    }

}

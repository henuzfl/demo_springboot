package com.zfl.demo.domain.processor;

import com.google.common.eventbus.Subscribe;
import com.zfl.demo.common.event.SampleEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SampleEventProcessor1 {


    @Subscribe
    public void dealWithEvent(SampleEvent event) {
        log.info("SampleEventProcessor1 received event: {}", event);
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("SampleEventProcessor1 dealWithEvent: {}", event);
    }
}

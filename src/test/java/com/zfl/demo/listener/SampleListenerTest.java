package com.zfl.demo.listener;

import com.zfl.demo.DbRollbackBase;
import com.zfl.demo.DemoApplication;
import com.zfl.demo.common.event.SampleEvent;
import com.zfl.demo.inboud.listener.SampleListener;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleListenerTest extends DbRollbackBase {

    @Autowired
    private SampleListener sampleListener;

    @Test
    public void testPostSampleEvent() {
        for (int i = 0; i < 10; i++) {
            SampleEvent event = new SampleEvent(randomLengthString(5), randomLengthString(15));
            sampleListener.postSampleEvent(event);
        }
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String randomLengthString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ((int) (Math.random() * 26) + 97));
        }
        return sb.toString();
    }
}

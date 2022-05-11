package com.zfl.demo.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zfl
 * @Date: 2020/10/27 14:01
 * @Version: 1.0.0
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService executorService() {
        return new ThreadPoolExecutor(2,
                4,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(128));
    }
}

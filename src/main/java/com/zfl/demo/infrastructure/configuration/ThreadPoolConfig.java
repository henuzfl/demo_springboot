package com.zfl.demo.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


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

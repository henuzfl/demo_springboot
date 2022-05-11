package com.zfl.demo.common.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SampleEvent {

    private String head;
    private String content;
    private LocalDateTime createdAt;

    public SampleEvent(String head, String content) {
        this.head = head;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "SampleEvent [head=" + head + ", content=" + content + ", createdAt=" + createdAt + "]";
    }
}

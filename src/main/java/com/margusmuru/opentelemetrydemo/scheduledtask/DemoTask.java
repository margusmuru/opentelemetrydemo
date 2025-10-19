package com.margusmuru.opentelemetrydemo.scheduledtask;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
public class DemoTask implements Runnable {
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                log.info("Demo task is running, {}", UUID.randomUUID());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Demo task interrupted", e);
                break;
            }
        }
    }
}

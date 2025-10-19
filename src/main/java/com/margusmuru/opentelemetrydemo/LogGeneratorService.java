package com.margusmuru.opentelemetrydemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class LogGeneratorService implements CommandLineRunner {
    private final TaskExecutor taskExecutor;

    public LogGeneratorService(@Qualifier("MainThreadConfig")
                               TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void run(String... args) throws Exception {
        DemoTask task = new DemoTask();
        // taskExecutor.execute(task);
    }
}

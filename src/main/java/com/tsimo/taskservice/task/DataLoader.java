package com.tsimo.taskservice.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final TaskRepository repo;

    public DataLoader(TaskRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        repo.save(new Task("Call booking"));
        repo.save(new Task("Make sets of clothes"));
        repo.save(new Task("25 days left"));
    }
}

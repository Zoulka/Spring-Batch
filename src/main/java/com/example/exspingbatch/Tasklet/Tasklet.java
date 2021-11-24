package com.example.exspingbatch.Tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;

public class Tasklet implements org.springframework.batch.core.step.tasklet.Tasklet {

    public final String name;

    public Tasklet(String name) {
        this.name = name;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
            throws Exception {
        System.out.println("Hello ma chere mere " + name);
        return RepeatStatus.FINISHED;
    }
}

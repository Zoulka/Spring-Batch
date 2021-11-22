package com.example.exospringbatch;

import Tasklet.MaTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class ExoSpringBatchApplication {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Step MataskletStep(){
        return stepBuilderFactory.get("step")
                .tasklet(new MaTasklet())
                .build();
    }
    @Bean
    public Job MataskletJob(){
        return jobBuilderFactory.get("job")
                .start(MataskletStep())
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(ExoSpringBatchApplication.class, args);
    }

}

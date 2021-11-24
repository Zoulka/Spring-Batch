package com.example.coursudemysrpingbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class CoursUdemySrpingBatchApplication {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    // on va creer notre premier Step
    @Bean
    public Step HelloWordStep(){
        return stepBuilderFactory.get("step")
                .tasklet(helloWordTasklet(null))
                .build();
    }
    @Bean
    @StepScope
    public HelloWordTasklet helloWordTasklet(@Value("#{jobParameters['name']}") final String name){
        return new HelloWordTasklet(name);
    }

    @Bean
    public Job HelloWordJob(){
        return jobBuilderFactory.get("job")
                .start(HelloWordStep())
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(CoursUdemySrpingBatchApplication.class, args);
    }

}

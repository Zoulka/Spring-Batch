package com.example.exspingbatch;

import com.example.exspingbatch.Tasklet.Tasklet;
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
public class ExSpingBatchApplication {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Step factureStep(){
        return stepBuilderFactory.get("step")
                .tasklet( tasklet(null))
                .build();
    }
    @Bean
    @StepScope
    public Tasklet tasklet (@Value("#{JobParameters ['name']}") final String name){
        return new Tasklet(name);
    }


    @Bean
    public Job factureJob(){
        return jobBuilderFactory.get("job")
                .start(factureStep())
                .build();
    }
    public static void main(String[] args) {
        SpringApplication.run(ExSpingBatchApplication.class, args);
    }

}

package com.cjw.server.job;

import com.cjw.server.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SampleJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SalesRepository salesRepository;

    @Bean
    public Job SampleJob(){
        return jobBuilderFactory.get("sampleJob")
                .start(sampleStep1())
                .build();
    }

    @Bean
    public Step sampleStep1() {
        return stepBuilderFactory.get("sampleStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("Sample Step1....");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}

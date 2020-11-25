package com.cjw.server.job;

import com.cjw.server.model.SalesModel;
import com.cjw.server.processor.SalesProcessor;
import com.cjw.server.writer.SalesWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
@Slf4j
public class SampleJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Job SampleJob() throws Exception {
        return jobBuilderFactory.get("sampleJob")
                .preventRestart()
                .start(sampleStep1())
                .build();
    }

    @Bean
    public Step sampleStep1() throws Exception {
        return stepBuilderFactory.get("sampleStep1")
                .<SalesModel, SalesModel>chunk(100)
                .reader(reader())
                .processor(process())
                .writer(writer())
                .build();

    }

    @Bean
    public MongoItemReader<SalesModel> reader() throws Exception {
        log.debug("sampleStep1.... reader()");
        MongoItemReader<SalesModel> mongoItemReader =
                new MongoItemReader<>();
        mongoItemReader.setTemplate(mongoTemplate);
        mongoItemReader.setCollection("sales");
        mongoItemReader.setTargetType(SalesModel.class);
        mongoItemReader.setPageSize(10);
        mongoItemReader.setQuery(new Query());
        log.info("reader Info::{}",mongoItemReader.read());
        return mongoItemReader;
    }

    @Bean
    public SalesProcessor process() {
        log.info("process!");
        return new SalesProcessor();
    }

    @Bean
    public SalesWriter writer(){
        log.info("Writer!");
        return new SalesWriter();
    }
}

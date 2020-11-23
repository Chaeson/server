package com.cjw.server.job;

import com.cjw.server.model.SalesModel;
import com.cjw.server.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SampleJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Bean
    public Job SampleJob() throws Exception {
        return jobBuilderFactory.get("sampleJob")
                .start(sampleStep1())
                .build();
    }

    @Bean
    public Step sampleStep1() throws Exception {
        return stepBuilderFactory.get("sampleStep1")
                .<SalesModel, SalesModel>chunk(1)
                .reader(reader())
                .writer(writer())
                .build();

    }

    private MongoItemWriter<SalesModel> writer() {
        log.info("Writer!");
        MongoItemWriter<SalesModel> mongoItemWriter = new MongoItemWriter<>();
        mongoItemWriter.setTemplate(mongoTemplate);
        mongoItemWriter.setCollection("sales");
        return mongoItemWriter;
    }

    public MongoItemReader<SalesModel> reader() throws Exception {
        log.debug("sampleStep1.... reader()");
        MongoItemReader<SalesModel> mongoItemReader =
                new MongoItemReader<>();
        mongoItemReader.setTemplate(mongoTemplate);
        mongoItemReader.setCollection("sales");
        mongoItemReader.setTargetType(SalesModel.class);
        mongoItemReader.setPageSize(10);
        mongoItemReader.setQuery(new Query());
        log.info("reader Info::{}",mongoItemReader.toString());
        return mongoItemReader;
    }


}

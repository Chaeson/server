package com.cjw.server.reader;

import com.cjw.server.model.SalesModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.*;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class SalesReader implements ItemReader<SalesModel> {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public SalesModel read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}

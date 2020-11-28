package com.cjw.server.writer;

import com.cjw.server.model.SalesModel;
import com.cjw.server.repository.SalesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

@Slf4j
public class SalesWriter implements ItemWriter<SalesModel> {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public void write(List<? extends SalesModel> list) throws Exception {
        for(int i=0; i<list.size(); i++){
            log.info("list::{}",list.get(i));
            salesRepository.save(list.get(i));
        }
    }
}

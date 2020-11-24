package com.cjw.server.processor;

import com.cjw.server.model.SalesModel;
import com.cjw.server.repository.SalesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Slf4j
public class SalesProcessor implements ItemProcessor<SalesModel, SalesModel> {

    @Autowired
    public SalesRepository salesRepository;

    @Override
    public SalesModel process(final SalesModel salesModel) throws Exception {
        List<SalesModel> salesList = salesRepository.findAll();
        for(SalesModel model : salesList){
            log.info("model:{}",model.toString());
            if(model.getStoreLocation().equalsIgnoreCase("san diego")) {
                model.setStoreLocation(model.getStoreLocation().toUpperCase());
                log.info("process Model::{}",model.toString());
            }
            return model;
        }
        return null;
    }
}

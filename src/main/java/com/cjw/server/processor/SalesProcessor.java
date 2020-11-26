package com.cjw.server.processor;

import com.cjw.server.model.SalesModel;
import com.cjw.server.repository.SalesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@EnableBatchProcessing
@Transactional
public class SalesProcessor implements ItemProcessor<SalesModel, SalesModel> {

    @Autowired
    public SalesRepository salesRepository;

    @Override
    public SalesModel process(final SalesModel salesModel) throws Exception {
        SalesModel processedSalesModel = new SalesModel();
        if(salesModel.getStoreLocation().equalsIgnoreCase("san diego")){
            processedSalesModel = salesModel;
            processedSalesModel.setStoreLocation(salesModel.getStoreLocation().toUpperCase());
        }
        return processedSalesModel;
    }

}

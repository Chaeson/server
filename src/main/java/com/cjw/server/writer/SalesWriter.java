package com.cjw.server.writer;

import com.cjw.server.model.SalesModel;
import com.cjw.server.repository.SalesRepository;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class SalesWriter implements ItemWriter<SalesModel> {

    @Autowired
    private SalesRepository salesRepository;

    @Override
    public void write(List<? extends SalesModel> list) throws Exception {
        salesRepository.saveAll(list);
    }
}

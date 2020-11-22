package com.cjw.server.service;

import com.cjw.server.model.SalesModel;
import com.cjw.server.repository.SalesRepository;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public JSONObject getFindAll(){
        JSONObject jsonObject=new JSONObject();
        System.out.println(salesRepository.findAll());
        return jsonObject;
    }
}

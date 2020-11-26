package com.cjw.server.repository;

import com.cjw.server.model.SalesModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface SalesRepository extends MongoRepository<SalesModel, String> {
}

package com.cjw.server.repository;

import com.cjw.server.model.SalesModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalesRepository extends MongoRepository<SalesModel, String> {
}

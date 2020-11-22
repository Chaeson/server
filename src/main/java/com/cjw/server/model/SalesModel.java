package com.cjw.server.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document("sales")
public class SalesModel {
    @Id
    private String _id;

    private Date saleDate;

    private Object items;

    private String storeLocation;

    private String[] customer;

    private String couponUsed;

    private String purchaseMethod;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}

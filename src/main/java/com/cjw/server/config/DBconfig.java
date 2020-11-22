package com.cjw.server.config;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import net.minidev.json.JSONObject;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DBconfig {

    static Logger logger = LoggerFactory.getLogger(DBconfig.class);

    public static void main(String []args){
        // MongoDB Connection Info.
        // MongoDB Atlas 설정한 정보로 데이터를 세팅한다.
        ConnectionString connString = new ConnectionString(
                "mongodb+srv://chae:4879@cluster0.aolss.mongodb.net/sample_supplies?retryWrites=true&w=majority"
        );

        // MongoDB API 참조값
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // Cluster 에서 세팅한 DataBase 명
        // 없는 경우 샘플 데이터를 만들 수 있다.
        // RDBMS 의 스키마와 비슷한 개념으로 보임...
        MongoDatabase database = mongoClient.getDatabase("sample_supplies");

        // Collection 명을 설정
        // RDBMS의 테이블? 과 비슷한 개념으로 보인다.
        logger.info("MongoDB Name:{}",database.getCollection("sales"));

        logger.info("MongoDB Count:{}",database.getCollection("sales").countDocuments());
        logger.info("MongoDB Find:{}",database.getCollection("sales").find().first());

        // MongoDB Insert
        // Document 세팅
        Map<String, Object> testInsert = new HashMap<String, Object>();
        testInsert.put("TestInt",1);
        testInsert.put("double", 2.0);
        testInsert.put("testString","String");
        testInsert.put("testDate",new Date());
        JSONObject json = new JSONObject();
        json.put("testKey","key");

        // Document Insert
        Document document = new Document(testInsert);

        // Collection Insert
        // database.getCollection("TestCol").insertOne(document);

        logger.info("MongoDB Insert after count:{}",database.getCollection("sales").countDocuments());

        // MongoDB Select
        ClientSession clientSession = mongoClient.startSession();
        MongoCollection collection =  database.getCollection("sales");

        // Select All in a Row
        collection.find().forEach(document1 -> {
            logger.info("MongoDB Select Collection All:{}",document1);
        });

        // 찾을 조건을 선택한다.
        // Document 의 TestInt 키가 1인 데이터를 찾는다.
        FindIterable<Document> iterable = collection.find( new Document("storeLocation", "San Diego"));

        // TestInt가 1인 데이터의 로우
        logger.info("MongoDB Find Result:{}",iterable.first());
        // TestInt의 데이터가 1인 데이터의 시간 키값.
        logger.info("MongoDB Find Result:{}",iterable.first().get("storeLocation"));

    }

}

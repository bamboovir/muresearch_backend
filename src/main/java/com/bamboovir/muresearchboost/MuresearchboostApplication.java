package com.bamboovir.muresearchboost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableWebFlux
@EnableAsync
@EnableElasticsearchRepositories(basePackages = "com.bamboovir.muresearchboost.app.elasticsearch")
@EnableMongoRepositories(basePackages = "com.bamboovir.muresearchboost.app.persistence")
public class MuresearchboostApplication {
    public static void main(String[] args) {
        SpringApplication.run(MuresearchboostApplication.class, args);
    }
}


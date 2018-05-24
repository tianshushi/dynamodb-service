/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import net.vicp.sealedbook.dynamodb.util.DynamoDBClientFactory;
import net.vicp.sealedbook.dynamodb.util.DynamoDBLocalClientFactory;

/**
 * @author shitianshu on 2018/5/5 上午5:02.
 */
@SpringBootApplication
@ServletComponentScan
public class Application {

    @Bean
    public DynamoDBClientFactory initDynamoDBClientFactory() {
        return new DynamoDBLocalClientFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

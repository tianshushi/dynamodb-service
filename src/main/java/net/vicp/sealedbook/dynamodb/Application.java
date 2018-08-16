/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import net.vicp.sealedbook.dynamodb.util.DynamoDBClientFactory;

/**
 * @author shitianshu on 2018/5/5 上午5:02.
 */
@SpringBootApplication
@ServletComponentScan
public class Application {

    @Value("${dynamoDB.client.factory}")
    private String dynamoDBClientFactory;

    @Bean
    public DynamoDBClientFactory initDynamoDBClientFactory() throws Exception {
        return (DynamoDBClientFactory) Class.forName(dynamoDBClientFactory).newInstance();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

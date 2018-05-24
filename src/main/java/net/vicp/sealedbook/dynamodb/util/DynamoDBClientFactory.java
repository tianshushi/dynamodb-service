/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

/**
 * AWS DynamoDB客户端工厂
 *
 * @author shitianshu on 2018/4/22 上午2:59.
 */
public abstract class DynamoDBClientFactory {

    /** 日志 */
    private static final Logger LOG = LoggerFactory.getLogger(DynamoDBClientFactory.class);

    /** AWS DynamoDB Client定义 */
    protected static AmazonDynamoDB client = null;

    protected static DynamoDB dynamoDB = null;

    /**
     * 获得AWS DynamoDB Client
     *
     * @return
     */
    public static AmazonDynamoDB fetchAmazonDynamoDBClient() {
        return client;
    }

    public static DynamoDB fetchDynamoDB() {
        return dynamoDB;
    }

    /**
     * 针对通过配置信息生成的AWS DynamoDB Client 进行一次table describe操作.
     */
    protected static void verifyDynamoDBClient() {
        LOG.info("verify {} DynamoDB client", dynamoDB);
    }
}

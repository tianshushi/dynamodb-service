/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

/**
 * 客户端初始化连接DynamoDB本地服务，
 *
 * @author shitianshu on 2018/4/22 上午3:01.
 */
public class DynamoDBLocalClientFactory extends DynamoDBClientFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DynamoDBLocalClientFactory.class);

    private static final String IP_PORT = "http://172.20.7.2:8000";

    /** DynamoDB错误重试次数 */
    private static final int ERROR_RETRY_TIME = 3;

    private static final String EMPTY_STRING = "";

    static {

        BasicAWSCredentials credentials = new BasicAWSCredentials(EMPTY_STRING, EMPTY_STRING);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setMaxErrorRetry(ERROR_RETRY_TIME);

        client = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(IP_PORT, "us-west-2"))
            .withClientConfiguration(clientConfiguration)
            .build();

        LOG.info("DynamoDB Local client init. client:[{}]", client);
        dynamoDB = new DynamoDB(client);
        LOG.info("DynamoDB Local init. dynamoDB:[{}]", dynamoDB);

        /** 测试生成的DynamoDB客户端有效性 */
        verifyDynamoDBClient();
    }

}

/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

/**
 * @author shitianshu on 2018/5/4 下午6:49.
 */
public class DynamoDBAWSClientFactory extends DynamoDBClientFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DynamoDBAWSClientFactory.class);

    /** 访问密钥 */
    private static String accessKey = "";

    /** 私有密钥 */
    private static String secretKey = "";

    /** DynamoDB错误重试次数 */
    private static final int ERROR_RETRY_TIME = 3;

    static {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setMaxErrorRetry(ERROR_RETRY_TIME);

        client = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withClientConfiguration(clientConfiguration)
            .withRegion(Regions.CN_NORTH_1)
            .build();


        LOG.info("DynamoDB AWS client init. client:[{}]", client);
        dynamoDB = new DynamoDB(client);
        LOG.info("DynamoDB AWS init. dynamoDB:[{}]", dynamoDB);
        /** 测试生成的DynamoDB客户端有效性 */
        verifyDynamoDBClient();
    }

}

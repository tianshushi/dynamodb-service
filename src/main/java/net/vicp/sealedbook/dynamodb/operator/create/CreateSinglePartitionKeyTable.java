/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.operator.create;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import net.vicp.sealedbook.dynamodb.util.DynamoDBLocalClientFactory;

/**
 * 使用API创建一张仅由分区键为主键的表
 *
 * @author shitianshu on 2018/4/22 下午1:00.
 */
public class CreateSinglePartitionKeyTable {

    private static final String TABLE_NAME = "TEST_TABLE_SINGLE_PARTITION_KEY";

    private static final Logger LOG = LoggerFactory.getLogger(CreateSinglePartitionKeyTable.class);

    public static void main(String[] args) {
        DynamoDB dynamoDB = DynamoDBLocalClientFactory.fetchDynamoDB();
        Table table = dynamoDB.createTable(
            TABLE_NAME
            , Arrays.asList(new KeySchemaElement("id", KeyType.HASH))
            , Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S))
            , new ProvisionedThroughput(10L, 10L)
        );

        try {
            table.waitForActive();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOG.info("table create finish.[{}]", table.getDescription());
    }

}

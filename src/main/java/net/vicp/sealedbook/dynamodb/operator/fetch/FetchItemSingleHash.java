/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.operator.fetch;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import net.vicp.sealedbook.dynamodb.util.DynamoDBLocalClientFactory;

/**
 * @author shitianshu on 2018/4/25 上午8:02.
 */
public class FetchItemSingleHash {

    private static final Logger LOG = LoggerFactory.getLogger(FetchItemSingleHash.class);

    private static final String TABLE_NAME = "TEST_TABLE_SINGLE_PARTITION_KEY";

    public static void main(String[] args) {
        DynamoDB dynamoDB = DynamoDBLocalClientFactory.fetchDynamoDB();

        /** 获得指定表对象 */
        Table table = dynamoDB.getTable(TABLE_NAME);

        Item item = table.getItem("id", "5e551197-5522-4af7-8754-ee08aa8ff3f8");

        LOG.info("fetch item {}", item);

        Object home = item.get("home");
        assert null == home;

        Object mapObject = item.get("map");
        Map<String, Object> map = item.getMap("map");
        assert map == mapObject;
        LOG.info("fetch map {}", map);

        Object ageObject = item.get("age");
        int age = item.getInt("age");
        assert ageObject instanceof Number;
        assert ((BigDecimal) ageObject).intValue() == age;
        LOG.info("fetch age {}", age);

        Object listObject = item.get("list");
        List<Object> list = item.getList("list");
        assert listObject instanceof List;
        assert listObject == list;
        LOG.info("fetch list {}", list);

    }
}

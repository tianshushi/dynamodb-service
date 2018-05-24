/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.operator.save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import net.vicp.sealedbook.dynamodb.util.DynamoDBLocalClientFactory;

/**
 * 使用putItem接口像表写入数据.
 *
 * @author shitianshu on 2018/4/24 下午8:55.
 */
public class PutItemSingleHashKey {

    private static final Logger LOG = LoggerFactory.getLogger(PutItemSingleHashKey.class);

    private static final String TABLE_NAME = "TEST_TABLE_SINGLE_PARTITION_KEY";

    public static void main(String[] args) {
        DynamoDB dynamoDB = DynamoDBLocalClientFactory.fetchDynamoDB();

        /** 获得指定表对象 */
        Table table = dynamoDB.getTable(TABLE_NAME);

        /** 因为要写入项目,所以要new一个项目出来 */
        Item item = new Item();
        /** 指定主键key,如果主键由复合主键组成，可以通过参数写入排序键 */
        item.withPrimaryKey("id", UUID.randomUUID().toString());
        /** 向表中attr_name_string字段写入string值 */
        item.withString("attr_name_string", "string attr value");
        /** 向表中age写入int值 */
        item.withInt("age", 85);
        /** 向表中写入Map集合 */
        Map map = new HashMap();
        map.put("map_key_1", "map_value_1");
        map.put("map_key_2", "map_value_2");
        item.withMap("map", map);
        /** 向表中写入List集合 */
        List<String> list = new ArrayList<String>();
        list.add("list_value1");
        list.add("list_value2");
        list.add("list_value3");
        item.withList("list", list);
        /** 向表中home写入null */
        item.withNull("home");

        LOG.info("put item {}", item);
        table.putItem(item);

    }

}

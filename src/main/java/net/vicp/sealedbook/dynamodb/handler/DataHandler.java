/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;

import net.vicp.sealedbook.dynamodb.entity.TableDescribeResponse;
import net.vicp.sealedbook.dynamodb.util.DynamoDBLocalClientFactory;

/**
 * 数据操作
 *
 * @author shitianshu on 2018/5/23 下午7:22.
 */
@Service
public class DataHandler {

    @Resource
    private TableHandler tableHandler;

    /**
     * 单条数据查询.
     *
     * @param tableName 表名
     * @param hashValue 分区键值
     * @return
     */
    public Map<String, Object> queryOne(String tableName, Object hashValue) {
        DynamoDB dynamoDB = DynamoDBLocalClientFactory.fetchDynamoDB();
        Table table = dynamoDB.getTable(tableName);

        TableDescribeResponse tableDescribeResponse = tableHandler.fetchTableDescribe(tableName);
        String hashKeyName = tableDescribeResponse.getHashKey();
        Item item = table.getItem(hashKeyName, hashValue);
        if (null == item) {
            return null;
        }
        return item.asMap();
    }

    /**
     * 单条数据查询
     *
     * @param tableName 表名
     * @param hashValue 分区键值
     * @param sortValue 排序键值
     * @return
     */
    public Map<String, Object> queryOne(String tableName, Object hashValue, Object sortValue) {
        DynamoDB dynamoDB = DynamoDBLocalClientFactory.fetchDynamoDB();
        Table table = dynamoDB.getTable(tableName);

        TableDescribeResponse tableDescribeResponse = tableHandler.fetchTableDescribe(tableName);
        String hashKeyName = tableDescribeResponse.getHashKey();
        String sortKeyName = tableDescribeResponse.getSortKey();
        Item item = table.getItem(hashKeyName, hashValue, sortKeyName, sortValue);
        if (null == item) {
            return null;
        }
        return item.asMap();
    }

    /**
     * 修改数据
     *
     * @param tableName 表名
     * @param hashValue 分区键值
     * @param modifyData 修改的数据
     */
    public void modify(String tableName, Object hashValue, Map<String, Object> modifyData) {
        DynamoDB dynamoDB = DynamoDBLocalClientFactory.fetchDynamoDB();
        Table table = dynamoDB.getTable(tableName);

        TableDescribeResponse tableDescribeResponse = tableHandler.fetchTableDescribe(tableName);
        String hashKeyName = tableDescribeResponse.getHashKey();

        UpdateItemSpec updateItemSpec = new UpdateItemSpec();
        PrimaryKey primaryKey = new PrimaryKey();
        primaryKey.addComponent(hashKeyName, hashValue);
        updateItemSpec.withPrimaryKey(primaryKey);

        for (Map.Entry<String, Object> entry : modifyData.entrySet()) {
            AttributeUpdate attributeUpdate = new AttributeUpdate(entry.getKey());
            attributeUpdate.put(entry.getValue());
            updateItemSpec.withAttributeUpdate(attributeUpdate);
        }
        table.updateItem(updateItemSpec);
    }

    /**
     * 删除数据
     *
     * @param tableName 表名
     * @param hashValue 分区键值
     */
    public void delete(String tableName, Object hashValue) {
        DynamoDB dynamoDB = DynamoDBLocalClientFactory.fetchDynamoDB();
        Table table = dynamoDB.getTable(tableName);

        TableDescribeResponse tableDescribeResponse = tableHandler.fetchTableDescribe(tableName);
        String hashKeyName = tableDescribeResponse.getHashKey();

        PrimaryKey primaryKey = new PrimaryKey();
        primaryKey.addComponent(hashKeyName, hashValue);
        table.deleteItem(primaryKey);
    }

}

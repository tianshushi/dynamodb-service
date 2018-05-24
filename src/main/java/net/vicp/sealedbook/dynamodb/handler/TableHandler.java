/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.AttributeUpdate;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Page;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

import net.vicp.sealedbook.dynamodb.entity.TableCreateRequest;
import net.vicp.sealedbook.dynamodb.entity.TableDescribeResponse;
import net.vicp.sealedbook.dynamodb.util.Convertor;
import net.vicp.sealedbook.dynamodb.util.DynamoDBLocalClientFactory;

/**
 * @author shitianshu on 2018/5/5 上午5:06.
 */
@Service
public class TableHandler {

    /**
     * 获得表的详细信息
     *
     * @param tableName 表名
     * @return
     */
    public TableDescribeResponse fetchTableDescribe(String tableName) {
        DynamoDB dynamoDB = DynamoDBLocalClientFactory.fetchDynamoDB();
        Table table = dynamoDB.getTable(tableName);
        TableDescription describe = table.describe();
        TableDescribeResponse tableDescribeResponse = Convertor.convert(describe);
        return tableDescribeResponse;
    }

    /**
     * 获得库中所有的表名
     * @return
     */
    public List<String> fetchTableNames() {
        DynamoDB dynamoDB = DynamoDBLocalClientFactory.fetchDynamoDB();
        TableCollection<ListTablesResult> tables = dynamoDB.listTables();
        Iterator<Page<Table, ListTablesResult>> iterator = tables.pages().iterator();

        List<String> tablesNameCollection = new ArrayList<String>();
        while (iterator.hasNext()) {
            Page<Table, ListTablesResult> next = iterator.next();
            ListTablesResult lowLevelResult = next.getLowLevelResult();
            List<String> tableNames = lowLevelResult.getTableNames();
            tablesNameCollection.addAll(tableNames);
        }
        return tablesNameCollection;
    }

    public void createTable(TableCreateRequest tableCreateRequest) {

        DynamoDB dynamoDB = DynamoDBLocalClientFactory.fetchDynamoDB();

        CreateTableRequest createTableRequest = new CreateTableRequest();
        dynamoDB.createTable(createTableRequest);
    }
}

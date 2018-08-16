/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.util;

import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughputDescription;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

import net.vicp.sealedbook.dynamodb.entity.TableDescribeResponse;

/**
 * @author shitianshu on 2018/5/4 下午8:51.
 */
public final class Convertor {

    public static TableDescribeResponse convert(TableDescription describe) {

        TableDescribeResponse tableDescribeResponse = new TableDescribeResponse();
        tableDescribeResponse.setTableName(describe.getTableName());
        tableDescribeResponse.setTableStatus(describe.getTableStatus());
        tableDescribeResponse.setCreationDateTime(describe.getCreationDateTime().getTime());
        tableDescribeResponse.setItemCount(describe.getItemCount());
        tableDescribeResponse.setTableArn(describe.getTableArn());

        ProvisionedThroughputDescription provisionedThroughput = describe.getProvisionedThroughput();
        Long writeCapacityUnits = provisionedThroughput.getWriteCapacityUnits();
        tableDescribeResponse.setWriteCapacityUnits(writeCapacityUnits);

        Long readCapacityUnits = provisionedThroughput.getReadCapacityUnits();
        tableDescribeResponse.setReadCapacityUnits(readCapacityUnits);

        Date lastDecreaseDateTime = provisionedThroughput.getLastDecreaseDateTime();
        if (null != lastDecreaseDateTime) {
            tableDescribeResponse.setLastDecreaseDateTime(lastDecreaseDateTime.getTime());
        }
        Date lastIncreaseDateTime = provisionedThroughput.getLastIncreaseDateTime();
        if (null != lastIncreaseDateTime) {
            tableDescribeResponse.setLastIncreaseDateTime(lastIncreaseDateTime.getTime());
        }
        Long numberOfDecreasesToday = provisionedThroughput.getNumberOfDecreasesToday();
        tableDescribeResponse.setNumberOfDecreasesToday(numberOfDecreasesToday);

        List<AttributeDefinition> attributeDefinitions = describe.getAttributeDefinitions();
        List<KeySchemaElement> keySchema = describe.getKeySchema();

        String[] keys = fetchKeys(attributeDefinitions, keySchema);
        tableDescribeResponse.setHashKey(keys[0]);
        tableDescribeResponse.setHashKeyType(keys[1]);
        tableDescribeResponse.setSortKey(keys[2]);
        tableDescribeResponse.setSortKeyType(keys[3]);
        return tableDescribeResponse;
    }

    private static String[] fetchKeys(List<AttributeDefinition> attributeDefinitions, List<KeySchemaElement> keySchema) {
        AttributeDefinition hashAttributeDefinition = attributeDefinitions.get(0);

        AttributeDefinition sortAttributeDefinition = null;
        if (attributeDefinitions.size() == 2) {
            sortAttributeDefinition = attributeDefinitions.get(1);
        }
        String hash = hashAttributeDefinition.getAttributeName();
        String hashType = hashAttributeDefinition.getAttributeType();
        String sort = "-";
        String sortType = "-";
        if (null != sortAttributeDefinition) {
            sort = sortAttributeDefinition.getAttributeName();
            sortType = sortAttributeDefinition.getAttributeType();
        }

        return new String[]{hash, hashType, sort, sortType};

    }
}

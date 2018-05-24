/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.entity;

import java.io.Serializable;

/**
 * @author shitianshu on 2018/5/4 下午8:30.
 */
public class TableDescribeResponse implements Serializable {

    /** 表名 */
    private String tableName;

    /** 分区键 */
    private String hashKey;

    /** 分区键类型 */
    private String hashKeyType;

    /** 排序键 */
    private String sortKey;

    /** 排序键类型 */
    private String sortKeyType;

    /** 表状态 */
    private String tableStatus;

    /** 表创建时间 */
    private long creationDateTime;

    /** 项目总数 */
    private long itemCount;

    /** 资源名称 */
    private String tableArn;

    /** 当前写入吞吐量 */
    private Long writeCapacityUnits;

    /** 当前读取吞吐量 */
    private Long readCapacityUnits;

    /** 上次减少时间 */
    private Long lastDecreaseDateTime;

    /** 上次增加时间 */
    private Long lastIncreaseDateTime;

    /** 当天减少次数 */
    private Long numberOfDecreasesToday;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

    public long getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(long creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    public String getTableArn() {
        return tableArn;
    }

    public void setTableArn(String tableArn) {
        this.tableArn = tableArn;
    }

    public Long getWriteCapacityUnits() {
        return writeCapacityUnits;
    }

    public void setWriteCapacityUnits(Long writeCapacityUnits) {
        this.writeCapacityUnits = writeCapacityUnits;
    }

    public Long getReadCapacityUnits() {
        return readCapacityUnits;
    }

    public void setReadCapacityUnits(Long readCapacityUnits) {
        this.readCapacityUnits = readCapacityUnits;
    }

    public Long getLastDecreaseDateTime() {
        return lastDecreaseDateTime;
    }

    public void setLastDecreaseDateTime(Long lastDecreaseDateTime) {
        this.lastDecreaseDateTime = lastDecreaseDateTime;
    }

    public Long getLastIncreaseDateTime() {
        return lastIncreaseDateTime;
    }

    public void setLastIncreaseDateTime(Long lastIncreaseDateTime) {
        this.lastIncreaseDateTime = lastIncreaseDateTime;
    }

    public Long getNumberOfDecreasesToday() {
        return numberOfDecreasesToday;
    }

    public void setNumberOfDecreasesToday(Long numberOfDecreasesToday) {
        this.numberOfDecreasesToday = numberOfDecreasesToday;
    }

    public String getHashKeyType() {
        return hashKeyType;
    }

    public void setHashKeyType(String hashKeyType) {
        this.hashKeyType = hashKeyType;
    }

    public String getSortKeyType() {
        return sortKeyType;
    }

    public void setSortKeyType(String sortKeyType) {
        this.sortKeyType = sortKeyType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableDescribeResponse{");
        sb.append("tableName='").append(tableName).append('\'');
        sb.append(", hashKey='").append(hashKey).append('\'');
        sb.append(", hashKeyType='").append(hashKeyType).append('\'');
        sb.append(", sortKey='").append(sortKey).append('\'');
        sb.append(", sortKeyType='").append(sortKeyType).append('\'');
        sb.append(", tableStatus='").append(tableStatus).append('\'');
        sb.append(", creationDateTime=").append(creationDateTime);
        sb.append(", itemCount=").append(itemCount);
        sb.append(", tableArn='").append(tableArn).append('\'');
        sb.append(", writeCapacityUnits=").append(writeCapacityUnits);
        sb.append(", readCapacityUnits=").append(readCapacityUnits);
        sb.append(", lastDecreaseDateTime=").append(lastDecreaseDateTime);
        sb.append(", lastIncreaseDateTime=").append(lastIncreaseDateTime);
        sb.append(", numberOfDecreasesToday=").append(numberOfDecreasesToday);
        sb.append('}');
        return sb.toString();
    }
}

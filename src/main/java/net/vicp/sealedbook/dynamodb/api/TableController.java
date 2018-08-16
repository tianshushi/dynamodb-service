/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.vicp.sealedbook.dynamodb.entity.TableCreateRequest;
import net.vicp.sealedbook.dynamodb.entity.TableDescribeResponse;
import net.vicp.sealedbook.dynamodb.handler.TableHandler;
import net.vicp.sealedbook.dynamodb.util.ApiResponse;

/**
 * @author shitianshu on 2018/5/5 上午5:06.
 */
@RestController
@RequestMapping("/table")
public class TableController {

    @Resource
    private TableHandler tableHandler;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<List<String>> tableList(HttpServletRequest request) {
        List<String> tableNames = tableHandler.fetchTableNames();
        return ApiResponse.success(tableNames);
    }

    @RequestMapping(value = "/{tableName}/describe", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<TableDescribeResponse> tableDescribe(@PathVariable String tableName) {
        TableDescribeResponse tableDescribeResponse = tableHandler.fetchTableDescribe(tableName);
        return ApiResponse.success(tableDescribeResponse);
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse createTable(TableCreateRequest tableCreateRequest) {
        tableHandler.createTable(tableCreateRequest);
        return ApiResponse.success();
    }

}

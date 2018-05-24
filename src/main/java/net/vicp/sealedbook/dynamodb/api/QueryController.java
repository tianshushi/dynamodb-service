/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.api;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.vicp.sealedbook.dynamodb.handler.DataHandler;
import net.vicp.sealedbook.dynamodb.util.ApiResponse;

/**
 * @author shitianshu on 2018/5/23 下午2:53.
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    private static final Logger LOG = LoggerFactory.getLogger(QueryController.class);

    @Resource
    private DataHandler dataHandler;

    @RequestMapping(value = "/{tableName}/hash/{hashKeyValue}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse fetchOneValue(@PathVariable String tableName, @PathVariable String hashKeyValue) {
        Map<String, Object> objectMap = dataHandler.queryOne(tableName, hashKeyValue);
        LOG.info("query one. tableName:[{}], hashKeyValue:[{}], data:[{}]", tableName, hashKeyValue, objectMap);
        return ApiResponse.success(objectMap);
    }

}

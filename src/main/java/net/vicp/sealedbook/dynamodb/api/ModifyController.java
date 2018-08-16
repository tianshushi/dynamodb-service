/**
 * Copyright (c) 2017, TalkingData and/or its affiliates. All rights reserved.
 */
package net.vicp.sealedbook.dynamodb.api;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.vicp.sealedbook.dynamodb.handler.DataHandler;
import net.vicp.sealedbook.dynamodb.util.ApiResponse;

/**
 * @author shitianshu on 2018/5/23 下午6:34.
 */
@RestController
@RequestMapping("/modify")
public class ModifyController {

    @Resource
    private DataHandler dataHandler;

    @RequestMapping(value = "/{tableName}/hash/{hashKeyValue}", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse fetchOneValue(@PathVariable String tableName, @PathVariable String hashKeyValue,
                                     @RequestBody Map<String, Object> modifyData) {

        dataHandler.modify(tableName, hashKeyValue, modifyData);
        return ApiResponse.success();
    }
}

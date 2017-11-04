package io.tchepannou.academy.user.controller;

import io.tchepannou.academy.user.client.BaseResponse;
import io.tchepannou.academy.user.service.TransactionIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public abstract class BaseController {
    @Autowired
    private TransactionIdGenerator transactionIdGenerator;

    protected <T extends BaseResponse> T init(T response){
        response.setTransactionId(transactionIdGenerator.generate());
        response.setTransactionDateTime(new Date());
        return response;
    }
}

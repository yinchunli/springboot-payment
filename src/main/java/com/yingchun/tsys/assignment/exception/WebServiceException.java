package com.yingchun.tsys.assignment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class WebServiceException extends Exception {

        HttpStatus httpStatus;
        String internalError;
        Map internalErrorData;
        String message;
        long timestamp = System.currentTimeMillis();

    public WebServiceException(HttpStatus httpStatus, String internalError, String message) {
        this.httpStatus = httpStatus;
        this.internalError = internalError;
        this.message = message;
    }

    public WebServiceException(HttpStatus httpStatus, String internalError, String message, Map internalErrorData) {
        this.httpStatus = httpStatus;
        this.internalError = internalError;
        this.message = message;
        this.internalErrorData = internalErrorData;
    }

    public Map toEntity () {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", timestamp);
        response.put("message", message);
        response.put("httpStatusCode", httpStatus);
        response.put("internalError", internalError);
        response.put("internalErrorData", internalErrorData);
        return response;
    }
}

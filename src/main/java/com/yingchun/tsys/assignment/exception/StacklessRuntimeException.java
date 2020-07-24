package com.yingchun.tsys.assignment.exception;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * An exception which does not fill in a stack trace
 * for performance reasons.
 */
@Slf4j
@SuppressWarnings("serial")
public class StacklessRuntimeException extends RuntimeException {

    private HttpStatus httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    private String internalError;
    private String internalErrorData=null;


    public StacklessRuntimeException(HttpStatus code, String message, Object... args) {
        super(formatMessage(message, args));
        setInternalError(getMessage());
        setHttpStatusCode(code);
    }

    public StacklessRuntimeException(String message, Object... args) {
        super(formatMessage(message, args));
        setInternalError(getMessage());
    }

    /**
     * Does not fill in the stack trace for this exception
     * for performance reasons.
     *
     * @return this instance
     * @see java.lang.Throwable#fillInStackTrace()
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Map toEntity () {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now(ZoneId.of("America/New_York")));
        response.put("message", getMessage());
        response.put("httpStatusCode", getHttpStatusCode());
        response.put("internalError", getInternalError());
        response.put("internalErrorData", getInternalErrorData());
        return response;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatus httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getInternalError() {
        return internalError;
    }

    public void setInternalError(String internalError) {
        this.internalError = internalError;
    }

    public String getInternalErrorData() {
        return internalErrorData;
    }

    public void setInternalErrorData(String internalErrorData) {
        this.internalErrorData = internalErrorData;
    }
    
    public static String formatMessage (String message, Object... args) {
        return MessageFormatter.arrayFormat(message, args).getMessage();
    }
}

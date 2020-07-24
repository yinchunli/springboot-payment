package com.yingchun.tsys.assignment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class SpringMvcExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(HTTPException.class)
    protected ResponseEntity<Object> handleHttpException (HTTPException e, WebRequest request)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, e.getHttpMessage(), headers, HttpStatus.valueOf(e.getHttpCode()), null);
    }

    @ExceptionHandler(WebServiceException.class)
    protected ResponseEntity<Object> handleWebServiceException (WebServiceException e, WebRequest request)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, e.getMessage(), headers, e.httpStatus, null);
    }

    @ExceptionHandler(StacklessRuntimeException.class)
    protected ResponseEntity<Object> handleStacklessRuntimeException (StacklessRuntimeException ex, WebRequest request)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (ex.getHttpStatusCode() != null && ex.getHttpStatusCode().value() != HttpStatus.INTERNAL_SERVER_ERROR.value())
            return handleExceptionInternal(ex, ex.toEntity(), headers, ex.getHttpStatusCode(), request);

        return handleExceptionInternal(ex, ex.toEntity(), headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}

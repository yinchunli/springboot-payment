package com.yingchun.tsys.assignment.exception;

import java.io.IOException;

public class HTTPException extends IOException
{
    private final int httpCode;
    private final String httpMessage;
    private final String httpError;
    private final String url;

    public HTTPException(int httpCode, String httpMessage, String httpError, String url)
    {
        super(String.format("URL: %1$s Code: %2$s Message: %3$s Error: %4$s", url, String.valueOf(httpCode), httpMessage, httpError));
        this.httpCode = httpCode;
        this.httpMessage = httpMessage;
        this.httpError = httpError;
        this.url = url;
    }

    /**
     * @return the httpCode
     */
    public int getHttpCode()
    {
        return httpCode;
    }

    /**
     * @return the httpMessage
     */
    public String getHttpMessage()
    {
        return httpMessage;
    }

    /**
     * @return the httpError
     */
    public String getHttpError()
    {
        return httpError;
    }
}

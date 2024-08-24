package com.mtasci.foreign_exchange.contracts;

import java.io.Serializable;

public class BaseResponse implements ApiResponse, Serializable {

    ResponseHeader responseHeader;

    @Override
    public ResponseHeader getResponseHeader() { return responseHeader; }

    @Override
    public void setResponseHeader(ResponseHeader responseHeader) { this.responseHeader = responseHeader; }

}

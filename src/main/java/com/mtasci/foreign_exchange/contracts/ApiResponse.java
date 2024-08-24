package com.mtasci.foreign_exchange.contracts;

import java.io.Serializable;

public interface ApiResponse extends Serializable {

    ResponseHeader getResponseHeader();

    void setResponseHeader(ResponseHeader responseHeader);

}

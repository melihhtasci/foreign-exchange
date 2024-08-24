package com.mtasci.foreign_exchange.contracts;

import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;
import com.mtasci.foreign_exchange.enums.ApplicationResponseCode;

public class ResponseBuilder<T> {

    public static <T extends BaseResponse> T success(T response) {
        final var header = ResponseHeader.builder().success(true)
                .code(ApplicationResponseCode.CODE_200.getCode())
                .message(ApplicationResponseCode.CODE_200.getDescription())
                .detailMessage(null).build();
        response.setResponseHeader(header);
        return response;
    }

    public static BaseResponse error(ForeignExchangeException e) {
        final BaseResponse baseResponse = new BaseResponse();
        final var responseHeader = ResponseHeader.builder().success(false)
                .code(e.getCode())
                .message(e.getMessage())
                .detailMessage(e.getDetailMessage())
                .build();
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

    public static BaseResponse error(int code, String message, String detailMessage) {
        final BaseResponse baseResponse = new BaseResponse();
        final var responseHeader = ResponseHeader.builder().success(false)
                .code(code)
                .message(message)
                .detailMessage(detailMessage)
                .build();
        baseResponse.setResponseHeader(responseHeader);
        return baseResponse;
    }

}

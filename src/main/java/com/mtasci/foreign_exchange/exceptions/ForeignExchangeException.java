package com.mtasci.foreign_exchange.exceptions;

import com.mtasci.foreign_exchange.enums.ApplicationResponseCode;


public class ForeignExchangeException extends Exception {

    private int code;
    private String message;
    private String detailMessage;

    public ForeignExchangeException(String message) {
        super(message);
        this.message = message;
    }

    public ForeignExchangeException(ApplicationResponseCode responseCode) {
        super(responseCode.getDescription());
        this.code = responseCode.getCode();
    }

    public ForeignExchangeException(Throwable cause, int code) {
        super(cause.getMessage(), cause);
        this.code = code;
    }

    public ForeignExchangeException(ApplicationResponseCode responseCode, Throwable cause) {
        super(responseCode.getDescription(), cause);
        this.code = responseCode.getCode();
        this.message = responseCode.getDescription();
    }

    public ForeignExchangeException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
        this.detailMessage = "An error occurred. Code: " + code;
    }

    public ForeignExchangeException(String message, String detailMessage, int code) {
        super(message);
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    public ForeignExchangeException(String message, Throwable cause) {
        super(message, cause);
        this.code = ApplicationResponseCode.CODE_999.getCode();
        this.detailMessage = ApplicationResponseCode.CODE_999.getDescription();
    }

    public int getCode() {
        return code;
    }
    public String getMessage() { return message; }
    public String getDetailMessage() { return detailMessage; }

}

package com.mtasci.foreign_exchange.enums;

public enum ApplicationResponseCode {
    CODE_200(200, "Successful"),
    CODE_400(400, "Bad request. Invalid request field(s)"),
    CODE_404(404, "Not found"),
    CODE_500(500, "Unexpected Server Error"),
    CODE_999(999, "Unknown"),
    CODE_1099(1099, "Currency layer client error");

    private int code;
    private String description;

    ApplicationResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

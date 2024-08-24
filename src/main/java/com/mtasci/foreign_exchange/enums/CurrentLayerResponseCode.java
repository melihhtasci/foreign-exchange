package com.mtasci.foreign_exchange.enums;

public enum CurrentLayerResponseCode {
    ERROR_404(404, "User requested a resource which does not exist."),
    ERROR_101(101, "User did not supply an access key or supplied an invalid access key."),
    ERROR_103(103, "User requested a non-existent API function."),
    ERROR_104(104, "User has reached or exceeded his subscription plan's monthly API request allowance."),
    ERROR_105(105, "The user's current subscription plan does not support the requested API function."),
    ERROR_106(106, "The user's query did not return any results."),
    ERROR_102(102, "The user's account is not active. User will be prompted to get in touch with Customer Support."),
    ERROR_201(201, "User entered an invalid Source Currency."),
    ERROR_202(202, "User entered one or more invalid currency codes."),
    ERROR_301(301, "User did not specify a date. [historical]"),
    ERROR_302(302, "User entered an invalid date. [historical, convertCurrency]"),
    ERROR_401(401, "User entered an invalid 'from' property. [convertCurrency]"),
    ERROR_402(402, "User entered an invalid 'to' property. [convertCurrency]"),
    ERROR_403(403, "User entered no or an invalid 'amount' property. [convertCurrency]"),
    ERROR_501(501, "User did not specify a Time-Frame. [timeframe, convertCurrency]"),
    ERROR_502(502, "User entered an invalid 'start_date' property. [timeframe, convertCurrency]"),
    ERROR_503(503, "User entered an invalid 'end_date' property. [timeframe, convertCurrency]"),
    ERROR_504(504, "User entered an invalid Time-Frame. [timeframe, convertCurrency]"),
    ERROR_505(505, "The Time-Frame specified by the user is too long - exceeding 365 days. [timeframe]"),
    ERROR_999(999, "Unmapped error code");

    private final int code;
    private final String description;

    CurrentLayerResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static CurrentLayerResponseCode findByCode(int code) {
        for (CurrentLayerResponseCode errorCode : CurrentLayerResponseCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return ERROR_999;
    }

}

package com.mtasci.foreign_exchange.constants;

public class ApiExampleResponses {

    public final static String CURRENCY_BAD_REQUEST = "\"{\\\"responseHeader\\\":{\\\"success\\\":false,\\\"code\\\":400,\\\"message\\\":\\\"Bad request. Invalid request field(s)\\\",\\\"detailMessage\\\":\\\"'from' parameter is required.\\\"}}\"";
    public final static String CURRENCY_NOT_FOUND = "\"{\\\"responseHeader\\\":{\\\"success\\\":false,\\\"code\\\":404,\\\"message\\\":\\\"some message\\\",\\\"detailMessage\\\":\\\"some message.\\\"}}\"";

    public final static String EXCHANGE_RATE_SUCCESS = "{\"responseHeader\":{\"success\":true,\"code\":200,\"message\":\"Successful\",\"detailMessage\":null},\"conversion\":0.026712}";

    public static final String HISTORY_BAD_REQUEST = "{\n  \"responseHeader\": {\n    \"success\": false,\n    \"code\": 400,\n    \"message\": \"Bad request. Invalid request field(s)\",\n    \"detailMessage\": \"Invalid transaction date or transaction id.\"\n  }\n}";
    public final static String HISTORY_NOT_FOUND = "{\n  \"responseHeader\": {\n    \"success\": false,\n    \"code\": 404,\n    \"message\": \"Not found.\",\n    \"detailMessage\": \"Currency conversion history cannot be found.\"\n  }\n}";

}

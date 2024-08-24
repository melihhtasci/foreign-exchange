package com.mtasci.foreign_exchange.contracts;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseHeader implements Serializable {

    @Schema(name = "success", example = "true", description = "Shows is the response success")
    private boolean success;
    @Schema(name = "code", example = "200", description = "Shows response return code")
    private int code;
    @Schema(name = "message", example = "Successful", description = "Message")
    private String message;
    @Schema(name = "detailMessage", example = "Successful", description = "Detailed message")
    private String detailMessage;

}

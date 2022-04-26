package com.cbd.finance.exception;

import lombok.*;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String code;
    private String message;
}

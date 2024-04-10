package com.imanuel.ronaldo.simplepos.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private final int httpStatusCode;
    private final int errorCode;
    private final String errorMessage;
}

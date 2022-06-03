package com.itechart.demojavakotlin.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder //todo remove after auth
public class UserRequest {

    private String name;
    private BigDecimal money;
}

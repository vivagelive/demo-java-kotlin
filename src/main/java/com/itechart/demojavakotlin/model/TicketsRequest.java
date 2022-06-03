package com.itechart.demojavakotlin.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketsRequest {

    private BigDecimal price;
    private int quantity;
    private String movieName;
}

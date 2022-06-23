package com.itechart.demojavakotlin.service.impl;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentInformationService {
    private static PaymentInformationService INSTANCE = new PaymentInformationService();

    private PaymentInformationService() {
    }

    public static PaymentInformationService getInstance() {
        return INSTANCE;
    }

    public void createBill(final String buyerName, final String movieName, final int quantity, final BigDecimal finalPrice) {
        System.out.printf("%n %-5s | %-7s | %-13s | %-5s %n", "Movie", "Ticket quantity", "Buyer name", "Final price");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-5s | %-15d | %-10s | %-11s %n", movieName, quantity, buyerName, finalPrice);
        System.out.println("-----------------------------------------------------");
    }
}

package com.itechart.demojavakotlin.service.impl;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.math.BigDecimal;

@Getter
@Setter
public class PaymentInformationService {
    private static final String LINE = "-------------------------------------------------------";

    private static PaymentInformationService INSTANCE = new PaymentInformationService();

    private PaymentInformationService() {
    }

    public static PaymentInformationService getInstance() {
        return INSTANCE;
    }

    public void createBill(final String buyerName, final String movieName, final int quantity, final BigDecimal finalPrice) {

        final String bill = String.format("%n %-5s | %-7s | %-13s | %-5s | %n" +
                "%s %n%-4s | %-15d | %-10s | %-11s %n %s", "Movie", "Ticket quantity", "Buyer name", "Final price",
                LINE, movieName, quantity, buyerName, finalPrice, LINE);

        final File paymentInformation = new File("src/main/resources/templates/bill.txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(paymentInformation);
            fileOutputStream.write(bill.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

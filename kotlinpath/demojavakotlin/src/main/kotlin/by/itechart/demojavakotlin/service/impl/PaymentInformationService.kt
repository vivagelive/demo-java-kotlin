package by.itechart.demojavakotlin.service.impl

import java.io.File
import java.io.FileOutputStream
import java.math.BigDecimal

object PaymentInformationService {
    fun createBill(buyerName: String, movieName: String, quantity: Int, finalPrice: BigDecimal) {
        val bill = """
            Movie | Ticket quantity | Buyer name    | Final price | 
            ------------------------------------------------------- 
            $movieName | $quantity  | $buyerName    | $finalPrice       
             ------------------------------------------------------
        """.trimIndent()
        val paymentInformation = File("src/main/resources/templates/bill.txt")
        FileOutputStream(paymentInformation).use {
            it.write(bill.toByteArray())
        }
    }
}

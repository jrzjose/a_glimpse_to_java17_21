package org.example.patternmatching;

import java.util.Date;

import org.example.record.Transaction;
import org.example.record.TransactionType;

public class TransactionAlert {

    public void checkUsingRecordPattern(Object transac) {
        // Java 21+: destructure the record using ReconrdPattern
        if (transac instanceof Transaction(Date date, String description, Double amount, TransactionType transactionType)) {
            System.out.println("date=" + date + ", description=" + description
                + ", amount=" + amount + ", transactionType=" + transactionType );
        }
        else {
            // ...
        }
    }
}

package org.example.record;

import java.util.Date;

public record Transaction(Date date, String description, Double amount, TransactionType transactionType) {
}

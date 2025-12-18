package org.example.record;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "BANK_TRANSACTION")
public class BankTransaction {
    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "DATETIME")
    private Date date;
    
    private String description;
    
    private Double amount;
    
    // @Enumerated(EnumType.STRING)
    @Convert(converter = TransactionTypeConverter.class)
    private TransactionType transactionType;

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }    
}

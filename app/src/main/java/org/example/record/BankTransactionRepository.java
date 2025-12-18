package org.example.record;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankTransactionRepository  extends JpaRepository<BankTransaction, String> {

    Transaction findByTransactionId(String transactionId);

    @Query("SELECT new org.example.record.Transaction(t.date, t.description, t.amount, t.transactionType)" +
           "FROM BankTransaction t WHERE t.transactionType = :transactionType")
    List<Transaction> fetchAll(TransactionType transactionType);
    
    @Query("SELECT new org.example.record.Transaction(t.date, t.description, t.amount, t.transactionType)" +
           "FROM BankTransaction t WHERE t.transactionId = :transactionId")
    Transaction findTransactionIdUsingJPQL(String transactionId);
}

package org.example.record;

public enum TransactionType {
    DebitCardPurchase("DCP"),
    DirectDeposit("DP"),
    BankFee("BF"),
    ATMWithdrawal("ATMW"),
    BillPayment("BP"),
    CreditCardPayment("CCP");

    private String transactionCode;
    TransactionType(String transactionCode) {
        this.transactionCode = transactionCode;
    }
    public String getTransactionCode() {
        return transactionCode;
    }
}

/*
o User object.
o Book object.
o Transaction Type (String: "Borrow" or "Return").
o Transaction Date (String or Date object).
 */
package com.fsmvu.librarymanagementsistem;

public class Transaction {
    User user = new User();
    Book book =new Book();
    public static String TransactionTypeB="Borrow";
    public static String TransactionTypeR="Return";
    java.util.Date date= new java.util.Date();
    String transactionDate=date.toString();
}

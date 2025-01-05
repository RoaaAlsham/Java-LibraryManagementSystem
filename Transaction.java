/*
o User object.
o Book object.
o Transaction Type (String: "Borrow" or "Return").
o Transaction Date (String or Date object).
 */
package com.fsmvu.librarymanagementsistem;

import static com.fsmvu.librarymanagementsistem.Library.defaultCapacity;

public class Transaction {
    private User user = new User();
    private Book book =new Book();
    private String TransactionType;
    private java.util.Date date= new java.util.Date();
    static Transaction[] transactionRecords= new Transaction[defaultCapacity];
    
 
    Transaction(Book book, User user, String TransactionType, java.util.Date currentDate){//transaction contructor
        this.book= book;
        this.user=user;
        this.TransactionType=TransactionType;
        this.date=currentDate;
    }
  
    private void expandTransactionRecordsListByOne(){
        Transaction[] newTransactionRecords=new Transaction[transactionRecords.length+1];
        System.arraycopy(transactionRecords,0, newTransactionRecords,0,transactionRecords.length);
        transactionRecords=newTransactionRecords;
    }
    
    // method to add a transaction to the transaction list
    public void addToTransactionRecords(Transaction transaction){
        boolean isAdded=false;
        while(!isAdded){
            for(int i=0; i<transactionRecords.length; i++){
                if(transactionRecords[i]==null){
                    transactionRecords[i]=transaction;
                    isAdded=true;
                    System.out.println("transaction add to the transaction records list");
                    return;
                }
            }
            if(!isAdded){
                expandTransactionRecordsListByOne();
            }
        }
    }
    public static void displayTransaction(){
        for(int i=0; i<transactionRecords.length; i++){
            if(transactionRecords[i]!=null){
                String bookTitle= transactionRecords[i].book.getTitle();
                String userName= transactionRecords[i].user.getName();
                String transactionType=transactionRecords[i].TransactionType;
                String transactionDate=transactionRecords[i].date.toString();
                System.out.printf("\nThe book '%s' is %s by '%s' at the following date:\n %s "
                ,bookTitle, transactionType,userName,transactionDate);
            }
        
        }
    }
    
}

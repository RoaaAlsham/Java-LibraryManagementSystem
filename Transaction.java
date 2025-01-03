/*
o User object.
o Book object.
o Transaction Type (String: "Borrow" or "Return").
o Transaction Date (String or Date object).
 */
package com.fsmvu.librarymanagementsistem;

import static com.fsmvu.librarymanagementsistem.Library.defaultCapacity;

public class Transaction {
    User user = new User();
    Book book =new Book();
    private String TransactionType;
    java.util.Date date= new java.util.Date();
    String TransactionDate=date.toString();
    
    Transaction(){}
    
    Transaction(Book book, User user, String TransactionType){//transaction contructor
        this.book= book;
        this.user=user;
        this.TransactionType=TransactionType; 
    }
    
    static Transaction[] transactionRecords= new Transaction[defaultCapacity];
    
    public void expandTransactionRecordsListByOne(){
        Transaction[] newTransactionRecords=new Transaction[transactionRecords.length+1];
        System.arraycopy(transactionRecords,0, newTransactionRecords,0,transactionRecords.length);
        transactionRecords=newTransactionRecords;
    }
    
    // method to add a transaction to the transaction list
    public void addToTransactionRecords(Transaction transaction){
        boolean isAdded=false;
        while(!isAdded){
            for(int i=0; i<transactionRecords.length; i++){
                if(transactionRecords[i]!=null){
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
                String transactionDate=transactionRecords[i].TransactionDate;
                System.out.printf("\nThe book %s %s by %s at the following date:\n %s "
                ,bookTitle,userName, transactionType,transactionDate);
            }
        
        }
    }
    
}

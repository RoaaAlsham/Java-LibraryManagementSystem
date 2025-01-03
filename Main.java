/*
 o In the main method, display a menu for the librarian to:
▪ Add books.
▪ Register users.
▪ Borrow books.
▪ Return books.
▪ Display all the books.
▪ View all transactions.
▪ Exit the program.
 */
package com.fsmvu.librarymanagementsistem;

public class Main {
    
    public static void main(String[] args) {
        Library library =new Library();
        library.addNewBook();
        System.out.println("////");
        library.registerNewUser();
        System.out.println("////");
        
        library.borrowBook();
        System.out.println("////");
        
        Book.displayShelves();
        System.out.println("////");
        Transaction.displayTransaction();
    }
    
}
package com.fsmvu.librarymanagementsistem;
import static com.fsmvu.librarymanagementsistem.Library.libraryObj;
import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) {
        
        Scanner scan=new Scanner(System.in);
        System.out.println("""
                           _____________________________
                           ##Library Management System##
                           _____________________________
                           1. Add a New Book 
                           2. Register a New User 
                           3. Borrow a Book 
                           4. Return a Book 
                           5. Display all Books
                           6. View Transactions
                           7. View Bookshelf
                           8. Exit
                           _____________________________
                       
                           """);
        System.out.println("Enter the corresponding number of the method you want to use ");
        int input= scan.nextInt();
        while(input!=8){
        
            switch(input){
                case 1:
                    libraryObj.addNewBook();
                    break;
                case 2:
                    libraryObj.registerNewUser();
                    break;
                case 3:
                    libraryObj.borrowBook();
                    break;
                case 4:
                    libraryObj.returnBook();
                    break;
                case 5:
                    libraryObj.displayBooksInLibrary();
                    break;
                case 6:
                    Transaction.displayTransaction();
                    break;
                case 7:
                    Book.displayShelves();
                    break;
                
                default:
                    System.out.println("please enter a number between 1 and 7 to use the program 😉");

            }
             System.out.println("");
             System.out.println("""
                           _____________________________
                           ##Library Management System##
                           _____________________________
                           1. Add a New Book 
                           2. Register a New User 
                           3. Borrow a Book 
                           4. Return a Book 
                           5. Display all Books
                           6. View Transactions
                           7. View Bookshelf
                           8. Exit
                           _____________________________
                       
                           """);
             
            System.out.println("Enter the corresponding number of the method you want to use ");
            input= scan.nextInt();   
        }    
        System.out.println("Have a nice day :) ");
   
    }
    
}
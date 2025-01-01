/*
o Stores books in shelves.
▪ Each shelf is represented as a book array and the shelves are represented in an
array of array of books.
▪ Each shelf have a capacity of 4 books.
▪ Books will be searched, borrowed from and returned to these shelves.
▪ When adding a book, if there is not enough shell space, a new shelf will be
created by increasing array size.
o Maintain a list of users, and transactions. Use arrays.
o Add new book method. Two books can’t have same title and author, add the necessary
checks here.
o Register new user method. Two users can’t have same e-mail, add the necessary checks
here.
o Handle borrowing and returning books using methods. Ensure a book is available before
borrowing.
o Keep track of book availability and user borrowing records in methods.
o Record all transactions.
 */
package com.fsmvu.librarymanagementsistem;

import java.util.Scanner;

public class Library {
    static Book[][] shelf = new Book[10][4];//make this array static to use it in the static method
    
    public static void addBookToLibraryShelves(Book book,int bookCopies){ //make method static to make it object independent   
        for(int i=0; i<shelf.length; i++){
            for(int j=0; j<shelf[i].length;j++){
                if(shelf[i][j]==null){
                    shelf[i][j]=book;
                    bookCopies--;
                    if(bookCopies==0){
                        System.out.println("copies added to library shelves successfully");
                        return; //finish after adding all copies of that book
                    }
                }
            }
        }
        System.out.println("the library shelves number will be increased");
        //if the compiler reachs this point, that means there is not enough space
         increaseShelvesNumber();
         addBookToLibraryShelves(book,bookCopies);
    }
    //to solve the issue that I need to update the left copies number else the method could work endlessly
    //create a second method with the same name but different parameter to start with
     public static void addBookToLibraryShelves(Book book){
         
         addBookToLibraryShelves(book,book.getAvailableCopies());
     }
    
    //since array size cannot be modified I need to create a new array and move the elements if the current array is full 
    public static void increaseShelvesNumber(){
        Book[][] updatedShelf=new Book[shelf.length+1][shelf[0].length];
        for(int i=0; i<shelf.length; i++){
            for(int j=0; j<shelf[i].length;j++){// duplicate elements to the new array
                updatedShelf[i][j]=shelf[i][j];    
            }
        }
        shelf=updatedShelf;
        
    }
    
    public static boolean isBookTitleExist(String bookTitle){//search for books
         for(int i=0; i<shelf.length; i++){
            for(int j=0; j<shelf[i].length;j++){
                if(shelf[i][j]==null){
                    return false;
                }else{
                    if(shelf[i][j].getTitle().equalsIgnoreCase(bookTitle))
                        return true;   } 
            }
         } 
         return false;
    }
    
    public static void addNewCopies(Book book, int numberOfNewCopies){ 
        book.addNewCopiesOfaBook(numberOfNewCopies);
        addBookToLibraryShelves(book, numberOfNewCopies);
    }
    
    //check if the title and author name are valid
    public static boolean isLetterOrSpace(String text){
        String upperCaseText=text.toUpperCase();
        for(int i=0; i<text.length();i++){
            if(!((upperCaseText.charAt(i)<= 'Z' 
                    && upperCaseText.charAt(i)>= 'A')|| upperCaseText.charAt(i)==' ')){
                return false;
            }
        }
        return true;
    }
    
    // this method will also ensure that the number is not negative
    public static boolean isNumber(String strNum){
        for(int i=0; i< strNum.length(); i++){
            if(!(strNum.charAt(i)>='0' && strNum.charAt(i)<='9'))
                return false;
        }
        return true;
    }
    //use a static method so that it can be used without a specific object
    
    public static void addNewBook(){
        
        Scanner scan= new Scanner(System.in);
        Book book =new Book(){};
        //write a while loop for every prompt so that user can not move to the next section until they enter a valid input
        while(true){
            System.out.print("Enter Book Title: ");
            String Title= scan.nextLine();
            
            if(isLetterOrSpace(Title)){
                if(isBookTitleExist(Title)){
                    System.out.printf("\nBook already %s exists. Enter how many new copies to add.",Title);
                    int numberOfNewCopies=scan.nextInt();
                    addNewCopies(book ,numberOfNewCopies); // fix yhe null issue
                    return;
                }else{
                    
                    book.setTitle(Title);
                    break;
                
                }   
                
            }
            else
                System.out.println(" a title can contain only letters or space ");  
        }
        //do the same for author name
        while(true){
            System.out.print("Enter Auhor: ");
            String Author= scan.nextLine();
            
            if(isLetterOrSpace(Author)){
                book.setAuthor(Author);
                break; //the only way to escape this loop is to enter a valid input
            }
            else
                System.out.println(" Author name can contain only letters or space ");  
        }
        
        while(true){
            System.out.println("\nEnter Total Copies:");
            /*adjust the parameter to be string and then convert the user input
            to int in order to avoid possible problems caused by the mixed use
            of scanner.nextInt and scanner.nextLine sequencely*/
            String strCopies=scan.nextLine();
            if(isNumber(strCopies)){
                int copiesNumber= Integer.parseInt(strCopies);
                book.setAvailableCopies(copiesNumber);
                break;
            }else{
                System.out.println("the copies number is not valid");
            }
        }
        
        System.out.printf("\nBook '%s' by %s has been added with %d copies.",
                book.getTitle(),book.getAuthor(), book.getAvailableCopies()); 
        
        Library.addBookToLibraryShelves(book);// add book to the library
        
        
    }
    
    //view shelves to track the curren sitution 
    public static void displayShelves(){
        for(int i=0; i<shelf.length; i++){
            for(int j=0; j<shelf[i].length;j++){
                if(shelf[i][j]==null)
                    return;
                else
                    System.out.print(shelf[i][j].getTitle() +" : ");
                
            }
            System.out.println("");
        }
    }
    
    
}

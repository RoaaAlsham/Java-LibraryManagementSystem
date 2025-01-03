/*
Book:
o Title (String).
o Author (String).
o Available Copies (int). A book cannot be borrowed if no copies are available.
o Total Copies (int).

*/
package com.fsmvu.librarymanagementsistem;

import static com.fsmvu.librarymanagementsistem.Library.defaultCapacity;

public class Book {
    private String Title;
    private String Author;
    private int AvailableCopies;
    private static int TotalCopies;
    private static Book[] booksInLibrary = new Book[defaultCapacity];
    Book(){} //empty constructor
    Book(String Title,String Author,int AvailableCopies){//constructor with parameter to assign to the ibject
        this.Title=Title;
        this.Author=Author;
        this.AvailableCopies= AvailableCopies;
        TotalCopies+=AvailableCopies;
    }
    //Since private data can not be accessed directly we use set and get methods
    // use this keyword to differentiate between the data fields and parameters
    public void setTitle(String Title){
            this.Title=Title;
    }
    public String getTitle(){
        return Title;
    }
    public void setAuthor(String Author){
            this.Author= Author;
    }
    public String getAuthor(){
        return Author;
    }
    public void setAvailableCopies(int copiesNumber){
         AvailableCopies= copiesNumber;
         TotalCopies+=copiesNumber;
    }
    public void addNewCopiesOfaBook(int numberOfNewCopies){
        AvailableCopies+=numberOfNewCopies;
        TotalCopies+=numberOfNewCopies;
    }
    public void decreaseAvailableCopiesNummByOne(){
        AvailableCopies--;
        TotalCopies--;
    }
    public int getAvailableCopies(){
        return AvailableCopies;
    }
    public int getTotalCopies(){
        return TotalCopies;
    }
    public static Book[] getBooksInLibrary(){
        return booksInLibrary;
    }
    
    
    //if we need to add more books to the library we will have to increase the array size
    //use private keyword because this method will be used only inside this class
    public static void expandLibraryBooksCapacitybyOne(){
        Book[] newBooksInLibrary= new Book[booksInLibrary.length+1];
        System.arraycopy(booksInLibrary,0,newBooksInLibrary,0, booksInLibrary.length);
        booksInLibrary=newBooksInLibrary;
    }
    //a method to add a book to the booksInLibrary array
    public boolean addToBooksInLibrary(Book book){
        boolean hasAdded=false;
        while(!hasAdded){
            for(int i=0; i<booksInLibrary.length; i++){
                if( booksInLibrary[i]== null){
                    booksInLibrary[i]=book;
                    hasAdded=true;       
                    break;
                }
            }
            if(!hasAdded)
                expandLibraryBooksCapacitybyOne();  
        }
        return hasAdded;
    }
    static Book[][] shelf = new Book[10][4];//make this array static to use it in the static method

    public void addBookToLibraryShelves(Book book,int bookCopies){ //make method static to make it object independent   
        int numberOfIncrements=0;
        while(bookCopies>0){
            for(int i=0; i<shelf.length; i++){
                for(int j=0; j<shelf[i].length;j++){
                    if(shelf[i][j]==null){
                        shelf[i][j]=book;
                        bookCopies--;
                        if(bookCopies==0){
                            System.out.println("copies added to library shelves successfully");
                            System.out.println((numberOfIncrements!=0)?("Built "+numberOfIncrements+" new shelves to accomodate new books"):" ");
                            System.out.printf("\nBuilt %d new shelves to accomodate new books", numberOfIncrements);
                            return; //finish after adding all copies of that book
                        }
                    }
                }
            }
            if(bookCopies!=0){
                numberOfIncrements++;
                //if the compiler reachs this point, that means there is not enough space
                 increaseShelvesNumber(); 
            }    
        }
    }
    //to solve the issue that I need to update the left copies number else the method could work endlessly
    //create a second method with the same name but different parameter to start with
     public void addBookToLibraryShelves(Book book){
         
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
    //edit this method to check both the title and the author name
    public static boolean isBookExistViaTitleAuthorInfo(String bookTitle,String bookAuthor){//search for books
         for(int i=0; i<booksInLibrary.length; i++){    
                if(booksInLibrary[i]!=null){
                    if((booksInLibrary[i].getTitle().equalsIgnoreCase(bookTitle)&&
                            booksInLibrary[i].getAuthor().equalsIgnoreCase(bookAuthor))){
                        return true;                        
                    }
                }              
         } 
         return false;
    }
    
    //write a method that retrun the in index of a book from a given title and author name
    public static int bookIndexViaTitleAuthorInfo(String title,String author){
        for(int i=0; i<booksInLibrary.length; i++){    
                if(booksInLibrary[i]!=null){
                    if(booksInLibrary[i].getTitle().equalsIgnoreCase(title)&&
                            booksInLibrary[i].getAuthor().equalsIgnoreCase(author)){
                        return i;                        
                    }
                }        
         } 
         return -1;    
    }
    
    public void addNewCopies(Book book, int numberOfNewCopies){ 
        book.addNewCopiesOfaBook(numberOfNewCopies);
        addBookToLibraryShelves(book, numberOfNewCopies);
    }
    
    // I need to return the book to access the available copies
    public static Book returnTheBookViaTitleAuthor(String title, String Author){
        for(int i=0; i<booksInLibrary.length; i++){
            if(booksInLibrary[i]!=null){
                if(booksInLibrary[i].getTitle().equalsIgnoreCase(title)&&
                        booksInLibrary[i].getTitle().equalsIgnoreCase(title)){
                    return booksInLibrary[i];
                }
            }
        }
        return null;
    }
    
    //write a method to remove the book from the book list
    public void replaceBorrowedBookWithNullFromBooksInLibrary(Book book){
            for(int i=0; i<booksInLibrary.length; i++){
                if( booksInLibrary[i]== book){
                    booksInLibrary[i]=null;
                    System.out.println("Good luck finding the latest copy. There are no longer any copies of this book available. :)");       
                    break;
                }
            }
    }
    
    //write a method to remove a copy of a book from the book shelf(replace with null)
    public void replaceBorrowedBookWithNullFromBookShelves(Book book){
        for(int i=0; i< shelf.length;i++){
            for(int j=0;j<shelf[i].length; j++){
                if(shelf[i][j]==book){
                    shelf[i][j]=null;
                    System.out.println("a copy of the book removed from the book shelf");
                    return;
                }
            }
        }
    }
    
    
    
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



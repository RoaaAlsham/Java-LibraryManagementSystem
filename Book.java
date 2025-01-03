/*
Book:
o Title (String).
o Author (String).
o Available Copies (int). A book cannot be borrowed if no copies are available.
o Total Copies (int).

*/
package com.fsmvu.librarymanagementsistem;

public class Book {
    private String Title;
    private String Author;
    private int AvailableCopies;
    static int TotalCopies;
    
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
    
    
    
}



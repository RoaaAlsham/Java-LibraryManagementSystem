/*
o Name (String).
o Email (String).
o Borrowed Books (Array of Book objects). A user can’t borrow more than 3 books at a time.
 */
package com.fsmvu.librarymanagementsistem;

public class User {
    private String Name;
    private String Email;
    private Book[] userBooks= new Book[3];
    
    User(String Name, String Email){
        this.Name= Name;
        this.Email=Email;
    }
    User(){}
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getName() {
        return Name;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public boolean canUserBorrowMore(){//check if the user exceeded the limit
        int count=0;
        for(int i=0; i<userBooks.length; i++){
            if(userBooks[i]!= null)
                count++;
        }
        if(count<3)
            return true;
        return false;    
    }
    public Book[] getUserBookList(){
        return userBooks;
    }
    //add a book to user's book list
    public void addBooktoUserBookList(Book book){
        for(int i=0;i<userBooks.length;i++){
            if(userBooks[i]==null){
                userBooks[i]=book;
                System.out.println("the book added to user's book successfully");
                return;
            }
        }
        System.out.println("the book didn't added to user book list, check the code again");
    }
    
}

/*
o Name (String).
o Email (String).
o Borrowed Books (Array of Book objects). A user can’t borrow more than 3 books at a time.
 */
package com.fsmvu.librarymanagementsistem;

public class User {
    private String Name;
    private String Email;
    private Book[] usersBooks= new Book[3];
    
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
        for(int i=0; i<usersBooks.length; i++){
            if(usersBooks[i]!= null)
                count++;
        }
        if(count<3)
            return true;
        return false;    
    }
    
    
}

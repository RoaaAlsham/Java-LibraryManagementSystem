/*
o Name (String).
o Email (String).
o Borrowed Books (Array of Book objects). A user can’t borrow more than 3 books at a time.
 */
package com.fsmvu.librarymanagementsistem;

import static com.fsmvu.librarymanagementsistem.Library.defaultCapacity;

public class User {
    private String Name;
    private String Email;
    private static Book[] userBooks= new Book[3];
    
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
    public Book[] getUserBookList(){
        return userBooks;
    }
    ///////////////////////////////////////////////////////////////////////////
    
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
    
    public static User[] registeredUsers= new User[defaultCapacity];
    
    //increase the capacity of the array if it is neccessary
    private void expandNumberOfUsersCapacitybyOn(){
        User[] newRegisteredUsers= new User[registeredUsers.length+1];
        System.arraycopy(registeredUsers,0,newRegisteredUsers,0,registeredUsers.length);
        registeredUsers=newRegisteredUsers;
    }
    
    public void addUsertoRegisteredUsersList(User user){
        boolean hasAdded=false;
        while(!hasAdded){
            for(int i=0; i< registeredUsers.length; i++){
                if(registeredUsers[i]==null){
                    registeredUsers[i]= user;
                    hasAdded= true;
                    return;
                }
            }
            if(!hasAdded){
                expandNumberOfUsersCapacitybyOn();
            }
        }  
    }
    
    //make method to check the user had already registered before or not by checking the email address
    public static boolean hasUserRegisteredBefore(String email){
        for(int i=0;i< registeredUsers.length; i++){
            if(registeredUsers[i]==null){
                return false;
            }else{
                if(registeredUsers[i].getEmail().equals(email)){
                    return true;
                }
            }
        }
        return false;
    }
    
    // I need to access to further properties of a user by his email so i need a method to return me the user' object
    public static User returnTheUserViaHisEmail(String email){
        for(int i=0;i< registeredUsers.length; i++){
            if(registeredUsers[i]!=null){
                if(registeredUsers[i].getEmail().equals(email)){
                    return registeredUsers[i];
                        }
                   }     
        }
        return null;// i hope that we won't reach to this line because i have already checked if the user exist
    }
}

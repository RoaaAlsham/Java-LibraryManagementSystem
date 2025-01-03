/*o Stores books in shelves.
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
    /*
    use this instance to implement methods (manage the library operation) so it is useful
    to be defined and accessable from eveywhere else I will have to declare all required methods to be static
    */
    public static Library libraryObj= new Library();
    public static final int defaultCapacity=10;
    //-------------------------VALIDATIONS--------------------------//
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
    
    //an email must contain @ sembol with no spaces
    public static boolean isValidEmail(String email){
        if(email.length()<4){
            System.out.println("emails length is insuffient");
            return false;
        }
        for(int i=0; i<email.length(); i++){
            if(email.charAt(i)==' '){
                System.out.println("An email cannot contain a space");
                return false;
            } 
        }
        if(!email.contains("@")){
            System.out.println("an email address must contain a '@' sembol!");
            return false;   
        }
        return true;
    }
    
    //-----------------------ADDING NEW BOOK------------------------//
    
    public void addNewBook(){

        String bookTitle, bookAuthor;
        int booksAvailableCopies;
        
        Scanner scan= new Scanner(System.in);  
        //write a while loop for every prompt so that user can not move to the next section until they enter a valid input
        while(true){
            System.out.print("\nEnter Book Title: ");
            String Title= scan.nextLine().trim();//delete extra spaces to avoid mistakes at (.equals) method
            if(isLetterOrSpace(Title)){
                System.out.print("Enter Auhor: ");
                String Author= scan.nextLine().trim();
                if(isLetterOrSpace(Author)){
                    if(Book.isBookExistViaTitleAuthorInfo(Title,Author)){
                        System.out.printf("\nBook already %s by %s exists. Enter how many new copies to add.",Title, Author);
                        int numberOfNewCopies=scan.nextInt();
                        int index= Book.bookIndexViaTitleAuthorInfo(Title, Author);
                        Book existedBook=Book.getBooksInLibrary()[index];
                        existedBook.addNewCopies(existedBook ,numberOfNewCopies);//come to check this line if something goes wrong
                        return;
                    }else{
                        bookTitle=Title;
                        bookAuthor=Author;
                        break;
                        }
                    }else{
                        System.out.println(" Author name can contain only letters or space ");
                    }      
            }else{
                System.out.println(" a title can contain only letters or space ");  
            }
        } 
        
        while(true){
            System.out.println("\nEnter Total Copies:");
            /*adjust the parameter to be string and then convert the user input
            to int in order to avoid possible problems caused by the mixed use
            of scanner.nextInt and scanner.nextLine sequencely*/
            String strCopies=scan.nextLine().trim();
            if(isNumber(strCopies)){
                int copiesNumber= Integer.parseInt(strCopies);
                booksAvailableCopies=copiesNumber;
                break;
            }else{
                System.out.println("the copies number is not valid");
            }
        }
        Book book= new Book(bookTitle, bookAuthor, booksAvailableCopies);
        
        book.addBookToLibraryShelves(book);// put the book on the library shelf
        book.addToBooksInLibrary(book);
        
        System.out.printf("\nBook '%s' by %s has been added with %d copies successfully.",
                book.getTitle(),book.getAuthor(), book.getAvailableCopies());  
    }
    //view shelves to track the current sitution 
     
    //------------------------REGISTER USERS-------------------------------//
    
    public void registerNewUser(){
        Scanner scan= new Scanner(System.in);
        String name, email;
        while(true){
            System.out.print("\nEnter User Name: ");
            String inputName=scan.nextLine().trim();
            if(isLetterOrSpace(inputName)){
                name=inputName;
                break;
            }else{
                System.out.println("\nUser name can only contains letter and spaces");
            }
        }
        while(true){
            System.out.println("Enter your Email: ");
            String inputEmail=scan.nextLine().trim();
            if(isValidEmail(inputEmail)){
                if(!User.hasUserRegisteredBefore(inputEmail)){
                    email=inputEmail;
                    break;
                }else{
                    System.out.println("user email is already exist");
                    return;
                }
            }
        }
        
        User user= new User(name, email);
        user.addUsertoRegisteredUsersList(user);
        System.out.printf("\nUser '%s' has been registered",name);  
    
    }
    //------------------------Borrow-a-Book--------------------------//
    /*
        // get the email from the user
            //check if he is registed
                and check if he can borrow more books
        //check if there is a copy of the book on the shelves that had not borrowed
            //decrement the available copies of that book anf=d the total books
            //add the chosen book to the users book list
            //make a method to replace the borrowed book's name on shelf with NULL*/
            //save the transaction
    
    public void borrowBook(){
        Scanner scan = new Scanner(System.in);
        // to be continued
        while(true){
            System.out.println("\nEnter your email address: ");
            String email= scan.nextLine().trim();
            if(User.hasUserRegisteredBefore(email)){
                User user= User.returnTheUserViaHisEmail(email);
                if(user.canUserBorrowMore()){
                    System.out.println("\nEnter Book Title: ");
                    String bookTitle= scan.nextLine();
                    System.out.println("\nEnter Author Name: ");
                    String bookAuthor= scan.nextLine();
                    if(Book.isBookExistViaTitleAuthorInfo(bookTitle, bookAuthor)){
                        Book book=Book.returnTheBookViaTitleAuthor(bookTitle, bookAuthor);
                         int availableCopies =book.getAvailableCopies();
                         if(availableCopies>1){
                                book.replaceBorrowedBookWithNullFromBookShelves(book);
                                book.decreaseAvailableCopiesNummByOne();
                                user.addBooktoUserBookList(book);
                                java.util.Date currentDate=new java.util.Date();
                                Transaction transaction= new Transaction(book,user,"Borrowed",currentDate);
                                transaction.addToTransactionRecords(transaction);
                                System.out.printf("Book '%s' has been borrowed by %s",book.getTitle(),user.getName());
                                return;

                         }else if(availableCopies==1){
                                book.replaceBorrowedBookWithNullFromBooksInLibrary(book);//because there is not any copy left
                                book.replaceBorrowedBookWithNullFromBookShelves(book);
                                book.decreaseAvailableCopiesNummByOne();
                                user.addBooktoUserBookList(book);
                                java.util.Date currentDate=new java.util.Date();
                                Transaction transaction= new Transaction(book,user,"Borrowed",currentDate);
                                transaction.addToTransactionRecords(transaction);
                                System.out.printf("Book '%s' has been borrowed by %s",book.getTitle(),user.getName());
                                return;
                         }else if(availableCopies==0){
                                System.out.println("Sorry, There is no copies of that book left");
                                return;// use return in a void method to terminate it completely
                         }
 
                    }else{
                        System.out.println("The book is not found in the library");
                        //instruct the user to add a new book
                    }
                    
                }else{
                    System.out.println("You have reached the maximum borrow limit.(three books at a time)");
                    return;
                    //instruct the user to return a book
                }
                
            }else{
                System.out.println("please register the user before borrowing a book");
                return;
                // Instruct the user to log in
            }
        }
    
    }

    
}

package library;

import java.io.*;
import java.util.*;

public class Library{
    Scanner sc = new Scanner(System.in);
    HashMap<Integer,Book> hm = new HashMap<>();
    public void logTransaction(String transaction) {
        try {
            FileWriter writer = new FileWriter("library_transaction.txt", true); // true means append to the file
            writer.write(new Date() + " - " + transaction + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }
    public void addBook(){
        System.out.print("Enter the Title : ");
        String bookTitle = sc.nextLine();
        System.out.print("Enter the Author : ");
        String author = sc.nextLine();
        System.out.print("Enter ISBN : ");
        int isbn = sc.nextInt();
        sc.nextLine();
        if(!hm.containsKey(isbn)){
            Book bk = new Book(bookTitle,author);
            hm.put(isbn,bk);
            System.out.println("Book Added : "+bookTitle);
            logTransaction("Added book: " + bk.getBookTitle() + ", ISBN: " + isbn+", Author: "+author);
        }
        else{
            System.out.println("ISBN Already Exists.");
        }
    }
    public void check(int isbn){
        if(!hm.containsKey(isbn)){
            System.out.println("No such book found.");
        }
    }
    public void issueBook(){
        System.out.print("Enter ISBN to Issue : ");
        int isbn = sc.nextInt();
        sc.nextLine();
        check(isbn);
        if(hm.containsKey(isbn)){
            Book b = hm.get(isbn);
            if(!b.getBookIssue()){
                System.out.println("Book Issued : "+b.getBookTitle());
                b.setBookIssue(true);
                logTransaction("Issued Book: "+b.getBookTitle()+"ISBN: "+isbn);
            }
            else{
                System.out.println("Book is already issued.");
            }
        }
    }
    public void returnBook(){
        System.out.print("Enter ISBN to Return : ");
        int isbn = sc.nextInt();
        sc.nextLine();
        check(isbn);
        if(hm.containsKey(isbn)){
            Book b = hm.get(isbn);
            System.out.println("Book Returned : "+b.getBookTitle());
            logTransaction("Returned Book: "+b.getBookTitle()+"ISBN: "+isbn);
            b.setBookIssue(false);
        }
    }
    public void checkAvailability(){
        System.out.print("Enter ISBN to Check Availability : ");
        int isbn = sc.nextInt();
        sc.nextLine();
        if(hm.containsKey(isbn)){
            System.out.println("Book is Available.");
        }
        else{
            System.out.println("No such book found.");
        }
    }
    public void viewAllBooks(){
        System.out.println("Books Available in the Library : ");

        for(Map.Entry<Integer,Book> entry : hm.entrySet()){
            Book b = hm.get(entry.getKey());
            System.out.println("Title : "+b.getBookTitle());
            System.out.println("Author : "+b.getAuthor());
            System.out.println("ISBN : "+entry.getKey());
            System.out.println("Book Issued : "+b.getBookIssue());
        }
    }
}
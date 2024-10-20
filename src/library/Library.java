package library;

import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Library{
    Scanner sc = new Scanner(System.in);
    final private static Logger logger = Logger.getLogger(Library.class.getName());
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library_management";
        String user = "root";
        String password = "2618";

        return DriverManager.getConnection(url,user,password);
    }
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
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement()){
            String addQuery = "INSERT INTO books (isbn, title, author) VALUES ("+isbn+", '"+bookTitle+"', '"+author+"')";
            stmt.executeUpdate(addQuery);
            System.out.println("Added Book : "+bookTitle);

            logTransaction("Added book: " + bookTitle + ", ISBN: " + isbn+", Author: "+author);
        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println("ISBN Already Exists.");
        }
        catch(SQLException e){
            logger.log(Level.SEVERE,"ERROR ADDING THE BOOK : "+bookTitle,e);
        }
    }
    public void issueBook(){
        System.out.print("Enter ISBN to Issue : ");
        int isbn = sc.nextInt();
        sc.nextLine();

        try(Connection conn = getConnection();
        Statement stmt = conn.createStatement()){
            String checkQuery = "SELECT * FROM books WHERE isbn = "+isbn;
            ResultSet rs = stmt.executeQuery(checkQuery);
            if(rs.next()){
                boolean isIssued = rs.getBoolean("is_issued");
                String title = rs.getString("title");
                if(!isIssued){
                    String issueQuery = "UPDATE books SET is_issued = true WHERE isbn = "+isbn;
                    stmt.executeUpdate(issueQuery);
                    System.out.println("Issued Book : "+title);
                    logTransaction("ISSUED BOOK : "+title+", ISBN : "+isbn);
                }
                else{
                    System.out.println("Book is already Issued.");
                }
            }
            else{
                System.out.println("No such book found.");
            }
        }
        catch (SQLException e){
            logger.log(Level.SEVERE,"ERROR ISSUING THE BOOK WITH ISBN : "+isbn);
        }
    }
    public void returnBook(){
        System.out.print("Enter ISBN to Return : ");
        int isbn = sc.nextInt();
        sc.nextLine();
        try(Connection conn = getConnection();
        Statement stmt = conn.createStatement()){
            String checkQuery = "SELECT * FROM books WHERE isbn = "+isbn;
            ResultSet rs = stmt.executeQuery(checkQuery);

            if(rs.next()){
                boolean isIssued = rs.getBoolean("is_issued");
                String title = rs.getString("title");
                if(isIssued){
                    String returnQuery = "UPDATE books SET is_issued = false WHERE isbn = "+isbn;
                    stmt.executeUpdate(returnQuery);

                    System.out.println("Returned Book : "+title);
                    logTransaction("Returned Book : "+title+", ISBN : "+isbn);
                }
                else{
                    System.out.println("Book is already returned.");
                }
            }
            else{
                System.out.println("No such book found.");
            }
        }
        catch(SQLException e){
            logger.log(Level.SEVERE,"ERROR RETURNING THE BOOK WITH ISBN : "+isbn);
        }
    }
    public void checkAvailability(){
        System.out.print("Enter ISBN to Check Availability : ");
        int isbn = sc.nextInt();
        sc.nextLine();
        try(Connection conn = getConnection();
        Statement stmt = conn.createStatement()){
            String checkQuery = "SELECT * FROM books WHERE isbn = "+isbn;
            ResultSet rs = stmt.executeQuery(checkQuery);

            if(rs.next()){
                boolean isIssued = rs.getBoolean("is_issued");
                System.out.println("Title : "+rs.getString("title"));
                System.out.println("Author : "+rs.getString("author"));
                System.out.println("Availability : "+(isIssued ? "Issued" : "Available"));
            }

            else {
                System.out.println("No such book found.");
            }
        }
        catch(SQLException e){
            logger.log(Level.SEVERE,"ERROR CHECKING THE AVAILABILITY OF THE BOOK WITH ISBN : "+isbn);
        }
    }
    public void viewAllBooks(){
        System.out.println("Books Available in the Library : ");

        try(Connection conn = getConnection();
        Statement stmt = conn.createStatement()){
            String displayQuery = "SELECT * FROM books";
            ResultSet rs = stmt.executeQuery(displayQuery);
            boolean hasBooks = false;
            while(rs.next()){
                hasBooks = true;
                boolean isIssued =rs.getBoolean("is_issued");
                System.out.println("ISBN : "+rs.getInt("isbn"));
                System.out.println("Title : "+rs.getString("title"));
                System.out.println("Author : "+rs.getString("author"));
                System.out.println("Availability : "+(isIssued ? "Issued" : "Available"));
            }
            if(!hasBooks){
                System.out.println("No Books Found.");
            }
        }
        catch(SQLException e){
            logger.log(Level.SEVERE,"ERROR RETRIEVING THE BOOKS FROM DATABASE.");
        }
    }
}
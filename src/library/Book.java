package library;

public class Book{
    final private String bookTitle;
    final private String author;
    private boolean bool;
    public Book(String bookTitle,String author){
        this.bookTitle=bookTitle;
        this.author=author;
    }

    public String getBookTitle(){
        return bookTitle;
    }
    public String getAuthor(){
        return author;
    }
    public void setBookIssue(boolean bool){
        this.bool=bool;
    }
    public boolean getBookIssue(){
        return bool;
    }
}
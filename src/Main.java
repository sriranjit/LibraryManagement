import java.util.*;
import library.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Library lb = new Library();
        boolean bool = true;
        while(bool){
            System.out.println("1.Add Book\n2.View All Books\n3.Issue Book\n4.Return Book\n5.Check Availability\n6.Exit");
            int opt = sc.nextInt();
            sc.nextLine();
            switch (opt){
                case 1:
                    lb.addBook();
                    break;
                case 2:
                    lb.viewAllBooks();
                    break;
                case 3:
                    lb.issueBook();
                    break;
                case 4:
                    lb.returnBook();
                    break;
                case 5:
                    lb.checkAvailability();
                    break;
                case 6:
                    System.out.print("Do you want to exit?(y/n) : ");
                    String ch = sc.nextLine();
                    if(!ch.equals("n")){
                        bool=false;
                        break;
                    }
            }
        }
    }
}
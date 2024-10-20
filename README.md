# Library Management System

This project is a simple console-based Library Management System built using Java. It allows users to manage a library by adding books, issuing books, returning them, checking availability, and maintaining a transaction log.

## Features

- **Add a Book**: Add a book with its title, author, and ISBN.
- **View All Books**: List all the books currently available in the library.
- **Issue a Book**: Issue a book to a user by its ISBN.
- **Return a Book**: Return a previously issued book.
- **Check Availability**: Check if a specific book is available in the library by its ISBN.
- **Transaction Logging**: Records all transactions (add, issue, return) in a text file called `transactions.txt`.
- **Database Connectivity**: Connects to a MySQL database to store and manage book data.

## Technologies Used

- **Java**: The primary programming language used for the application.
- **MySQL**: Database management system to store book and transaction data.

## Project Structure

- `Main.java`: The main class where the program starts. It handles user interaction through a menu.
- `Library.java`: Contains methods to manage library operations such as adding books, issuing books, and checking availability.
- `Book.java`: Represents a book with its title, author, and issuance status.
- `transactions.txt`: A log file where all transactions (add, issue, return) are recorded.

## Setup Instructions

1. **Clone or Download the Repository**  
   First, clone the repository to your local machine or download the ZIP file:  
   ```bash
   git clone https://github.com/your-repository/library-management-system.git
   ```

2. **Navigate to the Project Directory**  
   After cloning or downloading, navigate into the project directory:  
   ```bash
   cd library-management-system
   ```

3. **Set Up the MySQL Database**
  - Create a new MySQL database (e.g., `library_db`).
  - Run the following SQL commands to create a `books` table:  
    ```bash
    CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    is_issued BOOLEAN DEFAULT FALSE
    );
    ```

4. **Compile the Java Files**  
   You can compile the Java files using any IDE like IntelliJ IDEA, Eclipse, or using the command line. To compile via command line:  
   ```bash
   javac Main.java
   ```

5. **Run the Application**  
   After compilation, you can run the application by executing:  
   ```bash
   java Main
   ```

## Usage

Once the application is running, you will see a menu like the following in the console:

1. Add Book
2. View All Books
3. Issue Book
4. Return Book
5. Check Availability
6. Exit

### Usage Details

- **Add a Book**: Choose option 1 to add a book. You will be prompted to enter the title, author, and ISBN of the book.
- **View All Books**: Choose option 2 to view all the books in the library. This will display the title, author, ISBN, and issue status of each book.
- **Issue a Book**: Choose option 3 to issue a book. Enter the ISBN of the book you want to issue. The book will be marked as issued, and the transaction will be logged.
- **Return a Book**: Choose option 4 to return a book. Enter the ISBN of the book being returned. The book will be marked as available, and the return transaction will be logged.
- **Check Availability**: Choose option 5 to check the availability of a book. Enter the ISBN of the book to check if it is available or already issued.
- **Exit**: Choose option 6 to exit the program.

### Logging Transactions

All transactions are logged in a file called `transactions.txt`, which is created in the project root directory:
- Adding a book logs the book title and ISBN.
- Issuing a book logs the book title and the fact that it has been issued.
- Returning a book logs the book title and the fact that it has been returned.

## Prerequisites

- Java 8 or higher installed on your machine.
- MySQL Server installed and running.
- Any Java IDE (Optional, but recommended for a better development experience).

## Contributing

Feel free to submit issues or contribute by making a pull request if you have improvements or bug fixes.

## Author

**Sri Ranjit M**  
[GitHub Profile](https://github.com/sriranjit)  
[sriranjitmohan26@gmail.com](mailto:sriranjitmohan26@gmail.com)

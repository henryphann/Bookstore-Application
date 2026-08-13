package coe528.project;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

/**
 *
 * @author Henry Phan
 */
public class FileHandler 
{
    private static final String bookFile = "books.txt";
    private static final String customerFile = "customers.txt";
    
    /**
     * REQUIRES: books.txt must exist
     * MODIFIES: nothing
     * EFFECTS: Will load books from books.txt and return it as a list.
     */
    public List<Book> loadBooks()
    {
        List<Book> books = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(bookFile)))
        {
            String i;
            while((i = reader.readLine()) != null)
            {
                String[] data = i.split(",");
                String title = data[0];
                String price = data[1].trim();
                books.add(new Book(title, price));      
            }
        }
        catch (IOException e)
        {
            System.out.println("Error. Can not load book. " + e.getMessage());
        }
        return books;
    }
    
    /**
     * REQUIRES: books must not be null
     * MODIFIES: books.txt
     * EFFECTS: Will write a list of books to the file books.txt. which will overwrite the data previously
     */
    public void saveBooks(List<Book> books)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(bookFile)))
        {
            for (Book book: books)
            {
                writer.write(book.getTitle() + ", " + book.getPrice());
                writer.newLine();
            }
        }
        catch(IOException e)
        {
            System.out.println("Error. Can not save book. " + e.getMessage());
        }
    }
    
    /**
     * REQUIRES: customers.txt must exist
     * MODIFIES: nothing
     * EFFECTS: Will load customers from the file customers.txt and return it as a list. Then it will update their points and status.
     */
    public List<Customer> loadCustomers()
    {
        List<Customer> customers = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(customerFile)))
        {
            String i;
            while((i = reader.readLine()) != null)
            {
                String[] data = i.split(",");
                String username = data[0];
                String password = data[1];
                int points = Integer.parseInt(data[2].trim());
                
                Customer customer = new Customer(username, password);
                customer.addPoints(points);
                customer.getStatus().updatePoints(customer, 0);
                customers.add(customer);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error. Can not load customer. " + e.getMessage());
        }
        return customers;
    }
    
    /**
     * REQUIRES: customers must not be null
     * MODIFIES: customers.txt
     * EFFECTS: Will write a list of customers to the file customers.txt, which will overwrite the data previously
     */
    public void saveCustomers(List<Customer> customers)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(customerFile)))
        {
            for (Customer customer: customers)
            {
                writer.write(customer.getUsername() + "," + customer.getPassword() + "," + customer.getPoints());
                writer.newLine();
            }
        }
        catch(IOException e)
        {
            System.out.println("Error. Can not save customer: " + e.getMessage());
        }
    }
}

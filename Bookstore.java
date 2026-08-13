package coe528.project;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Henry Phan
 */
public class Bookstore 
{
    private List<Book> books;
    private List<Customer> customers;
    
    /**
     * MODIFIES: this
     * EFFECTS: Bookstore object constructor that has a new array list that is empty
     */
    public Bookstore()
    {
        books = new ArrayList<>();
        customers = new ArrayList<>();
    }
    
    /**
     * REQUIRES: book must not be null
     * MODIFIES: books
     * EFFECTS: Will add the book into the list of books
     */
    public void addBook(Book book)
    {
        books.add(book);
    }
    
    /**
     * REQUIRES: book must exist in the list
     * MODIFIES: books
     * EFFECTS: Will remove the book from the list of books
     */
    public void removeBook(Book book)
    {
        books.remove(book);
    }
    
    /**
     * REQUIRES: customer must not be null
     * MODIFIES: customers
     * EFFECTS: Will add the customer into the customer list
     */
    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
    
    /**
     * REQUIRES: customer must exist in the list
     * MODIFIES: customers
     * EFFECTS: Will remove the customer from the customer list
     */
    public void removeCustomer(Customer customer)
    {
        customers.remove(customer);
    }
    
    /**
     * EFFECTS: Will return the list of customers that is in the bookstore system
     */
    public List<Customer> getCustomer()
    {
        return customers;
    }
    
    /**
     * EFFECTS: Will return the list of books that is in the bookstore system
     */
    public List<Book> getBooks()
    {
        return this.books;
    }
}

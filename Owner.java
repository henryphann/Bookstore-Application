package coe528.project;

/**
 *
 * @author Henry Phan
 */
public class Owner extends User 
{
    /**
     * MODIFIES: this
     * EFFECTS: Owner object constructor with a given username ("admin") and password ("admin")
     */
    public Owner()
    {
        super("admin", "admin");
    }
    
    /**
     * REQUIRES: store and book must not be null
     * MODIFIES: store
     * EFFECTS: Will add a book to the bookstore's book list
     */
    public void addBook(Bookstore store, Book book) 
    {
        if (store != null && book != null) 
        {
            store.addBook(book);
        }
    }

    /**
     * REQUIRES: store and book must not be null
     * MODIFIES: store
     * EFFECTS: Will remove a book from the bookstore's book list
     */
    public void removeBook(Bookstore store, Book book)
    {
        if (store != null && book != null) 
        {
            store.removeBook(book);
        }
    }
    
    /**
     * REQUIRES: store and customer must not be null
     * MODIFIES: store
     * EFFECTS: Will register a customer to the bookstore's customer list
     */
    public void registerCustomer(Bookstore store, Customer customer)
    {
        if(store != null && customer != null)
        {
            store.addCustomer(customer);
        }
    }
    
    /**
     * REQUIRES: store and customer must not be null
     * MODIFIES: store
     * EFFECTS: Will delete a customer from the bookstore's customer list
     */
    public void deleteCustomer(Bookstore store, Customer customer)
    {
        if(store != null && customer != null)
        {
            store.removeCustomer(customer);
        }
    } 
}

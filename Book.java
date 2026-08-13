package coe528.project;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Henry Phan
 */
public class Book 
{
    private final String title;
    private final String price;
    private CheckBox select;
    
    /**
     * REQUIRES: title and price must not be null
     * MODIFIES: this
     * EFFECTS: Book object constructor that has a book title and book price
     */
    public Book(String title, String price)
    {
        this.title = title;
        this.price = price;
        this.select = new CheckBox();
    }
    
    /**
     * EFFECTS: Will return the title of the specific book
     */
    public String getTitle()
    {
        return this.title;
    }
    
    /**
     * EFFECTS: Will return the price of the specific book
     */
    public String getPrice()
    {
        return this.price;
    }
    
    /**
     * EFFECTS: Will return the CheckBox of the specific book
     */
    public CheckBox getSelect()
    {
        return this.select;
    }
    
    /**
     * REQUIRES: select must not be null.
     * MODIFIES: this.select
     * EFFECTS: Will set the checkbox associated with the book to the given Checkbox object
     */
    public void setSelect(CheckBox select)
    {
        this.select = select;
    }
}

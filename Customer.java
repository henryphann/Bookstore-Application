package coe528.project;

import java.util.List;

/**
 *
 * @author Henry Phan
 */
public class Customer extends User
{
    private String points;
    private CustomerState status;
    
    /**
     * REQUIRES: username and password must not be null
     * MODIFIES: this
     * EFFECTS: Customer object constructor that has a username and password, starts with 0 points and is a silver member by default
     */
    public Customer(String username, String password)
    {
        super(username, password);
        this.status = new SilverState();
        this.points = "0";
    }
    
    /**
     * EFFECTS: Will return the amount of points that the customer has
     */
    public int getPoints()
    {
        return Integer.parseInt(this.points);
    }
    
    /**
     * REQUIRES: points must be either 0 or greater
     * MODIFIES: this.points
     * EFFECTS: Will add the specific number of points to the customers account
     */
    public void addPoints(int points)
    {
        this.points = Integer.toString(Integer.parseInt(this.points) + points);
    }
    
    /**
     * EFFECTS: Will return the current status of the customer, that is either silver or gold
     */
    public CustomerState getStatus()
    {
        return this.status;
    }
    
    /**
     * REQUIRES: bookList must not be null and redeemPoints must be either true or false
     * MODIFIES: this.points and this.status
     * EFFECTS: This will process the purchase depending on the choice and will update the customer's points and status once it has been bought. Returns total of the transaction.
     */
    public double buyBook(List<Book> bookList, boolean redeemPoints)
    {
        double total = 0.0; 
        for(Book book : bookList)
        {
            total += Double.parseDouble(book.getPrice());
        }
        
        if(redeemPoints)
        {
            int redeemable = Integer.parseInt(points) / 100;
            int discount = Math.min(redeemable, (int)total);
            total -= discount;
            points = Integer.toString(Integer.parseInt(points) - (discount * 100));
        }
        
        total = total - (total * status.getDiscount());
        
        if(total < 0)
        {
            total = 0;
        }
        
        int earnedPoints = (int)(total * 10);
        status.updatePoints(this, earnedPoints);
        
        return total;
    }
    
    /**
     * REQUIRES: The state must not be null
     * MODIFIES: this.status
     * EFFECTS: This will update the customer's status to whichever state it needs to be changed to
     */
    public void updateStatus(CustomerState state)
    {
        this.status = state;
    }
}

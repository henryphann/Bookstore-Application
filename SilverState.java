package coe528.project;

/**
 *
 * @author Henry Phan
 */
public class SilverState implements CustomerState
{
    /**
     * EFFECTS: Will return the discount rate that will be applied to a customer who has a silver state
     */
    @Override
    public double getDiscount()
    {
        return 0.0;
    }
    
    /**
     * REQUIRES: Customer must not be null and points must be either 0 or greater
     * MODIFIES: customer
     * EFFECTS: Will update the points by adding the points to the customers account. Then, it will check if it is eligible to be a gold member and if they are, they will update their status to gold.
     */
    @Override
    public void updatePoints(Customer customer, int points)
    {
        customer.addPoints(points);
        if(customer.getPoints() >= 1000)
        {
            customer.updateStatus(new GoldState());
        }
    }
    
    // String representation to display state
    @Override
    public String toString()
    {
        return "S";
    }
}

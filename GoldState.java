package coe528.project;

/**
 *
 * @author Henry Phan
 */
public class GoldState implements CustomerState
{
    /**
     * EFFECTS: Will return the discount rate that will be applied to a customer who has a gold state
     */
    @Override
    public double getDiscount()
    {
        return 0.10;
    }
    
    /**
     * REQUIRES: Customer must not be null and points must be either 0 or greater
     * MODIFIES: customer
     * EFFECTS: Will update the points by adding the points to the customers account. Then, it will check if its points has went below the minimum amount to be a gold member. 
     * If it turns out that the customer is below the minimum requirement, it will update its status and downgrade to silver
     */
    @Override
    public void updatePoints(Customer customer, int points)
    {
        customer.addPoints(points);
        if(customer.getPoints() < 1000)
        {
            customer.updateStatus(new SilverState());
        }
    }
    
    // String representation to display state
    @Override
    public String toString()
    {
        return "G";
    }
}

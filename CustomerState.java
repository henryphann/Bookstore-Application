package coe528.project;

/**
 *
 * @author Henry Phan
 */
public interface CustomerState 
{
    /**
     * EFFECTS: Will return the discount rate if applicable depending on customers status state
     */
    double getDiscount();
    
    /**
     * REQUIRES: customer must not be null and points most be either 0 or greater
     * MODIFIES: customer
     * EFFECTS: Will add the number of points to the customer and update the customers state when it has enough points
     */
    void updatePoints(Customer customer, int points);
    
    // String representation to display state
    String toString();
}

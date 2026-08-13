package coe528.project;

/**
 *
 * @author Henry Phan 
 */
public abstract class User 
{
    protected String username;
    protected String password;
    
    /**
     * REQUIRES: username and password must not be null
     * MODIFIES: this
     * EFFECTS: User object will have a username and password after initialized
     */
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    /**
     * REQUIRES: The username and password must not be null.
     * MODIFIES: this
     * EFFECTS: Returns true when the username and password matches their credentials and false otherwise
     */
    public boolean login(String username, String password)
    {
        return(this.username.equals(username) && this.password.equals(password));
    }
    
    /**
     * EFFECTS: Return the user's username
     */
    public String getUsername()
    {
        return this.username;
    }
    
    /**
     * EFFECTS: Return the user's password
     */
    public String getPassword()
    {
        return this.password;
    }
}

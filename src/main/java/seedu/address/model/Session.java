package seedu.address.model;

/**
 * Represents User's Session.
 */
public class Session {

    private boolean isLoggedIn;
    private String username;
    private int securityLevel;

    public Session(){
        resetSession();
    }

    /**
     * Sets session details for user upon successful login
     */
    public void login(String username, int securityLevel){
        this.isLoggedIn = true;
        this.username = username;
        this.securityLevel = securityLevel;
    }

    public void logout(){
        resetSession();
    }

    private void resetSession(){
        isLoggedIn = false;
        username = "";
        securityLevel = 0;
    }

    private String getUsername() {
        return username;
    }

    private int getSecurityLevel() {
        return securityLevel;
    }

}

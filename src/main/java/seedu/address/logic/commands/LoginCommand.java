package seedu.address.logic.commands;

/**
 * Attempts to log in user with given Username and Password
 */
public class LoginCommand extends Command {

    public static final String COMMAND_WORD = "login";
    public static final String ACCEPTED_USERNAME = "prisonwarden99";
    public static final String ACCEPTED_PASSWORD = "password1";
    public static final int SECURITY_LEVEL = 5;

    public static final String MESSAGE_LOGIN_FAILURE = "Login failed. Username and/or Password entered incorrectly.";
    public static final String MESSAGE_LOGIN_SUCCESS = "Login Success";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Logs in with your username and password to gain "
            + "access to the Prison Book.\n"
            + "Parameters: user/YOUR_USERNAME pw/YOUR_PASSWORD...\n"
            + "Example: " + COMMAND_WORD + " user/prisonwarden99 pw/password1";


    private final String username;
    private final String password;

    public LoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public CommandResult execute() {
        if (attemptLogin(username, password) <= -1) {
            return new CommandResult("Login Failed. Username entered: " + username + " Pw entered: " + password);
        } else {
            model.login(username, attemptLogin(username, password));
            return new CommandResult(MESSAGE_LOGIN_SUCCESS);
        }
    }

    /**
     *   Returns -1 if username or password is wrong.
     *   Returns the user's security level if username and password is verified against database.
     */
    private int attemptLogin (String username, String password) {
        if (!isValidUser(username, password)) {
            return -1;
        } else {
            return getSecurityLevel(username);
        }
    }

    /**
     *   Check if username and password is verified against database.
     */
    private boolean isValidUser(String username, String password) {
        if (username.equals(ACCEPTED_USERNAME) && password.equals(ACCEPTED_PASSWORD)) {
            return true;
        } else {
            return false;
        }
    }

    private int getSecurityLevel(String username) {
        if (username.equals(ACCEPTED_USERNAME)) {
            return SECURITY_LEVEL;
        } else {
            return -1;
        }
    }


    /*
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && this.predicate.equals(((FindCommand) other).predicate)); // state check
    }
    */

}

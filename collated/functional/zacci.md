# zacci
###### \java\seedu\address\logic\commands\AddUserCommand.java
``` java
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.user.User;
import seedu.address.model.user.exceptions.UserAlreadyExistsException;

/**
 * Adds a new user to the PrisonBook
 */
public class AddUserCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "adduser";
    public static final String COMMAND_ALIAS = "au";
    public static final int MIN_SECURITY_LEVEL = 3;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new user to the PrisonBook.\n"
            + "Parameters: user/NEW_USERNAME pw/NEW_PASSWORD sl/SECURITY_LEVEL (integer from 0 tp 3)...\n"
            + "Example: " + COMMAND_WORD + " user/newuser1 pw/password1 sl/2";

    public static final String MESSAGE_ADD_USER_SUCCESS = "New user %s added to PrisonBook";
    public static final String MESSAGE_ALREADY_EXISTING_USER = "%s is already a user in PrisonBook";
    private final String username;
    private final String password;
    private final int securityLevel;

    private User userToAdd;

    /**
     * @param username of the new user to be added to the PrisonBook
     * @param password of the new user to be added to the PrisonBook
     * @param securityLevel of the new user to be added to the PrisonBook
     */
    public AddUserCommand(String username, String password, int securityLevel) {
        requireNonNull(username);
        requireNonNull(password);
        this.username = username;
        this.password = password;
        this.securityLevel = securityLevel;
        userToAdd = new User(username, password, securityLevel);
    }

    @Override
    /**
     * Returns the MIN_SECURITY_LEVEL to caller
     */
    public int getMinSecurityLevel() {
        return MIN_SECURITY_LEVEL;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        requireNonNull(model);
        requireNonNull(userToAdd);
        try {
            model.addUser(userToAdd);
        } catch (UserAlreadyExistsException uaee) {
            throw new CommandException(String.format(MESSAGE_ALREADY_EXISTING_USER, username));
        }
        return new CommandResult(String.format(MESSAGE_ADD_USER_SUCCESS, username));
    }

    public User getUserToAdd() {
        return userToAdd;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddUserCommand // instanceof handles nulls
                && Objects.equals(this.userToAdd, ((AddUserCommand) other).userToAdd));
    }
}
```
###### \java\seedu\address\logic\parser\AddressBookParser.java
``` java
        case LoginCommand.COMMAND_WORD:
            return new LoginCommandParser().parse(arguments);

        case LogoutCommand.COMMAND_WORD:
            return new LogoutCommand();

        case CheckStatusCommand.COMMAND_WORD:
            return new CheckStatusCommand();

        case ShowUsersCommand.COMMAND_WORD:
            return new ShowUsersCommand();

        case AddUserCommand.COMMAND_WORD:
            return new AddUserCommandParser().parse(arguments);
```
###### \java\seedu\address\model\AddressBook.java
``` java
    //// user-level operations

    /**
     *
     * @param u is the user to add to the HashMap
     */
    public void addUser(User u) {
        users.addUser(u);
    }

    /**
     * Attempt to log in with the entered username and password
     */
    public int attemptLogin(String username, String password) {
        return users.verify(username, password);
    }

    @Override
    public ObservableList<User> getUserList() {
        return users.getUserList();
    }

    public void setUsers(ObservableList<User> users) {
        this.users.setUsers(users);
    }

```
###### \java\seedu\address\model\Model.java
``` java
    /** Returns the session */
    Session getSession();

    /** Clears existing session*/
    void logout();

    /** Logs in verified user and assigns security level to the session */
    void login(String username, int securityLevel);

    /** Attempts to login user with entered username and password */
    boolean attemptLogin(String username, String password);

    /** Returns Session details to caller */
    String getSessionDetails();

    /** Returns Session security level to caller */
    int getSecurityLevel();

    /** Adds given user to the PrisonBook */
    void addUser(User user) throws UserAlreadyExistsException;
```
###### \java\seedu\address\model\ModelManager.java
``` java
    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void logout() {
        session.logout();
        logger.info("User logged out");
    }

    @Override
    public void login(String username, int securityLevel) {
        session.login(username, securityLevel);
        logger.info("User logged in with: u/" + username + " slevel/" + securityLevel);
    }

    @Override
    public boolean attemptLogin(String username, String password) {
        int securityLevel = addressBook.attemptLogin(username, password);
        if (securityLevel < 0) {
            return false;
        } else {
            login(username, securityLevel);
            return true;
        }
    }

    @Override
    public String getSessionDetails() {
        return ("Username: " + session.getUsername() + " Security Level: " + session.getSecurityLevel());
    }

    @Override
    public int getSecurityLevel() {
        return session.getSecurityLevel();
    }

    @Override
    public void addUser(User userToAdd) {
        addressBook.addUser(userToAdd);
        indicateAddressBookChanged();
    }
```
###### \java\seedu\address\model\ReadOnlyAddressBook.java
``` java
    /**
     * Returns an unmodifiable view of the users list.
     * This list will not contain any duplicate users.
     */
    ObservableList<User> getUserList();
```
###### \java\seedu\address\storage\XmlAdaptedUser.java
``` java
package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.model.user.User;

/**
 * JAXB-friendly version of the Person.
 */
public class XmlAdaptedUser {

    @XmlElement(required = true)
    private String username;
    @XmlElement(required = true)
    private String password;
    @XmlElement(required = true)
    private int securityLevel;

    /**
     * Constructs an XmlAdaptedUser.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedUser() {}

    /**
     * Constructs an {@code XmlAdaptedPerson} with the given person details.
     */
    public XmlAdaptedUser(String username, String password, int securityLevel) {
        this.username = username;
        this.password = password;
        this.securityLevel = securityLevel;
    }

    /**
     * Converts a given User into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedUser
     */
    public XmlAdaptedUser(User source) {
        username = source.getUsername();
        password = source.getPassword();
        securityLevel = source.getSecurityLevel();
    }

    /**
     * Converts this jaxb-friendly adapted user object into the model's User object.
     *
     */
    public User toModelType() {
        return new User(username, password, securityLevel);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlAdaptedUser)) {
            return false;
        }

        XmlAdaptedUser otherUser = (XmlAdaptedUser) other;
        return this.username.equals(((XmlAdaptedUser) other).username)
                && this.password.equals(((XmlAdaptedUser) other).password)
                && this.securityLevel == (((XmlAdaptedUser) other).securityLevel);
    }
}
```

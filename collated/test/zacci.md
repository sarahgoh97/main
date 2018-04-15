# zacci
###### \java\seedu\address\logic\commands\AddCommandTest.java
``` java
        @Override
        public void login(String username, int securityLevel){};

        @Override
        public void logout(){};

        @Override
        public Session getSession() {
            return new Session();
        };

        @Override
        public String getSessionDetails() {
            return "";
        }

        @Override
        public boolean attemptLogin(String username, String password) {
            return true;
        }

        @Override
        public int getSecurityLevel() {
            return 5;
        }

        @Override
        public void addUser(User user) {};

        @Override
        public void deleteUser(String user) {};

        @Override
        public boolean checkIsLoggedIn() {
            return true;
        };
```
###### \java\seedu\address\logic\commands\AddUserCommandTest.java
``` java
package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddUserCommand.MESSAGE_ALREADY_EXISTING_USER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalUsers.PRISONGUARD;
import static seedu.address.testutil.TypicalUsers.VALID_MINIMUM_SECURITY_LEVEL;
import static seedu.address.testutil.TypicalUsers.VALID_PASSWORD;
import static seedu.address.testutil.TypicalUsers.VALID_USERNAME;
import static seedu.address.testutil.TypicalUsers.getTypicalUserDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.user.User;

public class AddUserCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalUserDatabase(), new UserPrefs());

    public AddUserCommandTest() {}

    @Test
    public void execute_validUsernameValidPasswordValidSecurityLevel_success() throws Exception {
        AddUserCommand addUserCommand = prepareCommand(VALID_USERNAME, VALID_PASSWORD,
                VALID_MINIMUM_SECURITY_LEVEL);

        String expectedMessage = String.format(AddUserCommand.MESSAGE_ADD_USER_SUCCESS, VALID_USERNAME);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        User userToAdd = new User(VALID_USERNAME, VALID_PASSWORD, VALID_MINIMUM_SECURITY_LEVEL);
        expectedModel.addUser(userToAdd);

        assertCommandSuccess(addUserCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_userAlreadyExists_failure() {
        AddUserCommand addUserCommand = prepareCommand(PRISONGUARD.getUsername(), PRISONGUARD.getPassword(),
                PRISONGUARD.getSecurityLevel());

        assertCommandFailure(addUserCommand, model,
                String.format(MESSAGE_ALREADY_EXISTING_USER, PRISONGUARD.getUsername()));

    }

    private AddUserCommand prepareCommand(String username, String password, int securityLevel) {
        AddUserCommand addUserCommand = new AddUserCommand(username, password, securityLevel);
        addUserCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return addUserCommand;
    }
}
```
###### \java\seedu\address\logic\commands\DeleteUserCommandTest.java
``` java
package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalUsers.PRISONLEADER;
import static seedu.address.testutil.TypicalUsers.PRISONWARDEN;
import static seedu.address.testutil.TypicalUsers.VALID_USERNAME;
import static seedu.address.testutil.TypicalUsers.getTypicalUserDatabase;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.user.exceptions.CannotDeleteSelfException;
import seedu.address.model.user.exceptions.NotEnoughAuthorityToDeleteException;
import seedu.address.model.user.exceptions.UserDoesNotExistException;

public class DeleteUserCommandTest {

    private Model model = new ModelManager(getTypicalUserDatabase(), new UserPrefs());

    public DeleteUserCommandTest() {}

    @Test
    public void execute_deleteUserOfHigherSecurityLevel_failure() {

        ModelManager tempModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        tempModel.attemptLogin(PRISONLEADER.getUsername(), PRISONLEADER.getPassword());
        DeleteUserCommand deleteUserCommand = prepareCommand(PRISONWARDEN.getUsername(), tempModel);

        assertCommandFailure(deleteUserCommand, model, NotEnoughAuthorityToDeleteException.MESSAGE);
    }

    @Test
    public void execute_deleteSelf_failure() {

        ModelManager tempModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        tempModel.attemptLogin(PRISONLEADER.getUsername(), PRISONLEADER.getPassword());
        DeleteUserCommand deleteUserCommand = prepareCommand(PRISONLEADER.getUsername(), tempModel);

        assertCommandFailure(deleteUserCommand, model, CannotDeleteSelfException.MESSAGE);
    }

    @Test
    public void execute_deleteNonExistentUser_failure() {

        ModelManager tempModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        tempModel.attemptLogin(PRISONLEADER.getUsername(), PRISONLEADER.getPassword());
        DeleteUserCommand deleteUserCommand = prepareCommand(VALID_USERNAME, tempModel);

        assertCommandFailure(deleteUserCommand, model, UserDoesNotExistException.MESSAGE);
    }

    private DeleteUserCommand prepareCommand(String username) {
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(username);
        deleteUserCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return deleteUserCommand;
    }

    private DeleteUserCommand prepareCommand(String username, Model tempModel) {
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand(username);
        deleteUserCommand.setData(tempModel, new CommandHistory(), new UndoRedoStack());
        return deleteUserCommand;
    }
}
```
###### \java\seedu\address\logic\commands\LoginCommandTest.java
``` java
package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalUsers.PRISONGUARD;
import static seedu.address.testutil.TypicalUsers.VALID_PASSWORD;
import static seedu.address.testutil.TypicalUsers.VALID_USERNAME;
import static seedu.address.testutil.TypicalUsers.getTypicalUserDatabase;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class LoginCommandTest {

    private Model model = new ModelManager(getTypicalUserDatabase(), new UserPrefs());

    public LoginCommandTest() {}

    @Test
    public void execute_validUsernameValidPassword_success() {
        LoginCommand loginCommand = prepareCommand(PRISONGUARD.getUsername(), PRISONGUARD.getPassword());

        String expectedMessage = LoginCommand.MESSAGE_LOGIN_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.attemptLogin(PRISONGUARD.getUsername(), PRISONGUARD.getPassword());

        assertCommandSuccess(loginCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_userDoesNotExist_failure() {

        LoginCommand loginCommand = prepareCommand(VALID_USERNAME, VALID_PASSWORD);

        assertCommandFailure(loginCommand, model, LoginCommand.MESSAGE_LOGIN_FAILURE);
    }

    @Test
    public void execute_userAlreadyLoggedIn_failure() {

        ModelManager tempModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        tempModel.attemptLogin(PRISONGUARD.getUsername(), PRISONGUARD.getPassword());
        LoginCommand loginCommand = prepareCommand(VALID_USERNAME, VALID_PASSWORD, tempModel);

        assertCommandFailure(loginCommand, model, LoginCommand.MESSAGE_ALREADY_LOGGED_IN);
    }



    private LoginCommand prepareCommand(String username, String password) {
        LoginCommand loginCommand = new LoginCommand(username, password);
        loginCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return loginCommand;
    }

    private LoginCommand prepareCommand(String username, String password, Model tempModel) {
        LoginCommand loginCommand = new LoginCommand(username, password);
        loginCommand.setData(tempModel, new CommandHistory(), new UndoRedoStack());
        return loginCommand;
    }
}
```
###### \java\seedu\address\logic\commands\LogoutCommandTest.java
``` java
package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalUsers.PRISONGUARD;
import static seedu.address.testutil.TypicalUsers.getTypicalUserDatabase;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class LogoutCommandTest {

    private Model model = new ModelManager(getTypicalUserDatabase(), new UserPrefs());

    public LogoutCommandTest() {}

    @Test
    public void execute_validSessionOngoing_success() {

        ModelManager tempModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        tempModel.attemptLogin(PRISONGUARD.getUsername(), PRISONGUARD.getPassword());
        LogoutCommand logoutCommand = prepareCommand(tempModel);

        String expectedMessage = LogoutCommand.MESSAGE_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(logoutCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_userNotLoggedIn_failure() {

        LogoutCommand logoutCommand = prepareCommand();

        assertCommandFailure(logoutCommand, model, LogoutCommand.MESSAGE_USER_NOT_LOGGED_IN);
    }

    private LogoutCommand prepareCommand() {
        LogoutCommand logoutCommand = new LogoutCommand();
        logoutCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return logoutCommand;
    }

    private LogoutCommand prepareCommand(Model tempModel) {
        LogoutCommand logoutCommand = new LogoutCommand();
        logoutCommand.setData(tempModel, new CommandHistory(), new UndoRedoStack());
        return logoutCommand;
    }
}
```
###### \java\seedu\address\logic\LogicManagerTest.java
``` java
    public LogicManagerTest() {
        model.login("maxSecurityLevelUser", 999);
    }
```
###### \java\seedu\address\TestApp.java
``` java
    public Model getModel() {
        Model copy = new ModelManager((model.getAddressBook()), new UserPrefs());
        model.login("maxSecurityLevelUser", 999);
        ModelHelper.setFilteredList(copy, model.getFilteredPersonList());
        return copy;
    }

    //@author sarahgoh97
    public Model getNewModel() {
        Model copy = new ModelManager((model.getAddressBook()), new UserPrefs());
        model.login("maxSecurityLevelUser", 999);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        ModelHelper.setFilteredList(copy, model.getFilteredPersonList());
        return copy;
    }
```
###### \java\seedu\address\testutil\TypicalUsers.java
``` java
package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.user.User;
import seedu.address.model.user.exceptions.UserAlreadyExistsException;

/**
 * A utility class containing a list of Users, usernames, passwords and security levels to be used in tests.
 */
public class TypicalUsers {
    public static final String VALID_USERNAME = "prisonwarden99";
    public static final String VALID_PASSWORD = "password99";
    public static final int VALID_MINIMUM_SECURITY_LEVEL = 1;
    public static final int VALID_MAXIMUM_SECURITY_LEVEL = 3;
    public static final String INVALID_USERNAME = "prison warden"; //cannot have spaces
    public static final String INVALID_PASSWORD = "password1("; //cannot have "("
    public static final int INVALID_SECURITY_LEVEL = 4;

    public static final User PRISONWARDEN = new User("prisonwarden", "password3", 3);
    public static final User PRISONLEADER = new User("prisonleader", "password2", 2);
    public static final User PRISONGUARD = new User("prisonguard", "password1", 1);

    //Manually Added
    public static final User PRISONWARDEN2 = new User("prisonwarden2", "password3", 3);
    public static final User PRISONLEADER2 = new User("prisonleader2", "password2", 2);
    public static final User PRISONGUARD2 = new User("prisonguard2", "password1", 1);


    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalUserDatabase() {
        AddressBook ab = new AddressBook();
        for (User user : getTypicalUsers()) {
            try {
                ab.addUser(user);
            } catch (UserAlreadyExistsException e) {
                throw new AssertionError("not possible");
            }
        }
        return ab;
    }

    public static List<User> getTypicalUsers() {
        return new ArrayList<User>(Arrays.asList(PRISONWARDEN, PRISONLEADER, PRISONGUARD));
    }
}
```

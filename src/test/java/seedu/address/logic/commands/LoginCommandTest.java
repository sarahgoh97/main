package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalUsers.getTypicalUserDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class LoginCommandTest {

    private Model model = new ModelManager(getTypicalUserDatabase(), new UserPrefs());

    public LoginCommandTest() {}

    @Test
    public void execute_validUsernameValidPasswordValidSecurityLevel_success() throws Exception {
        AddUserCommand addUserCommand = prepareCommand(VALID_USERNAME, VALID_PASSWORD,
                VALID_MINIMUM_SECURITY_LEVEL);

        String expectedMessage = String.format(AddUserCommand.MESSAGE_ADD_USER_SUCCESS, VALID_USERNAME);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        User userToAdd = new User(VALID_USERNAME,VALID_PASSWORD,VALID_MINIMUM_SECURITY_LEVEL);
        expectedModel.addUser(userToAdd);

        assertCommandSuccess(addUserCommand, model, expectedMessage, expectedModel);
    }

    private LoginCommand prepareCommand(String username, String password) {
        LoginCommand loginCommand = new LoginCommand(username, password);
        loginCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return loginCommand;
    }
}

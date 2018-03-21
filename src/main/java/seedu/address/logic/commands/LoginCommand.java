package seedu.address.logic.commands;


public class LoginCommand extends Command {

    public static final String COMMAND_WORD = "login";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Logs in with your username and password to gain "
            + "access to the Prison Book.\n"
            + "Parameters: user/YOUR_USERNAME pw/YOUR_PASSWORD...\n"
            + "Example: " + COMMAND_WORD + " user/prisonwarden99 pw/password1";


    public LoginCommand() {
    }


    @Override
    public CommandResult execute() {
        return new CommandResult("Login attempted");
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
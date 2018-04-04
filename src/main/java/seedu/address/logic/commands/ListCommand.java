package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_ALIAS = "l";
    public static final int minSecurityLevel = 1;

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    @Override
    /**
     * Returns the minSecurityLevel to caller
     */
    public int getMinSecurityLevel() {
        return minSecurityLevel;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

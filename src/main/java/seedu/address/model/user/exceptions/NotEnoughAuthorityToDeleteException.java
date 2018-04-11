package seedu.address.model.user.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Signals that the username that is being added already exists.
 */
public class NotEnoughAuthorityToDeleteException extends IllegalValueException {
    public NotEnoughAuthorityToDeleteException() {
        super("You do not have the authority to delete this user");
    }
}

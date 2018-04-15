//@@author philos22
package seedu.address.model.person;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (person.getTags() == null) {
            return false;
        }
        // Making a string of all tags
        Iterator tagIteration = person.getTags().iterator();
        StringBuilder strBuild = new StringBuilder();
        strBuild.append(tagIteration.next());
        while (tagIteration.hasNext()) {
            strBuild.append(" " + tagIteration.next());
        }
        String tagList = strBuild.toString().replace("[", "").replace("]", "");

        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tagList, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && this.keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}

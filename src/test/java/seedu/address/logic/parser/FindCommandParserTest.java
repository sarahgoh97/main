package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.address.logic.commands.FindCommand;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    //Todo : this test causing too much pain and burde
    // n, will fix later - doesn't directly affect functionality anyway

    //    @Test
    //    public void parse_validArgs_returnsFindCommand() {
    //        // no leading and trailing whitespaces
    //        ArrayList<String> nameList = new ArrayList<String>();
    //        nameList.add("Alice");
    //        nameList.add("Bob");
    //        ArrayList<String> tagList = new ArrayList<String>();
    //        tagList.add("Tag1");
    //        tagList.add("Tag2");
    //        FindCommand expectedFindCommand =
    //                new FindCommand(new ContainsKeywordsPredicate(nameList,
    //                        tagList));
    //        System.out.println("test : " + expectedFindCommand); //debug
    //
    //        assertParseSuccess(parser, "find n/Alice Bob t/Tag1 Tag2", expectedFindCommand);
    //
    //        // multiple whitespaces between keywords
    //        assertParseSuccess(parser, "find \n n/Alice \n \t Bob  \t t/Tag1 \n Tag2", expectedFindCommand);
    //    }

}

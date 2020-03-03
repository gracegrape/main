package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliPrefix.PEOPLE_COMMAND_TYPE;
import static seedu.address.logic.parser.CliPrefix.WALLET_COMMAND_TYPE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDebts.TEXTBOOK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.global.ExitCommand;
import seedu.address.logic.commands.global.HelpCommand;
import seedu.address.logic.commands.people.PeopleAddCommand;
import seedu.address.logic.commands.people.PeopleClearCommand;
import seedu.address.logic.commands.people.PeopleDeleteCommand;
import seedu.address.logic.commands.people.PeopleEditCommand;
import seedu.address.logic.commands.people.PeopleEditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.people.PeopleFindCommand;
import seedu.address.logic.commands.people.PeopleListCommand;
import seedu.address.logic.commands.people.PeopleOweCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class SharkieParserTest {

    private final SharkieParser parser = new SharkieParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        PeopleAddCommand command = (PeopleAddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new PeopleAddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(PEOPLE_COMMAND_TYPE + " " + PeopleClearCommand.COMMAND_WORD)
                instanceof PeopleClearCommand);
        assertTrue(parser.parseCommand(PEOPLE_COMMAND_TYPE + " " + PeopleClearCommand.COMMAND_WORD + " 3")
                instanceof PeopleClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        PeopleDeleteCommand command = (PeopleDeleteCommand) parser.parseCommand(PEOPLE_COMMAND_TYPE + " "
                + PeopleDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new PeopleDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        PeopleEditCommand command = (PeopleEditCommand) parser.parseCommand(PEOPLE_COMMAND_TYPE + " "
                + PeopleEditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new PeopleEditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD)
                instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        PeopleFindCommand command = (PeopleFindCommand) parser.parseCommand(
                PEOPLE_COMMAND_TYPE + " " + PeopleFindCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new PeopleFindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD)
                instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(PEOPLE_COMMAND_TYPE + " " + PeopleListCommand.COMMAND_WORD)
                instanceof PeopleListCommand);
        assertTrue(parser.parseCommand(PEOPLE_COMMAND_TYPE + " " + PeopleListCommand.COMMAND_WORD + " 3")
                instanceof PeopleListCommand);
    }

    @Test
    public void parseCommand_owe() throws Exception {
        PeopleOweCommand command = (PeopleOweCommand) parser.parseCommand(PEOPLE_COMMAND_TYPE + " "
                + PeopleOweCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                + PersonUtil.getDebtDescription(TEXTBOOK));
        assertEquals(new PeopleOweCommand(INDEX_FIRST_PERSON, TEXTBOOK), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_globalCommands_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser
                .parseCommand(ExitCommand.COMMAND_WORD + " 3"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser
                .parseCommand(HelpCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_prefixOnly_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand(
                PEOPLE_COMMAND_TYPE));

        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand(
                WALLET_COMMAND_TYPE));
    }

    @Test
    public void parseCommand_commandWithoutPrefix_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand(
                PeopleListCommand.COMMAND_WORD));
    }
}

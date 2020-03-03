package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliPrefix.GLOBAL_COMMAND_TYPE;
import static seedu.address.logic.parser.CliPrefix.PEOPLE_COMMAND_TYPE;
import static seedu.address.logic.parser.CliPrefix.WALLET_COMMAND_TYPE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.global.ExitCommand;
import seedu.address.logic.commands.global.HelpCommand;
import seedu.address.logic.commands.people.PeopleAddCommand;
import seedu.address.logic.commands.people.PeopleClearCommand;
import seedu.address.logic.commands.people.PeopleDeleteCommand;
import seedu.address.logic.commands.people.PeopleEditCommand;
import seedu.address.logic.commands.people.PeopleFindCommand;
import seedu.address.logic.commands.people.PeopleListCommand;
import seedu.address.logic.commands.people.PeopleOweCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.people.PeopleAddCommandParser;
import seedu.address.logic.parser.people.PeopleDeleteCommandParser;
import seedu.address.logic.parser.people.PeopleEditCommandParser;
import seedu.address.logic.parser.people.PeopleFindCommandParser;
import seedu.address.logic.parser.people.PeopleOweCommandParser;

/**
 * Parses user input.
 */
public class SharkieParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandType>\\S+ )?(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandType = matcher.group("commandType");
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        if (commandType == GLOBAL_COMMAND_TYPE) {
            return getGlobalCommand(commandWord, arguments);
        }

        switch (commandType.trim()) {
        case PEOPLE_COMMAND_TYPE:
            return getPeopleCommand(commandWord, arguments);
        case WALLET_COMMAND_TYPE:
            return getWalletCommand(commandWord, arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

    }

    private Command getPeopleCommand(String commandWord, String arguments) throws ParseException {
        if (commandWord == null) {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

        switch (commandWord) {
        case PeopleAddCommand.COMMAND_WORD:
            return new PeopleAddCommandParser().parse(arguments);

        case PeopleEditCommand.COMMAND_WORD:
            return new PeopleEditCommandParser().parse(arguments);

        case PeopleDeleteCommand.COMMAND_WORD:
            return new PeopleDeleteCommandParser().parse(arguments);

        case PeopleClearCommand.COMMAND_WORD:
            return new PeopleClearCommand();

        case PeopleFindCommand.COMMAND_WORD:
            return new PeopleFindCommandParser().parse(arguments);

        case PeopleListCommand.COMMAND_WORD:
            return new PeopleListCommand();

        case PeopleOweCommand.COMMAND_WORD:
            return new PeopleOweCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command getWalletCommand(String commandWord, String arguments) throws ParseException {
        if (commandWord == null) {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

        switch (commandWord) {

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command getGlobalCommand(String commandWord, String arguments) throws ParseException {
        if (commandWord == null) {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}

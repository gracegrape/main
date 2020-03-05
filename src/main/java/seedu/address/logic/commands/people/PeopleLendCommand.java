package seedu.address.logic.commands.people;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliPrefix.PEOPLE_COMMAND_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Debt;
import seedu.address.model.transaction.Loan;
import seedu.address.model.transaction.TransactionList;

/**
 * Records the amount of money the user lends to a person.
 */
public class PeopleLendCommand extends Command {

    public static final String COMMAND_WORD = "lend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Records the amount of money that you lend to a"
            + " person. Parameters: <index> (must be a positive integer) "
            + PREFIX_NAME + "<description> "
            + PREFIX_AMOUNT + "<amount> "
            + "[" + PREFIX_DATE + "<date:dd/mm/yyyy>]\n"
            + "Example: " + PEOPLE_COMMAND_TYPE + " "
            + COMMAND_WORD + " 4 "
            + PREFIX_NAME + "Dinner "
            + PREFIX_AMOUNT + "10.00 "
            + PREFIX_DATE + "02/02/2020";

    public static final String MESSAGE_LEND_SUCCESS = "Increased loan to %1$s by %2$s. %1$s now owes you %3$s.";

    private final Index targetIndex;
    private final Loan loan;

    public PeopleLendCommand(Index targetIndex, Loan loan) {
        this.targetIndex = targetIndex;
        this.loan = loan;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personUserLends = lastShownList.get(targetIndex.getZeroBased());
        Person addedLoanPerson = createPersonLends(personUserLends, loan);
        model.setPerson(personUserLends, addedLoanPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_LEND_SUCCESS, personUserLends.getName(),
                loan.getAmount(), addedLoanPerson.getLoans().getTotal()));
    }

    /**
     * Creates and returns a {@code Person} after adding the {@code loan} the user lends to the person.
     */
    private static Person createPersonLends(Person personUserLends, Loan loan) {
        assert personUserLends != null;

        Name name = personUserLends.getName();
        Phone phone = personUserLends.getPhone();
        Email email = personUserLends.getEmail();
        TransactionList<Debt> debts = personUserLends.getDebts();
        TransactionList<Loan> updatedLoans = new TransactionList<>();
        updatedLoans.setTransactions(personUserLends.getLoans());
        updatedLoans.add(loan);
        Set<Tag> tags = personUserLends.getTags();
        return new Person(name, phone, email, debts, updatedLoans, tags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PeopleLendCommand // instanceof handles nulls
                && targetIndex.equals(((PeopleLendCommand) other).targetIndex))
                && loan.equals(((PeopleLendCommand) other).loan); // state check
    }
}


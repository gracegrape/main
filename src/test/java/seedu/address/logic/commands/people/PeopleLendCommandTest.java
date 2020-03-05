package seedu.address.logic.commands.people;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalLoans.SHOPPING;
import static seedu.address.testutil.TypicalLoans.TRAVEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PeopleLendCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personUserLends = lastShownList.get(INDEX_FIRST_PERSON.getZeroBased());
        Person addedLoanPerson = new PersonBuilder(personUserLends).withLoans(SHOPPING).build();

        PeopleLendCommand peopleLendCommand = new PeopleLendCommand(INDEX_FIRST_PERSON, SHOPPING);

        String expectedMessage = String.format(PeopleLendCommand.MESSAGE_LEND_SUCCESS,
                addedLoanPerson.getName(), SHOPPING.getAmount(), addedLoanPerson.getLoans().getTotal());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), addedLoanPerson);

        assertCommandSuccess(peopleLendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {

        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personUserLends = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person addedLoanPerson = new PersonBuilder(personUserLends).withLoans(SHOPPING).build();

        PeopleLendCommand peopleLendCommand = new PeopleLendCommand(INDEX_FIRST_PERSON, SHOPPING);

        String expectedMessage = String.format(PeopleLendCommand.MESSAGE_LEND_SUCCESS,
                addedLoanPerson.getName(), SHOPPING.getAmount(), addedLoanPerson.getLoans().getTotal());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), addedLoanPerson);

        assertCommandSuccess(peopleLendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {

        final PeopleLendCommand standardCommand = new PeopleLendCommand(INDEX_FIRST_PERSON, SHOPPING);

        // same values -> returns true
        PeopleLendCommand commandWithSameValues = new PeopleLendCommand(INDEX_FIRST_PERSON, SHOPPING);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new PeopleClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new PeopleLendCommand(INDEX_SECOND_PERSON, SHOPPING)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new PeopleLendCommand(INDEX_FIRST_PERSON, TRAVEL)));
    }

}


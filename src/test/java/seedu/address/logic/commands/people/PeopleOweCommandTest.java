package seedu.address.logic.commands.people;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalDebts.MILKTEA;
import static seedu.address.testutil.TypicalDebts.TEXTBOOK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PeopleOweCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personUserOwe = lastShownList.get(INDEX_FIRST_PERSON.getZeroBased());
        Person addedDebtPerson = new PersonBuilder(personUserOwe).withDebts(TEXTBOOK).build();

        PeopleOweCommand peopleOweCommand = new PeopleOweCommand(INDEX_FIRST_PERSON, TEXTBOOK);

        String expectedMessage = String.format(PeopleOweCommand.MESSAGE_OWE_SUCCESS,
                addedDebtPerson.getName(), TEXTBOOK.getAmount(), addedDebtPerson.getDebts().getTotal());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), addedDebtPerson);

        assertCommandSuccess(peopleOweCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {

        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personUserOwe = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person addedDebtPerson = new PersonBuilder(personUserOwe).withDebts(TEXTBOOK).build();

        PeopleOweCommand peopleOweCommand = new PeopleOweCommand(INDEX_FIRST_PERSON, TEXTBOOK);

        String expectedMessage = String.format(PeopleOweCommand.MESSAGE_OWE_SUCCESS,
                addedDebtPerson.getName(), TEXTBOOK.getAmount(), addedDebtPerson.getDebts().getTotal());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), addedDebtPerson);

        assertCommandSuccess(peopleOweCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {

        final PeopleOweCommand standardCommand = new PeopleOweCommand(INDEX_FIRST_PERSON, TEXTBOOK);

        // same values -> returns true
        PeopleOweCommand commandWithSameValues = new PeopleOweCommand(INDEX_FIRST_PERSON, TEXTBOOK);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new PeopleClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new PeopleOweCommand(INDEX_SECOND_PERSON, TEXTBOOK)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new PeopleOweCommand(INDEX_FIRST_PERSON, MILKTEA)));
    }

}

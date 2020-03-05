package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDebts.SUPPER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DebtBuilder;
import seedu.address.testutil.LoanBuilder;

class DebtTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Debt(null, null, null));
    }

    @Test
    public void equals() {
        Debt supperCopy = new DebtBuilder()
                .withDescription("Supper")
                .withAmount("10")
                .withDate("2020-01-23").build();
        Loan supperLoan = new LoanBuilder()
                .withDescription("Supper")
                .withAmount("10")
                .withDate("2020-01-23").build();
        Debt debtDiffDesc = new DebtBuilder()
                .withDescription("Dinner")
                .withAmount("10")
                .withDate("2020-01-23").build();
        Debt debtDiffAmt = new DebtBuilder()
                .withDescription("Supper")
                .withAmount("20")
                .withDate("2020-01-23").build();
        Debt debtDiffDate = new DebtBuilder()
                .withDescription("Supper")
                .withAmount("10")
                .withDate("2020-01-12").build();

        // same values -> returns true
        assertTrue(SUPPER.equals(supperCopy));

        // same object -> returns true
        assertTrue(SUPPER.equals(SUPPER));

        // null -> returns false
        assertFalse(SUPPER.equals(null));

        // different type -> returns false
        assertFalse(SUPPER.equals(5));

        // different type of transaction -> returns false
        assertFalse(SUPPER.equals(supperLoan));

        // different description -> returns false
        assertFalse(SUPPER.equals(debtDiffDesc));

        // different amount -> returns false
        assertFalse(SUPPER.equals(debtDiffAmt));

        // different date -> returns false
        assertFalse(SUPPER.equals(debtDiffDate));

    }
}

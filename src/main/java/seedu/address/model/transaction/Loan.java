package seedu.address.model.transaction;

import java.time.LocalDate;

import seedu.address.model.tag.Tag;

/**
 * Represents a loan, money which the user lends.
 */
public class Loan extends Transaction {
    /**
     * Constructs a Loans object.
     */
    public Loan(Description description, Amount amount, LocalDate date) {
        super(description, amount, date, new Tag("Loan"));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Loan description: ")
                .append(getDescription())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Date: ")
                .append(getDate());
        return builder.toString();
    }
}

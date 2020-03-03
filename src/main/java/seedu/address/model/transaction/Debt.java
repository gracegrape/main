package seedu.address.model.transaction;

import java.time.LocalDate;

import seedu.address.model.tag.Tag;

/**
 * Represents a Debt, money which the user owes another person..
 * Guarantees: immutable.
 */
public class Debt extends Transaction {

    /**
     * Constructs a Debt object.
     */
    public Debt(Description description, Amount amount, LocalDate date) {
        super(description, amount, date, new Tag("Debt"));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Debt description: ")
                .append(getDescription())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Date: ")
                .append(getDate());
        return builder.toString();
    }

}

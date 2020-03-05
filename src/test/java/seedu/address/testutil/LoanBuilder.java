package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Loan;

/**
 * A utility class to help with building Loan objects.
 */
public class LoanBuilder {

    public static final String DEFAULT_DESCRIPTION = "Loan example";
    public static final String DEFAULT_AMOUNT = "0";
    public static final String DEFAULT_DATE = "2020-01-01";

    private Description description;
    private Amount amount;
    private LocalDate date;

    public LoanBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        amount = new Amount(Double.parseDouble(DEFAULT_AMOUNT));
        date = LocalDate.parse(DEFAULT_DATE);
    }

    /**
     * Initializes the LoanBuilder with the data of {@code loanToCopy}.
     */
    public LoanBuilder(Loan loanToCopy) {
        description = loanToCopy.getDescription();
        amount = loanToCopy.getAmount();
        date = loanToCopy.getDate();
    }

    /**
     * Sets the {@code Description} of the {@code Loan} that we are building.
     */
    public LoanBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Loan} that we are building.
     */
    public LoanBuilder withAmount(String amount) {
        this.amount = new Amount(Double.parseDouble(amount));
        return this;
    }

    /**
     * Sets the {@code LocalDate} of the {@code Loan} that we are building.
     */
    public LoanBuilder withDate(String date) {
        this.date = LocalDate.parse(date);
        return this;
    }

    public Loan build() {
        return new Loan(description, amount, date);
    }

}

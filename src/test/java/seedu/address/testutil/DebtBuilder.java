package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Debt;
import seedu.address.model.transaction.Description;

/**
 * A utility class to help with building Debt objects.
 */
public class DebtBuilder {
    public static final String DEFAULT_DESCRIPTION = "Debt example";
    public static final String DEFAULT_AMOUNT = "0";
    public static final String DEFAULT_DATE = "2020-01-01";

    private Description description;
    private Amount amount;
    private LocalDate date;

    public DebtBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        amount = new Amount(Double.parseDouble(DEFAULT_AMOUNT));
        date = LocalDate.parse(DEFAULT_DATE);
    }

    /**
     * Initializes the DebtBuilder with the data of {@code debtToCopy}.
     */
    public DebtBuilder(Debt debtToCopy) {
        description = debtToCopy.getDescription();
        amount = debtToCopy.getAmount();
        date = debtToCopy.getDate();
    }

    /**
     * Sets the {@code Description} of the {@code Debt} that we are building.
     */
    public DebtBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Debt} that we are building.
     */
    public DebtBuilder withAmount(String amount) {
        this.amount = new Amount(Double.parseDouble(amount));
        return this;
    }

    /**
     * Sets the {@code LocalDate} of the {@code Debt} that we are building.
     */
    public DebtBuilder withDate(String date) {
        this.date = LocalDate.parse(date);
        return this;
    }

    public Debt build() {
        return new Debt(description, amount, date);
    }

}

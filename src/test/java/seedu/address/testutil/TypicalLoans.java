package seedu.address.testutil;

import seedu.address.model.transaction.Loan;

/**
 * A utility class containing a list of {@code Loan} objects to be used in tests.
 */
public class TypicalLoans {
    public static final Loan BREAKFAST = new LoanBuilder()
            .withDescription("Breakfast")
            .withAmount("5")
            .withDate("2018-08-08").build();
    public static final Loan LUNCH = new LoanBuilder()
            .withDescription("Lunch")
            .withAmount("12")
            .withDate("2019-09-09").build();
    public static final Loan DINNER = new LoanBuilder()
            .withDescription("Dinner")
            .withAmount("22")
            .withDate("2020-02-02").build();
    public static final Loan SUPPER = new LoanBuilder()
            .withDescription("Supper")
            .withAmount("8")
            .withDate("2018-08-18").build();
    public static final Loan DESSERT = new LoanBuilder()
            .withDescription("Dessert")
            .withAmount("6")
            .withDate("2019-09-19").build();
    public static final Loan SHOPPING = new LoanBuilder()
            .withDescription("Shopping")
            .withAmount("50")
            .withDate("2020-02-20").build();
    public static final Loan TRAVEL = new LoanBuilder()
            .withDescription("Travel")
            .withAmount("120")
            .withDate("2022-02-22").build();
}

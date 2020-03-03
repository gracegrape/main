package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.transaction.Debt;

/**
 * A utility class containing a list of {@code Debt} objects to be used in tests.
 */
public class TypicalDebts {

    public static final Debt SUPPER = new DebtBuilder()
            .withDescription("Supper")
            .withAmount("10")
            .withDate("2020-01-23").build();
    public static final Debt TEXTBOOK = new DebtBuilder()
            .withDescription("Textbook")
            .withAmount("100")
            .withDate("2019-08-10").build();
    public static final Debt MILKTEA = new DebtBuilder()
            .withDescription("Milk Tea")
            .withAmount("3")
            .withDate("2020-02-21").build();
    public static final Debt MOVIE = new DebtBuilder()
            .withDescription("Movie")
            .withAmount("13")
            .withDate("2019-12-26").build();
    public static final Debt IPHONE = new DebtBuilder()
            .withDescription("iPhone 13")
            .withAmount("1000")
            .withDate("2022-10-03").build();
    public static final Debt TAOBAO = new DebtBuilder()
            .withDescription("TaoBao")
            .withAmount("11")
            .withDate("2019-11-11").build();
    public static final Debt MCGRIDDLE = new DebtBuilder()
            .withDescription("McGriddle")
            .withAmount("5.40")
            .withDate("2020-02-19").build();

    private TypicalDebts() {
    } // prevents instantiation


    public static List<Debt> getTypicalDebts() {
        return new ArrayList<>(Arrays.asList(SUPPER, TEXTBOOK, MILKTEA, MOVIE, IPHONE, TAOBAO, MCGRIDDLE));
    }
}

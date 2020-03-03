package seedu.address.model.transaction;

/**
 * Represents an Amount of money.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount implements Comparable<Amount> {

    public static final String MESSAGE_CONSTRAINTS = "Amount of money should only contain numbers.";
    public final double amount;

    /**
     * Constructs an {@code Amount}.
     *
     * @param amount A valid amount of money.
     */
    public Amount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns true if a given amount (in String form) is valid.
     */
    public static boolean isValidAmount(String test) {
        try {
            Double.parseDouble(test.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Adds {@code amountToAdd} to the current amount.
     *
     * @return the total amount after adding.
     */
    public Amount add(Amount amountToAdd) {
        double totalAmount = amountToAdd.amount + this.amount;
        return new Amount(totalAmount);
    }


    @Override
    public String toString() {
        return String.format("$%.2f", amount);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && amount == ((Amount) other).amount); // state check
    }

    @Override
    public int hashCode() {
        return Double.valueOf(amount).hashCode();
    }

    @Override
    public int compareTo(Amount o) {
        return Double.compare(amount, o.amount);
    }

}

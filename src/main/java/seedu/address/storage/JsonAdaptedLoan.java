package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Loan;

/**
 * Jackson-friendly version of {@link Loan}
 */
public class JsonAdaptedLoan {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Loan's %s field is missing!";

    private final String description;
    private final String amount;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedLoan} with the given loan details.
     */
    @JsonCreator
    public JsonAdaptedLoan(@JsonProperty("description") String description,
                           @JsonProperty("amount") String amount,
                           @JsonProperty("date") String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Converts a given {@code Loan} into this class for Jackson use.
     */
    public JsonAdaptedLoan(Loan loan) {
        description = loan.getDescription().description;
        amount = String.valueOf(loan.getAmount().amount);
        date = loan.getDate().toString();
    }

    /**
     * Converts this Jackson-friendly adapted debt object into the model's {@code Loan} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted loan.
     */
    public Loan toModelType() throws IllegalValueException {

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(Double.parseDouble(amount));

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDate.class.getSimpleName()));
        }
        final LocalDate modelDate;
        try {
            modelDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(e.getMessage());
        }

        return new Loan(modelDescription, modelAmount, modelDate);
    }
}

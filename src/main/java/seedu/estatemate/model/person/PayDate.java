package seedu.estatemate.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.estatemate.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's pay date in EstateMate.
 * Guarantees: immutable; is valid as declared in {@link #isValidPayDate(String)}
 */
public class PayDate {

    /**
     * The first character of the pay date must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String MESSAGE_CONSTRAINTS = "Pay date must be in the format: yyyy-MM-dd, "
            + "and it should not be blank";

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public final LocalDate value;

    /**
     * Constructs a {@code PayDate}.
     *
     * @param payDate A valid pay date.
     */
    public PayDate(String payDate) {
        requireNonNull(payDate);
        checkArgument(isValidPayDate(payDate), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(payDate.trim(), DATE_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date in the correct format.
     */
    public static boolean isValidPayDate(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        String trimmedInput = input.trim();
        if (!trimmedInput.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            LocalDate.parse(trimmedInput.trim(), DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.format(DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PayDate otherPayDate)) {
            return false;
        }

        return value.equals(otherPayDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

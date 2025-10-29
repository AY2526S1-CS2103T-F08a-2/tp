package seedu.estatemate.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.estatemate.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Person's lease in EstateMate.
 * Guarantees: immutable; is valid as declared in {@link #isValidLease(String)}
 */
public class Lease {

    public static final String MESSAGE_CONSTRAINTS = "Leases should be of the format \"yyyy-MM-dd yyyy-MM-dd\" "
            + "and adhere to the following constraints:\n"
            + "1. The first date is the start date and the second date is the end date.\n"
            + "2. The end date must be on the same day or after the start date."
            + "3. The dates must be valid calendar dates.";

    /*
     * A valid input consists of 2 dates of format dddd-dd-dd, separated by a space.
     */
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{4}-\\d{2}-\\d{2}";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd")
            .withResolverStyle(ResolverStyle.STRICT);

    public final String value;

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a {@code Lease}.
     *
     * @param lease A valid lease of format "yyyy-MM-dd yyyy-MM-dd".
     */
    public Lease(String lease) {
        requireNonNull(lease);
        checkArgument(isValidLease(lease), MESSAGE_CONSTRAINTS);
        value = lease;

        String[] dates = getDates(lease);
        startDate = LocalDate.parse(dates[0], DATE_FORMATTER);
        endDate = LocalDate.parse(dates[1], DATE_FORMATTER);
    }

    /**
     * Returns true if a given string is in a valid lease format.
     */
    public static boolean isValidLease(String test) {
        return test.matches(VALIDATION_REGEX) && isValidLeaseDates(test);
    }

    /**
     * Returns true if the input lease dates are dates that exist and the end date is on or after the start date.
     * Assumes that the input has already passed the VALIDATION_REGEX format: "dddd-dd-dd dddd-dd-dd".
     */
    private static boolean isValidLeaseDates(String test) {
        String[] dates = getDates(test);
        try {
            // Check that start and end dates are valid dates
            LocalDate startDate = LocalDate.parse(dates[0], DATE_FORMATTER);
            LocalDate endDate = LocalDate.parse(dates[1], DATE_FORMATTER);
            // Check that end date is on the day of start date or after
            if (endDate.isBefore(startDate)) {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Parses the lease for its start and end dates.
     * @return A string array with the first element as the start date and second element as the end date.
     */
    private static String[] getDates(String lease) {
        return lease.split(" ");
    }

    /**
     * Converts the lease into a more human-readable string representation, intended for display purposes.
     * @return A display friendly string representing the lease period.
     */
    public String toDisplayValue() {
        return startDate.format(DATE_FORMATTER) + " to " + endDate.format(DATE_FORMATTER);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Lease)) {
            return false;
        }

        Lease otherLease = (Lease) other;
        boolean isStartDateEquals = startDate.equals(otherLease.startDate);
        boolean isEndDateEquals = endDate.equals(otherLease.endDate);
        return isStartDateEquals && isEndDateEquals;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

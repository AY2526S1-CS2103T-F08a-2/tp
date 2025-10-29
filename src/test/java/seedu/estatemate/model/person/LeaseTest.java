package seedu.estatemate.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.estatemate.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LeaseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Lease(null));
    }

    @Test
    public void constructor_invalidLease_throwsIllegalArgumentException() {
        String invalidLease = "";
        assertThrows(IllegalArgumentException.class, () -> new Lease(invalidLease));
    }

    @Test
    public void isValidLease() {
        // null lease
        assertThrows(NullPointerException.class, () -> Lease.isValidLease(null));

        // blank lease
        assertFalse(Lease.isValidLease("")); // empty string
        assertFalse(Lease.isValidLease(" ")); // spaces only

        // does not follow regex format
        assertFalse(Lease.isValidLease("hello")); // non-date input
        assertFalse(Lease.isValidLease("hello there")); // non-date input with space
        assertFalse(Lease.isValidLease("2025-02-01 there")); // single non-date input
        assertFalse(Lease.isValidLease("hello 2025-02-01")); // single non-date input
        assertFalse(Lease.isValidLease("25-02-01 25-02-01")); // incorrect number of year digits
        assertFalse(Lease.isValidLease("25-012-01 25-02-01")); // incorrect number of month digits
        assertFalse(Lease.isValidLease("2025-2-1 2025-2-2")); // single digit date input
        assertFalse(Lease.isValidLease("01/02/2025 2025/02/02")); // use of slash instead of hyphen
        assertFalse(Lease.isValidLease("2025/02/01 02/02/2025")); // use of slash instead of hyphen
        assertFalse(Lease.isValidLease("01-02-2025 2025-02-02")); // single invalid date format
        assertFalse(Lease.isValidLease("2025-02-01 02-02-2025")); // single invalid date format
        assertFalse(Lease.isValidLease("-2025-02-29 -2025-03-02")); // negative year dates (to indicate BCE)

        // invalid dates
        assertFalse(Lease.isValidLease("2025-19-01 2025-20-02")); // swapped day and month: YYYY-dd-MM
        assertFalse(Lease.isValidLease("2025-02-10 2025-02-02")); // start date after end date
        assertFalse(Lease.isValidLease("2025-02-10 2024-02-10")); // start date after end date
        assertFalse(Lease.isValidLease("2025-02-10 2025-19-02")); // date does not exist
        assertFalse(Lease.isValidLease("2025-02-33 2025-03-02")); // date does not exist
        // date 2025-02-29 is not a valid calendar date (for resolver strict DateTimeFormatter)
        assertFalse(Lease.isValidLease("2025-02-29 2025-03-02"));

        // valid leases
        assertTrue(Lease.isValidLease("2025-12-01 2025-12-08")); // different day digits
        assertTrue(Lease.isValidLease("2025-02-01 2025-05-01")); // different month digits
        assertTrue(Lease.isValidLease("2025-01-01 2030-01-01")); // different year digits
        assertTrue(Lease.isValidLease("2025-11-13 2030-06-21")); // different day, month, year digits
        assertTrue(Lease.isValidLease("2025-03-21 2027-02-14")); // end date after start date
        assertTrue(Lease.isValidLease("2025-01-01 2030-01-01")); // end date on start date
    }

    @Test
    public void equals() {
        Lease lease = new Lease("2024-03-21 2028-09-21");

        // same values -> returns true
        assertTrue(lease.equals(new Lease("2024-03-21 2028-09-21")));

        // same object -> returns true
        assertTrue(lease.equals(lease));

        // null -> returns false
        assertFalse(lease.equals(null));

        // different types -> returns false
        assertFalse(lease.equals(5.0f));

        // different values -> returns false
        assertFalse(lease.equals(new Lease("2026-11-12 2030-05-16")));
    }

    @Test
    public void toDisplayValue() {
        Lease lease = new Lease("2024-03-21 2028-09-21");
        String actualDisplayValue = lease.toDisplayValue();

        String expectedDisplayValue = "2024-03-21 to 2028-09-21";

        assertTrue(actualDisplayValue.equals(expectedDisplayValue));
    }
}

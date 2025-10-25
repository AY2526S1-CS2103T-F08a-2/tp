package seedu.estatemate.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.estatemate.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PayDateTest {

    @Test
    public void construct_null_throwsNullPointerException() {
        // throws exception when input is null
        assertThrows(NullPointerException.class, () -> PayDate.isValidPayDate(null));
    }

    @Test
    public void construct_invalidPayDate_throwsIllegalArgumentException() {
        // invalid format
        String invalidPayDate = "14/10/2025";
        assertThrows(IllegalArgumentException.class, () -> new PayDate(invalidPayDate));
    }

    @Test
    public void isValidPayDate() {
        // null pay date
        assertThrows(NullPointerException.class, () -> PayDate.isValidPayDate(null));

        // all possible invalid pay dates
        assertFalse(PayDate.isValidPayDate(""));
        assertFalse(PayDate.isValidPayDate(" "));
        assertFalse(PayDate.isValidPayDate("2025/09/12")); // incorrect format
        assertFalse(PayDate.isValidPayDate("2025-9-1")); // incorrect format
        assertFalse(PayDate.isValidPayDate("12-09-2025")); // incorrect format : MM-dd-YYYY

        // all possible valid pay dates
        assertTrue(PayDate.isValidPayDate("2025-10-10"));
    }

    @Test
    public void equals() {
        PayDate payDate = new PayDate("2025-10-10");

        // same value -> returns true
        assertTrue(payDate.equals(new PayDate("2025-10-10")));

        // same object -> returns true
        assertTrue(payDate.equals(payDate));

        // null -> returns false
        assertFalse(payDate.equals(null));

        // different types -> returns false
        assertFalse(payDate.equals(5.0f));

        // different values -> returns false
        assertFalse(payDate.equals(new PayDate("2025-10-12")));
    }
}

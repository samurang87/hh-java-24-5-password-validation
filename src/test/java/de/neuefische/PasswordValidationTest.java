package de.neuefische;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordValidationTest {


    @Test
    void givenLongEnough_lengthValidationReturnsTrue() {
        String password = "aaaaaaaaaa";
        boolean res = Main.isLongEnough(password);
        Assertions.assertTrue(res);
    }

    @Test
    void givenTooShort_lengthValidationReturnsFalse() {
        String password = "anshwn";
        boolean res = Main.isLongEnough(password);
        Assertions.assertFalse(res);
    }

    @Test
    void given12_containsDigitsReturnsTrue() {
        String password = "12345";
        boolean res = Main.containsDigits(password);
        Assertions.assertTrue(res);
    }

    @Test
    void givenLettersOnly_containsDigitsReturnsFalse() {
        String password = "hello";
        boolean res = Main.containsDigits(password);
        Assertions.assertFalse(res);
    }

}

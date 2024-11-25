package de.neuefische;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    void givenLowercase_containsLowercaseReturnsTrue() {
        String password = "hello";
        boolean res = Main.containsLowercaseLetters(password);
        Assertions.assertTrue(res);
    }

    @ParameterizedTest
    @CsvSource({
            "10", "HENLO", "&ˆ+)"
    })
    void givenNotLowercase_containsLowercaseReturnsFalse(String password) {
        boolean res = Main.containsLowercaseLetters(password);
        Assertions.assertFalse(res);
    }

    @Test
    void givenUppercase_containsUppercaseReturnsTrue() {
        String password = "OH BOY";
        boolean res = Main.containsUppercaseLetters(password);
        Assertions.assertTrue(res);
    }

    @ParameterizedTest
    @CsvSource({
            "10", "smol", "&ˆ+)"
    })
    void givenNotUppercase_containsUppercaseReturnsFalse(String password) {
        boolean res = Main.containsUppercaseLetters(password);
        Assertions.assertFalse(res);
    }

    @ParameterizedTest
    @CsvSource({
            "admin", "azerty", "password", "Password", "qwerty", "qwertz",
    })
    void givenTrivialPassword_isUncommonReturnsFalse(String password) {
        boolean res = Main.isUncommon(password);
        Assertions.assertFalse(res);
    }

    @ParameterizedTest
    @CsvSource({
            "abcdef12", "6GhijK",
    })
    void givenAlphabeticalSequence_isUncommonReturnsFalse(String password) {
        boolean res = Main.isUncommon(password);
        Assertions.assertFalse(res);
    }

    @ParameterizedTest
    @CsvSource({
            "123456s", "654f321",
    })
    void givenNumericalSequence_isUncommonReturnsFalse(String password) {
        boolean res = Main.isUncommon(password);
        Assertions.assertFalse(res);
    }

    @ParameterizedTest
    @CsvSource({
            "aaa111", "tt4t444ttt", "aAaa111"
    })
    void givenTrivialRepetition_isUncommonReturnsFalse(String password) {
        boolean res = Main.isUncommon(password);
        Assertions.assertFalse(res);
    }

    @Test
    void givenSpecialCharacter_isUncommonReturnsTrue() {
        String password = "abcd@efgh";
        boolean res = Main.isUncommon(password);
        Assertions.assertTrue(res);
    }
}

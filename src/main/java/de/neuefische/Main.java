package de.neuefische;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Password Validator");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a potential password: ");
        String password = scanner.nextLine();
        boolean res = isLongEnough(password) &&
                containsDigits(password) &&
                containsLowercaseLetters(password) &&
                containsUppercaseLetters(password) &&
                isUncommon(password);
        if (res) {
            System.out.println("Your password is strong! \uD83D\uDCAA");
        } else {
            System.out.println("Your password is weak... \uD83D\uDE22");
        }
    }

    public static boolean isLongEnough(String password) {
        return password.length() >= 8;
    }

    public static boolean containsDigits(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsLowercaseLetters(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsUppercaseLetters(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUncommon(String password) {
        // The top common passwords contain:
        // - the words admin, password
        // - sequences that are easy to type on the keyboard, such as qwerty, azerty, qwertz
        // - sequences of numbers and/or letters, such as 123456, abcdefgh
        // - repeated characters to fill the length requirements, e.g. aaa11111
        String[] commonPasswords = {
                "admin",
                "azerty",
                "password",
                "qwerty",
                "qwertz",
        };
        if (Arrays.asList(commonPasswords).contains(password.toLowerCase())) {
            return false;
        }
        if (containsAlphabeticalSequence(password)) {
            return false;
        }
        if (containsNumericalSequence(password)) {
            return false;
        }
        return !containsOnlyOneLetterAndNumber(password);
    }

    private static boolean containsAlphabeticalSequence(String password) {
        String passwordWithoutDigits = password.replaceAll("\\d", "");
        if (passwordWithoutDigits.length() < 3) {
            return false;
        }
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        return alphabet.contains(passwordWithoutDigits.toLowerCase());
    }

    private static boolean containsNumericalSequence(String password) {
        String passwordWithoutLetters = password.replaceAll("\\D", "");
        if (passwordWithoutLetters.length() < 3){
            return false;
        }
        for (int i = 1; i < passwordWithoutLetters.length(); i++) {
            char current = passwordWithoutLetters.charAt(i);
            char previous = passwordWithoutLetters.charAt(i - 1);
            if (!Character.isDigit(current) || !Character.isDigit(previous)) {
                return false;
            }
            if (Math.abs(Character.getNumericValue(current) - Character.getNumericValue(previous)) != 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsOnlyOneLetterAndNumber(String password) {
        String passwordWithoutLetters = password.replaceAll("\\D", "");
        boolean onlyOneRepeatedNumber = true;
        if (passwordWithoutLetters.length() < 2) {
            onlyOneRepeatedNumber = false;
        } else {
            char firstLetter = passwordWithoutLetters.charAt(0);
            for (int i = 1; i < passwordWithoutLetters.length(); i++) {
                char current = passwordWithoutLetters.charAt(i);
                if (current != firstLetter) {
                    onlyOneRepeatedNumber = false;
                    break;
                }
            }
        }
        boolean onlyOneRepeatedLetter = true;
        String passwordWithoutDigits = password.toLowerCase().replaceAll("\\d", "");
        if (passwordWithoutDigits.length() < 2) {
            onlyOneRepeatedLetter = false;
        } else {
            char firstDigit = passwordWithoutDigits.charAt(0);
            for (int i = 1; i < passwordWithoutDigits.length(); i++) {
                char current = passwordWithoutDigits.charAt(i);
                if (current != firstDigit) {
                    onlyOneRepeatedLetter = false;
                    break;
                }
            }
        }
        return onlyOneRepeatedNumber && onlyOneRepeatedLetter;
    }
}

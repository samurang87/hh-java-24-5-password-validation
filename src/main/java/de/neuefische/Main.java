package de.neuefische;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                isUncommon(password) &&
                containsSpecialCharacter(password);
        if (res) {
            System.out.println("Your password is strong! \uD83D\uDCAA");
        } else {
            System.out.println("Your password is weak... \uD83D\uDE22");
            System.out.println("You could use something like " + generateRandomString());
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
        return !containsOnlyOneOfLetterAndNumber(password);
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

    private static boolean isOnlyOneRepeatedCharacter(String text) {
        boolean onlyOneRepeatedChar = true;
        if (!text.isEmpty()) {
            char firstDigit = text.charAt(0);
            for (int i = 1; i < text.length(); i++) {
                char current = text.charAt(i);
                if (current != firstDigit) {
                    onlyOneRepeatedChar = false;
                    break;
                }
            }
        }
        return onlyOneRepeatedChar;
    }

    private static boolean containsOnlyOneOfLetterAndNumber(String password) {
        password = password.replaceAll("[^a-zA-Z0-9\\s]", "");
        String passwordWithoutLetters = password.replaceAll("\\D", "");
        boolean onlyOneRepeatedNumber = isOnlyOneRepeatedCharacter(passwordWithoutLetters);
        boolean onlyOneRepeatedLetter = isOnlyOneRepeatedCharacter(password.toLowerCase().replaceAll("\\d", ""));
        return onlyOneRepeatedNumber && onlyOneRepeatedLetter;
    }

    public static boolean containsSpecialCharacter(String password) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        return m.find();
    }

    public static String generateRandomString() {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String special = "!@#$%^&*()_+-=";

        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            result.append(letters.charAt(random.nextInt(letters.length())));
        }

        for (int i = 0; i < 3; i++) {
            char upperChar = letters.charAt(random.nextInt(letters.length()));
            result.append(String.valueOf(upperChar).toUpperCase());
        }

        for (int i = 0; i < 3; i++) {
            result.append(special.charAt(random.nextInt(special.length())));
        }

        for (int i = 0; i < 3; i++) {
            result.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return result.toString();
    }
}

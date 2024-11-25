package de.neuefische;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Password Validator");
        String password = "MyPassword1";
        boolean res = isLongEnough(password) &&
                containsDigits(password) &&
                containsLowercaseLetters(password) &&
                containsUppercaseLetters(password);
        System.out.println(res);
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
}

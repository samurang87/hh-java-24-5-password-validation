package de.neuefische;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Password Validator");
        String password = "pass";
        boolean res = isLongEnough(password);
        System.out.println(res);
    }

    public static boolean isLongEnough(String password) {
        return password.length() >= 8;
    }
}

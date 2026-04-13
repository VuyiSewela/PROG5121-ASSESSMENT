import java.util.Scanner;

public class Main {

    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecialCharacter = true;
            }
        }

        return hasUppercase && hasDigit && hasSpecialCharacter;
    }

    public static boolean checkCellPhoneNumber(String cellPhoneNumber) {
        return cellPhoneNumber.matches("^\\+27[6-8][0-9]{8}$");
    }

    public static boolean loginUser(String enteredUsername, String enteredPassword,
                                    String storedUsername, String storedPassword) {
        return enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword);
    }

    public static String returnLoginStatus(boolean loginSuccess, String firstName, String lastName) {
        if (loginSuccess) {
            return "Welcome " + firstName + " " + lastName + ", it is great to have you back again.";
        } else {
            return "Username or password is incorrect, please try again.";
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String firstName = "";
        String lastName = "";
        String storedUsername = "";
        String storedPassword = "";
        String cellPhoneNumber = "";

        boolean isRegistered = false;
        boolean running = true;

        System.out.println("Welcome ");

        while (running) {
            System.out.println("\nMain Menu");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String option = input.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter first name: ");
                    firstName = input.nextLine();

                    System.out.print("Enter last name: ");
                    lastName = input.nextLine();

                    System.out.print("Enter username: ");
                    String username = input.nextLine();

                    if (!checkUserName(username)) {
                        System.out.println("Username is not correctly formatted. Please ensure that your username has an underscore and is not more than 5 characters long.");
                        break;
                    } else {
                        System.out.println("Username successfully captured.");
                    }

                    System.out.print("Enter password: ");
                    String password = input.nextLine();

                    if (!checkPasswordComplexity(password)) {
                        System.out.println("Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
                        break;
                    } else {
                        System.out.println("Password successfully captured.");
                    }

                    System.out.print("Enter cell phone number (e.g. +27831234567): ");
                    String phone = input.nextLine();

                    if (!checkCellPhoneNumber(phone)) {
                        System.out.println("Cell phone number incorrectly formatted or does not contain valid international code.");
                        break;
                    } else {
                        System.out.println("Cell phone number successfully added.");
                    }

                    storedUsername = username;
                    storedPassword = password;
                    cellPhoneNumber = phone;
                    isRegistered = true;

                    System.out.println("User registered successfully.");
                    System.out.println("You can now log in.");
                    break;

                case "2":
                    if (!isRegistered) {
                        System.out.println("No users registered yet, please register first.");
                        break;
                    }

                    System.out.print("Enter username: ");
                    String enteredUsername = input.nextLine();

                    System.out.print("Enter password: ");
                    String enteredPassword = input.nextLine();

                    boolean loginSuccess = loginUser(
                            enteredUsername,
                            enteredPassword,
                            storedUsername,
                            storedPassword
                    );

                    System.out.println(returnLoginStatus(loginSuccess, firstName, lastName));
                    break;

                case "3":
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }

        input.close();
    }
}

package Main;

import java.util.Scanner;
import java.util.Random;

public class Main {

    // METHOD TO CHECK USERNAME
    // Username must contain "_" and be 5 characters or less
    public static boolean checkUserName(String username) {

        return username.contains("_") && username.length() <= 5;
    }

    // METHOD TO CHECK PASSWORD COMPLEXITY
    // Password must contain:
    // - at least 8 characters
    // - capital letter
    // - number
    // - special character
    public static boolean checkPasswordComplexity(String password) {

        // If password is less than 8 characters
        if (password.length() < 8) {
            return false;
        }

        // Variables used to track password rules
        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        // Loop through every character in password
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            // Check uppercase
            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            }

            // Check number
            if (Character.isDigit(ch)) {
                hasNumber = true;
            }

            // Check special character
            if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        // Password only valid if ALL conditions are true
        return hasUpper && hasNumber && hasSpecial;
    }

    // METHOD TO VALIDATE SA CELL NUMBER
    public static boolean checkCellPhoneNumber(String number) {

        // Must start with +27 and contain valid digits
        return number.matches("^\\+27[6-8][0-9]{8}$");
    }

    // METHOD TO CHECK LOGIN DETAILS
    public static boolean loginUser(String enteredUsername,
                                    String enteredPassword,
                                    String storedUsername,
                                    String storedPassword) {

        // Compare entered details to registered details
        return enteredUsername.equals(storedUsername)
                && enteredPassword.equals(storedPassword);
    }

    public static void main(String[] args) {

        // Scanner object for user input
        Scanner input = new Scanner(System.in);

        // Variables to store user details
        String firstName;
        String lastName;
        String username;
        String password;
        String phoneNumber;

        // ================= REGISTER =================

        System.out.println("===== REGISTER =====");

        // Ask user for first name
        System.out.print("Enter first name: ");
        firstName = input.nextLine();

        // Ask user for last name
        System.out.print("Enter last name: ");
        lastName = input.nextLine();

        // ===== USERNAME VALIDATION LOOP =====
        while (true) {

            System.out.print("Enter username: ");
            username = input.nextLine();

            // If username is correct
            if (checkUserName(username)) {

                System.out.println("Username successfully captured.");
                break;

            } else {

                System.out.println("Username is not correctly formatted.");
            }
        }

        // ===== PASSWORD VALIDATION LOOP =====
        while (true) {

            System.out.print("Enter password: ");
            password = input.nextLine();

            // If password is valid
            if (checkPasswordComplexity(password)) {

                System.out.println("Password successfully captured.");
                break;

            } else {

                System.out.println("Password is not correctly formatted.");
            }
        }

        // ===== PHONE NUMBER VALIDATION LOOP =====
        while (true) {

            System.out.print("Enter cellphone number: ");
            phoneNumber = input.nextLine();

            // Validate number
            if (checkCellPhoneNumber(phoneNumber)) {

                System.out.println("Cell phone number successfully added.");
                break;

            } else {

                System.out.println("Cell phone number incorrectly formatted.");
            }
        }

        // Registration complete
        System.out.println("\nRegistration successful!");

        // ================= LOGIN =================

        System.out.println("\n===== LOGIN =====");

        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        // Check if login is correct
        boolean loginSuccess = loginUser(
                loginUsername,
                loginPassword,
                username,
                password
        );

        // If login successful
        if (loginSuccess) {

            System.out.println("\nWelcome "
                    + firstName
                    + " "
                    + lastName);

            System.out.println("Welcome to QuickChat.");

            // Variable to count sent messages
            int totalMessages = 0;

            // Main menu loop
            while (true) {

                System.out.println("\n===== MENU =====");
                System.out.println("1. Send Messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");

                System.out.print("Choose option: ");

                int option = Integer.parseInt(input.nextLine());

                // ===== OPTION 1 =====
                if (option == 1) {

                    // Ask how many messages user wants to send
                    System.out.print("How many messages would you like to send? ");

                    int numMessages = Integer.parseInt(input.nextLine());

                    // Loop through number of messages
                    for (int i = 0; i < numMessages; i++) {

                        System.out.println("\nMessage " + (i + 1));

                        // Create new Message object
                        Message msg = new Message();

                        // Ask recipient number
                        System.out.print("Enter recipient number: ");
                        String recipient = input.nextLine();

                        // Ask message text
                        System.out.print("Enter message: ");
                        String messageText = input.nextLine();

                        // Save values into object
                        msg.setRecipient(recipient);
                        msg.setMessage(messageText);
                        msg.setMessageNumber(i);

                        // Generate random message ID
                        Random random = new Random();

                        String messageID = String.valueOf(
                                100000000 + random.nextInt(900000000)
                        );

                        // Store message ID
                        msg.setMessageID(messageID);

                        // Check message ID length
                        if (!msg.checkMessageID()) {

                            System.out.println("Message ID invalid.");
                            continue;
                        }

                        // Check recipient number format
                        if (!msg.checkRecipientCell()) {

                            System.out.println("Cell phone number incorrectly formatted.");
                            continue;
                        }

                        // Check message length
                        if (messageText.length() > 250) {

                            int exceeded =
                                    messageText.length() - 250;

                            System.out.println(
                                    "Message exceeds 250 characters by "
                                            + exceeded
                            );

                            continue;

                        } else {

                            System.out.println("Message ready to send.");
                        }

                        // Display generated hash
                        System.out.println("Message Hash: "
                                + msg.createMessageHash());

                        // ===== SEND MENU =====
                        System.out.println("\nChoose:");
                        System.out.println("1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message");

                        int choice =
                                Integer.parseInt(input.nextLine());

                        // Display result
                        String result =
                                msg.sentMessage(choice);

                        System.out.println(result);

                        // If user selected SEND
                        if (choice == 1) {

                            totalMessages++;

                            // Display message details
                            System.out.println("\nFULL MESSAGE DETAILS");

                            System.out.println("Message ID: "
                                    + msg.getMessageID());

                            System.out.println("Message Hash: "
                                    + msg.createMessageHash());

                            System.out.println("Recipient: "
                                    + recipient);

                            System.out.println("Message: "
                                    + messageText);
                        }
                    }

                    // Display total messages sent
                    System.out.println(
                            "\nTotal messages sent: "
                                    + totalMessages
                    );

                }

                // ===== OPTION 2 =====
                else if (option == 2) {

                    System.out.println("Coming Soon.");
                }

                // ===== OPTION 3 =====
                else if (option == 3) {

                    System.out.println("Goodbye!");
                    break;
                }

                // Invalid menu option
                else {

                    System.out.println("Invalid option.");
                }
            }

        }

        // Login failed
        else {

            System.out.println("Username or password incorrect.");
        }

        // Close scanner
        input.close();
    }
}

package Main;

public class Main {

    // VARIABLES FOR MESSAGE DETAILS
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;

    // ================= SETTERS =================

    // Store message ID
    public void setMessageID(String messageID) {

        this.messageID = messageID;
    }

    // Return message ID
    public String getMessageID() {

        return messageID;
    }

    // Store message number
    public void setMessageNumber(int messageNumber) {

        this.messageNumber = messageNumber;
    }

    // Store recipient number
    public void setRecipient(String recipient) {

        this.recipient = recipient;
    }

    // Store message text
    public void setMessage(String message) {

        this.message = message;
    }

    // ================= VALIDATION METHODS =================

    // Check message ID length
    public boolean checkMessageID() {

        return messageID.length() <= 10;
    }

    // Check recipient cellphone number
    public boolean checkRecipientCell() {

        return recipient.matches("^\\+27[6-8][0-9]{8}$");
    }

    // ================= CREATE HASH =================

    public String createMessageHash() {

        // Split sentence into words
        String[] words = message.split(" ");

        // First word
        String firstWord = words[0].toUpperCase();

        // Last word
        String lastWord =
                words[words.length - 1].toUpperCase();

        // First 2 digits of message ID
        String firstTwo =
                messageID.substring(0, 2);

        // Return final hash
        return firstTwo
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;
    }

    // ================= SEND MESSAGE =================

    public String sentMessage(int choice) {

        // Option 1 = send
        if (choice == 1) {

            return "Message successfully sent.";
        }

        // Option 2 = discard
        else if (choice == 2) {

            return "Press 0 to delete message.";
        }

        // Option 3 = store
        else if (choice == 3) {

            return "Message successfully stored.";
        }

        // Invalid option
        else {

            return "Invalid choice.";
        }
    }
}

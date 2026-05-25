public class Message {

    // VARIABLES
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;

    // STORE MESSAGE ID
    public void setMessageID(String messageID) {

        this.messageID = messageID;
    }

    // RETURN MESSAGE ID
    public String getMessageID() {

        return messageID;
    }

    // STORE MESSAGE NUMBER
    public void setMessageNumber(int messageNumber) {

        this.messageNumber = messageNumber;
    }

    // STORE RECIPIENT
    public void setRecipient(String recipient) {

        this.recipient = recipient;
    }

    // STORE MESSAGE
    public void setMessage(String message) {

        this.message = message;
    }

    // CHECK MESSAGE ID
    public boolean checkMessageID() {

        return messageID.length() <= 10;
    }

    // CHECK RECIPIENT NUMBER
    public boolean checkRecipientCell() {

        return recipient.matches("^\\+27[6-8][0-9]{8}$");
    }

    // CREATE MESSAGE HASH
    public String createMessageHash() {

        String[] words = message.split(" ");

        String firstWord = words[0].toUpperCase();

        String lastWord =
                words[words.length - 1].toUpperCase();

        String firstTwo =
                messageID.substring(0, 2);

        return firstTwo
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord;
    }

    // SEND MESSAGE METHOD
    public String sentMessage(int choice) {

        if (choice == 1) {

            return "Message successfully sent.";
        }

        else if (choice == 2) {

            return "Press 0 to delete message.";
        }

        else if (choice == 3) {

            return "Message successfully stored.";
        }

        else {

            return "Invalid choice.";
        }
    }
}

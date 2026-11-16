package com.mycompany.programming1a_poe;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    private String messageStatus;
    private static int totalMessages = 0;

    public Message(String messageID, int messageNumber, String recipient, String message) {
        this.messageID = messageID.isEmpty() ? generateID() : messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
        this.messageStatus = "";
    }

    private String generateID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+\\d{10,12}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (message.length() <= 250) return "Message ready to send.";
        int excess = message.length() - 250;
        return "Message exceeds 250 characters by " + excess + ", please reduce size.";
    }

    public String createMessageHash() {
        if (message == null || message.trim().isEmpty()) return "";
        String[] words = message.trim().split("\\s+");
        String first = words[0].replaceAll("[^a-zA-Z]", "");
        String last = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + (first + last).toUpperCase();
    }

    public String SentMessage(int choice) {
        if (choice == 1) {
            messageStatus = "sent";
            totalMessages++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            messageStatus = "disregard";
            return "Press 0 to delete the message.";
        } else {
            messageStatus = "stored";
            return "Message successfully stored.";
        }
    }

    public void storeMessage() {
        try (FileWriter file = new FileWriter("storedMessages.json", true)) {
            String json = "{\"messageID\":\"" + messageID + "\",\"messageHash\":\"" + messageHash + "\",\"recipient\":\"" + recipient + "\",\"message\":\"" + message + "\"}\n";
            file.write(json);
        } catch (IOException e) {
            System.out.println("Error storing message");
        }
    }

    public String printMessages() {
        return "MessageID: " + messageID + "\nMessageHash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + message;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    // ===== PART 3 METHODS =====
    public static String findLongestMessage(ArrayList<Message> storedList) {
        if (storedList.isEmpty()) return "No stored messages.";
        Message longest = storedList.get(0);
        for (Message m : storedList) {
            if (m.getMessage().length() > longest.getMessage().length()) {
                longest = m;
            }
        }
        return longest.getMessage();
    }

    public static String searchByMessageID(ArrayList<Message> list, String id) {
        for (Message m : list) {
            if (m.getMessageID().equals(id)) {
                return "Recipient: " + m.getRecipient() + "\nMessage: " + m.getMessage();
            }
        }
        return "Message ID not found.";
    }

    public static String searchByRecipient(ArrayList<Message> list, String recipient) {
        String result = "";
        for (Message m : list) {
            if (m.getRecipient().equals(recipient)) {
                result += m.getMessage() + "\n";
            }
        }
        return result.isEmpty() ? "No messages for recipient." : result;
    }

    public static String deleteByHash(ArrayList<Message> list, String hash) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMessageHash().equals(hash)) {
                String deleted = list.get(i).getMessage();
                list.remove(i);
                return "Message: " + deleted + " successfully deleted.";
            }
        }
        return "Hash not found.";
    }

    public static String displayReport(ArrayList<Message> list) {
        String report = "=== MESSAGE REPORT ===\n";
        for (Message m : list) {
            report += "Hash: " + m.getMessageHash() + "\nRecipient: " + m.getRecipient() + "\nMessage: " + m.getMessage() + "\n---\n";
        }
        return report;
    }
    // ===== END PART 3 =====

    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getMessageHash() { return messageHash; }
    public String getMessageStatus() { return messageStatus; }
    public void setMessageHash(String h) { this.messageHash = h; }
    public void setMessageStatus(String s) { this.messageStatus = s; }
}
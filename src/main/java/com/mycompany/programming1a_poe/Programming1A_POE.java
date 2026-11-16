// PROG5121 - Programming 1A POE Part 3
// Student: Mothekgeng Masemola
// Project: QuickChat Messaging App
// Description: This program allows users to register, login, send, store and search messages
// Part 3 Added: ArrayLists for sent/stored messages, JSON file saving, search and report menu
package com.mycompany.programming1a_poe;

import java.util.Scanner;
import java.util.ArrayList;

public class Programming1A_POE {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("       REGISTRATION");
        System.out.println("=================================");

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter your username: ");
        String username = input.nextLine();
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        System.out.print("Enter your South African cell phone number: ");
        String cellPhoneNumber = input.nextLine();

        Login user = new Login(firstName, lastName, username, password, cellPhoneNumber);

        System.out.println("\n=================================");
        System.out.println("       REGISTRATION RESULT");
        System.out.println("=================================");
        System.out.println(user.registerUser());

        if (user.checkUserName() && user.checkPasswordComplexity() && user.checkCellPhoneNumber()) {

            System.out.println("\n=================================");
            System.out.println("             LOGIN");
            System.out.println("=================================");
            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();
            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

            user.setLoginDetails(loginUsername, loginPassword);
            String loginResult = user.returnLoginStatus();
            System.out.println("\n" + loginResult);

            if (loginResult.toLowerCase().contains("welcome")) {
                System.out.println("\nWelcome to QuickChat.");

                // PART 3 ARRAYS
                ArrayList<Message> sentMessages = new ArrayList<>();
                ArrayList<Message> disregardedMessages = new ArrayList<>();
                ArrayList<Message> storedMessages = new ArrayList<>();
                ArrayList<String> messageHashes = new ArrayList<>();
                ArrayList<String> messageIDs = new ArrayList<>();

                while (true) {
                    System.out.println("\n--- QuickChat Menu ---");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Stored Messages");
                    System.out.println("4) Quit");
                    System.out.print("Choice: ");
                    int choice = Integer.parseInt(input.nextLine());

                    if (choice == 1) {
                        System.out.print("How many messages to send? ");
                        int howMany = Integer.parseInt(input.nextLine());
                        for (int i = 0; i < howMany; i++) {
                            System.out.print("Recipient (+27...): ");
                            String rec = input.nextLine();
                            System.out.print("Message: ");
                            String txt = input.nextLine();

                            Message m = new Message("", sentMessages.size() + storedMessages.size() + disregardedMessages.size() + 1, rec, txt);
                            
                            if (txt.length() > 250) {
                                System.out.println("Please enter a message of less than 250 characters.");
                                i--; continue;
                            }
                            if (!rec.matches("^\\+\\d{10,12}$") && !rec.matches("^0\\d{9}$")) {
                                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                                i--; continue;
                            }

                            System.out.println("Message ID generated: " + m.getMessageID());
                            System.out.println("Message Hash: " + m.getMessageHash());

                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message to send later");
                            System.out.print("Choose: ");
                            int sendChoice = Integer.parseInt(input.nextLine());
                            System.out.println(m.SentMessage(sendChoice));

                            if (sendChoice == 1) {
                                sentMessages.add(m);
                            } else if (sendChoice == 2) {
                                disregardedMessages.add(m);
                            } else {
                                storedMessages.add(m);
                                m.storeMessage();
                            }
                            messageHashes.add(m.getMessageHash());
                            messageIDs.add(m.getMessageID());
                            System.out.println(m.printMessages());
                        }
                        System.out.println("Total messages sent: " + Message.returnTotalMessages());

                    } else if (choice == 2) {
                        System.out.println("Coming Soon.");
                    } else if (choice == 3) {
                        System.out.println("\n--- Stored Messages ---");
                        System.out.println("a) Display sender/recipient of all stored");
                        System.out.println("b) Display longest stored message");
                        System.out.println("c) Search by Message ID");
                        System.out.println("d) Search by recipient");
                        System.out.println("e) Delete by Message Hash");
                        System.out.println("f) Display Report");
                        System.out.print("Choose a-f: ");
                        String sub = input.nextLine();

                        if (sub.equals("a")) {
                            for (Message m : storedMessages) System.out.println("Recipient: " + m.getRecipient() + " | Message: " + m.getMessage());
                        } else if (sub.equals("b")) {
                            System.out.println(Message.findLongestMessage(storedMessages));
                        } else if (sub.equals("c")) {
                            System.out.print("Enter Message ID: ");
                            System.out.println(Message.searchByMessageID(storedMessages, input.nextLine()));
                        } else if (sub.equals("d")) {
                            System.out.print("Enter recipient: ");
                            System.out.println(Message.searchByRecipient(storedMessages, input.nextLine()));
                        } else if (sub.equals("e")) {
                            System.out.print("Enter Message Hash: ");
                            System.out.println(Message.deleteByHash(storedMessages, input.nextLine()));
                        } else if (sub.equals("f")) {
                            System.out.println(Message.displayReport(storedMessages));
                        }

                    } else if (choice == 4) {
                        break;
                    }
                }
            }
        } else {
            System.out.println("\nRegistration was unsuccessful.");
        }
        input.close();
    }
}
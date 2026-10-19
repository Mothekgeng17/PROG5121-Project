package com.mycompany.programming1a_poe;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Mothekgeng Masemola
 */
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

        Login user = new Login(
                firstName,
                lastName,
                username,
                password,
                cellPhoneNumber
        );

        System.out.println();
        System.out.println("=================================");
        System.out.println("       REGISTRATION RESULT");
        System.out.println("=================================");

        System.out.println(user.registerUser());

        if (user.checkUserName()
                && user.checkPasswordComplexity()
                && user.checkCellPhoneNumber()) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("             LOGIN");
            System.out.println("=================================");

            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

                user.setLoginDetails(loginUsername, loginPassword);

                System.out.println();
                String loginResult = user.returnLoginStatus();
                System.out.println(loginResult);

                // PART 2 - QUICKCHAT - Starts at line 69
                if (loginResult.toLowerCase().contains("welcome")) {
                    System.out.println("\nWelcome to QuickChat.");
                    ArrayList<Message> quickMessages = new ArrayList<>();
                    
                    while (true) {
                        System.out.println("\n--- QuickChat Menu ---");
                        System.out.println("1) Send Messages");
                        System.out.println("2) Show recently sent messages");
                        System.out.println("3) Quit");
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
                                
                                Message m = new Message("", quickMessages.size() + 1, rec, txt);
                                String recCheck = m.checkRecipientCell();
                                System.out.println(recCheck);
                                if (recCheck.contains("incorrect")) {
                                    System.out.println("Cell number incorrectly formatted.");
                                    i--; continue;
                            }
                            if (txt.length() > 250) {
                                    System.out.println("Exceeds 250 chars.");
                                    i--; continue;
                                }
                                quickMessages.add(m);
                                System.out.println("ID: " + m.getMessageID());
                                System.out.println("Hash: " + m.getMessageHash());
                                System.out.println("Sent to: " + m.getRecipient());
                            }
                        } else if (choice == 2) {
                            System.out.println("Coming Soon.");
                        } else if (choice == 3) {
                            break;
                        }
                    }
                }

            } else {
                System.out.println();
                System.out.println("Registration was unsuccessful.");
            }

            input.close();
        }
    }
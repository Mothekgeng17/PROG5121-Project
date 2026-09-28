package com.mycompany.programming1a_poe;

import java.util.Scanner;

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
            System.out.println(user.returnLoginStatus());

        } else {

            System.out.println();
            System.out.println("Registration was unsuccessful.");
        }

        input.close();
    }
}
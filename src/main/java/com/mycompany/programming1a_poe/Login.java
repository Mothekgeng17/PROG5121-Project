 // ST Number ST10501204 - Mothekgeng17 - PROG5121 Part 1
package com.mycompany.programming1a_poe;

/**
 *
 * @author Mothekgeng Masemola
 */
public class Login {

    // Variables used to store the user's details Kyle, kyl_1,etc
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String loginUsername;
    private String loginPassword;
    private String cellPhoneNumber;

    // Constructor to initialize user detail
    public Login(String firstName, String lastName, String username,
            String password, String cellPhoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // Checks whether the username is correctly formatted
    public Boolean checkUserName() {

        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    // Checks whether the password meets the complexity requirements
    public Boolean checkPasswordComplexity() {

        if (password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[^a-zA-Z0-9].*")) {

            return true;

        } else {
            return false;
        }
    }

    // Checks whether the cellphone number has the correct international format
    // Regex for SA cellphone validation - adapted from GeeksforGeeks (2024)
    public Boolean checkCellPhoneNumber() {

        String cellPhoneRegex = "^\\+27[0-9]{9}$";

        return cellPhoneNumber.matches(cellPhoneRegex);
    }

    // Registers the user and returns the required messages
    public String registerUser() {

        if (!checkUserName()) {

            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";

        }

        if (!checkPasswordComplexity()) {

            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

        }

        if (!checkCellPhoneNumber()) {

            return "Cell phone number incorrectly formatted or does not contain international code.";

        }

        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.";
    }

    // Stores the username and password entered during login
    public void setLoginDetails(String loginUsername, String loginPassword) {

        this.loginUsername = loginUsername;
        this.loginPassword = loginPassword;
    }

    // Checks whether the login details match the registered details
    public Boolean loginUser() {

        if (username.equals(loginUsername)
                && password.equals(loginPassword)) {

            return true;

        } else {
            return false;
        }
    }

    // Returns the appropriate login message
    public String returnLoginStatus() {

        if (loginUser()) {

            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";

        } else {

            return "Username or password incorrect, please try again.";
        }
    }
    // References:
    // GeeksforGeeks. 2024. Regular Expressions in Java. Available at: https://www.geeksforgeeks.org/regular-expressions-in-java/ [Accessed 28 September 2026]
}  
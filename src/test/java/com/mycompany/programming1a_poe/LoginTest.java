package com.mycompany.programming1a_poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // Test Data from POE page 8-10
    String validUsername = "kyl_1";
    String invalidUsername = "kyle!!!!!!!";
    String validPassword = "Ch&&sec@ke99!";
    String invalidPassword = "password";
    String validCell = "+27838968976";
    String invalidCell = "08966553";

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("First", "Last", validUsername, validPassword, validCell);
        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login("First", "Last", invalidUsername, validPassword, validCell);
        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordComplexityMeets() {
        Login login = new Login("First", "Last", validUsername, validPassword, validCell);
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordComplexityFails() {
        Login login = new Login("First", "Last", validUsername, invalidPassword, validCell);
        assertFalse(login.checkPasswordComplexity());
    }

    @Test
    public void testCellCorrectlyFormatted() {
        Login login = new Login("First", "Last", validUsername, validPassword, validCell);
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCellIncorrectlyFormatted() {
        Login login = new Login("First", "Last", validUsername, validPassword, invalidCell);
        assertFalse(login.checkCellPhoneNumber());
    }

    @Test
    public void testRegisterUserSuccess() {
        Login login = new Login("Kyl", "User", validUsername, validPassword, validCell);
        String result = login.registerUser();
        assertTrue(result.contains("successfully"));
    }

    @Test
    public void testLoginSuccessful() {
        Login login = new Login("Kyl", "User", validUsername, validPassword, validCell);
        login.setLoginDetails(validUsername, validPassword);
        assertTrue(login.loginUser());
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login("Kyl", "User", validUsername, validPassword, validCell);
        login.setLoginDetails("wrong", "wrong");
        assertFalse(login.loginUser());
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        Login login = new Login("Kyl", "User", validUsername, validPassword, validCell);
        login.setLoginDetails(validUsername, validPassword);
        String status = login.returnLoginStatus();
        assertTrue(status.contains("Welcome"));
    }

    @Test
    public void testReturnLoginStatusFailed() {
        Login login = new Login("Kyl", "User", validUsername, validPassword, validCell);
        login.setLoginDetails("wrong", "wrong");
        String status = login.returnLoginStatus();
        assertTrue(status.contains("incorrect"));
    }
}
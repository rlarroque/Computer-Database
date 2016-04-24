package com.excilys.computer_database.dto;

/**
 * @author rlarroque on 4/24/2016.
 */
public class UserDTO {

    private String username;
    private String password;
    private String confirm_password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}

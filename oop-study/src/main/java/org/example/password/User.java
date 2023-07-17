package org.example.password;



public class User {
    private String password;

    public void initPassword(PasswordGenerator passwordGenerator) {
        String randomPass= passwordGenerator.generatePassword();

        if (randomPass.length() >= 8 && randomPass.length() <= 12) {
            this.password = randomPass;
        }
    }

    public String getPassword() {
        return password;
    }
}

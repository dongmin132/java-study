package org.example.password;

public class FixedPasswordGenerator implements PasswordGenerator{
    @Override
    public String generatePassword() {
        return "abcdefghijk";
    }
}

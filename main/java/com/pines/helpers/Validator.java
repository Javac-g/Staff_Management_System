package com.pines.helpers;

import com.pines.models.User;

import java.util.List;
import java.util.regex.Pattern;




public class Validator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public void validateFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()  ) {
            throw new IllegalArgumentException("First name is required");
        }
    }

    public void validateLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
    }
    public void validateAge(Integer age) {
        if (age == null || age < 0 || age < 18)  {
            throw new IllegalArgumentException("The user is underage: " + age);
        }
    }
    public void validateUser(User x){
        if (x == null){
            throw  new IllegalArgumentException("The user is not found");
        }
    }
    public void validateEmailFormat(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (!Pattern.compile(EMAIL_REGEX).matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
    public void validate_email_uniqueness(String email, List<User> dataBase){
        if (dataBase.stream().anyMatch(u -> u.getEmail().equals(email))) {
            throw new IllegalArgumentException("Email is already in the system");
        }
    }
    public void validateEmailSimilarity(String oldEmail, String newEmail, List<User> dataBase){
        if (!oldEmail.equals(newEmail)) {
            validate_email_uniqueness(newEmail, dataBase);
        }else throw new IllegalArgumentException("New email cant be the same as old one");

    }
}

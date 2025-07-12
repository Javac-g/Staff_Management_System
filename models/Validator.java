package models;

import java.util.List;
import java.util.regex.Pattern;

import static jdk.internal.joptsimple.internal.Strings.isNullOrEmpty;


public class Validator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public void validateName(String firstName, String lastName) {
        if (isNullOrEmpty(firstName) || isNullOrEmpty(lastName)) {
            throw new IllegalArgumentException("Name parameters should be filled");
        }
    }

    public void validateAge(Integer age) {
        if (age == null || age < 18) {
            throw new IllegalArgumentException("The user is underage: " + age);
        }
    }
    public void validateUser(User x){
        if (x == null){
            throw  new IllegalArgumentException("The user is null");
        }
    }
    public void validateEmailFormat(String email) {
        if (isNullOrEmpty(email)) {
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

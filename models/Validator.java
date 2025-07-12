package models;

import java.util.List;
import java.util.regex.Pattern;

import static jdk.internal.joptsimple.internal.Strings.isNullOrEmpty;


public class Validator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private void validateName(String firstName, String lastName) {
        if (isNullOrEmpty(firstName) || isNullOrEmpty(lastName)) {
            throw new IllegalArgumentException("Name parameters should be filled");
        }
    }

    private void validateAge(Integer age) {
        if (age == null || age < 18) {
            throw new IllegalArgumentException("The user is underage: " + age);
        }
    }
    public void validateUser(User x){
        if (x == null){
            throw  new IllegalArgumentException("The user is null");
        }
    }
    private void validateEmailUniquenessAndFormat(String email, List<User> dataBase) {
        if (isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Email is required");
        }

        if (dataBase.stream().anyMatch(u -> u.getEmail().equals(email))) {
            throw new IllegalArgumentException("Email is already in the system");
        }

        if (!Pattern.compile(EMAIL_REGEX).matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}

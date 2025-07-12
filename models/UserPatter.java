package models;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserPatter {
    int getAge();
    int getId();
    UUID getUuid();
    void setAge(int age);
    void setId(int id);
    void setEmail(String email);
    void setFirstName(String first_name);
    void setLastName(String last_name);
    String getFirstName();
    String getEmail();
    String getLastName();
    LocalDateTime getDateOfCreation();
}


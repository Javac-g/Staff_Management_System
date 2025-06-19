package models;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserPatter {
    int getAge();
    UUID getUuid();
    void setAge(int age);
    void setFirstName(String first_name);
    void setLastName(String last_name);
    String getFirstName();
    String getLastName();
    LocalDateTime getDateOfCreation();
}


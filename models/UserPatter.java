package models;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserPatter {
    int getAge();
    UUID getId();
    void setAge(int age);
    void setName(String name);
    String getName();
    LocalDateTime getDateOfCreation();
}


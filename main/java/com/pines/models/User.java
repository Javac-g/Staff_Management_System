package com.pines.models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.String.format;

public class User implements UserPatter{

    private int age,id;
    private String first_name, last_name, email, full_name;
    private final UUID uuid;
    private final LocalDateTime dateOfCreation;


    public User() {

        Supplier<UUID> supplier = UUID::randomUUID;
        Stream<UUID> infiniteStream = Stream.generate(supplier);
        uuid = infiniteStream.findFirst().orElseThrow( ()-> new IllegalStateException("No UUID generated") );
        this.dateOfCreation = LocalDateTime.now();
    }
    private User(String email) {
        this.email = email;
        this.first_name = "John|Jane";
        this.last_name = "Dou";
        this.age = 0;
        id = -1;
        uuid=null;
        this.dateOfCreation = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }
    public static User getUnknown(String email){
        return new User(email);
    }
    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }
    public String getFull_name(){
        return this.first_name + " " + this.last_name;
    }
    @Override
    public int hashCode(){
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(age);
        hash = 23 * hash + Objects.hashCode(id);
        hash = 23 * hash + Objects.hashCode(email);
        hash = 23 * hash + Objects.hashCode(first_name);
        hash = 23 * hash + Objects.hashCode(last_name);
        hash = 23 * hash + Objects.hashCode(uuid);
        hash = 23 * hash + Objects.hashCode(dateOfCreation);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        if(this == object){
            return true;
        }
        if(object == null || this.getClass() != object.getClass() ){
            return false;
        }
        User user = (User)object;
        return Objects.equals(this.age,user.getAge()) &&
               Objects.equals(this.first_name,user.getFirstName()) &&
               Objects.equals(this.last_name,user.getLastName()) &&
               Objects.equals(this.uuid,user.getUuid()) &&
               Objects.equals(this.id,user.getId()) &&
               Objects.equals(this.email,user.getEmail()) &&
               Objects.equals(this.dateOfCreation,user.getDateOfCreation());
    }
    @Override
    public String toString(){
        return format("Database ID: %d%n" +
                        "UUID: %s%n" +
                        "Name: %s%n" +
                        "Age: %s%n" +
                        "Date of Creation: %s%n" +
                        "Email: %s%n",
                id,
                uuid != null ? uuid : "null",
                (first_name != null && last_name != null) ? getFull_name() : "Unknown",
                (age > 0) ? age : "Unknown",
                (dateOfCreation != null) ? dateOfCreation : "Unknown",
                (email != null && !email.isEmpty()) ? email : "Unknown");

    }

}

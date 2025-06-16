package models;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.String.format;

public class User {

    private int age;
    private String name;
    private UUID id;
    private LocalDateTime dateOfCreation;


    public User(int age, String name) {
        this.age = age;
        this.name = name;
        Supplier<UUID> supplier = UUID::randomUUID;
        Stream<UUID> infiniteStream = Stream.generate(supplier);
        id = infiniteStream.findFirst().orElseThrow( ()-> new IllegalStateException("No UUID generated") );
        this.dateOfCreation = LocalDateTime.now();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(age);
        hash = 23 * hash + Objects.hashCode(name);
        hash = 23 * hash + Objects.hashCode(id);
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
               Objects.equals(this.name,user.getName()) &&
               Objects.equals(this.id,user.getId()) &&
               Objects.equals(this.dateOfCreation,user.getDateOfCreation());
    }
    @Override
    public String toString(){
        return format("ID: %s%nName: %s  %s%nAge: %d%nDate: %s%n",
                id != null ? id : "null",
                name != null ? name : "Unknown",
                age != 0 ? age : "Unknown",
                dateOfCreation != null ? dateOfCreation : "Unknown");

    }

}

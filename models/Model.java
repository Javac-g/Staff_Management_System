package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static jdk.internal.joptsimple.internal.Strings.isNullOrEmpty;

public class Model {

    private static final List<User> dataBase = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Model.class.getName());


    public void text_log(UserPatter user, String method) throws FileNotFoundException {

        byte[] str = ("Method: " + method ).getBytes();

        try(FileOutputStream fos = new FileOutputStream("info.log",true);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(fos);)
        {
            bos.write(str);
            bos.writeTo(fos);
            dos.writeUTF(user.toString());

        }catch (FileNotFoundException e1){
            e1.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setID(UserPatter user){
        if (!dataBase.isEmpty()) {
            int id = dataBase.get(dataBase.size() - 1).getId() + 1;
            user.setId(id);
        }
        if (dataBase.isEmpty()){
            user.setId(1);
        }

    }

    public UserPatter addUsed(String first_name,String last_name,String email,Integer age) throws FileNotFoundException {
        User user = new User();
        validateName(first_name,last_name);
        validateAge(age);
        validateEmailUniquenessAndFormat(email);

        try {

            user.setFirstName(first_name);
            user.setLastName(last_name);
            user.setEmail(email);
            user.setAge(age);


            setID(user);
            text_log(user, "Created: ");
            logger.info("Searched: " + user);
            dataBase.add(user);
            return user;
        }catch (Exception e){
            System.err.println("Error adding user: " + e.getMessage());
            return User.getUnknown(email);
        }

    }
    public User findUser(String email) throws FileNotFoundException {
       validateEmailUniquenessAndFormat(email);

        User user = dataBase.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseGet(() -> {
                    User unknown = User.getUnknown(email);
                    try {
                        text_log(unknown, "Searched, not found: ");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    return unknown;
                });

        text_log(user, "Searched, found: ");
        return user;
    }
    public UserPatter updateUser(String email, String fn, String ln, String em,Integer age) throws FileNotFoundException {
      validateName(fn,ln);
      validateEmailUniquenessAndFormat(email);
      validateEmailUniquenessAndFormat(em);
      validateAge(age);

        if (age < 18){
            throw new IllegalArgumentException("The user is underage - cannot be accepted");
        }
        User x = findUser(email);
        if (x == null){
            logger.info("Not found");
            throw new IllegalArgumentException("Model - update user method - user not found by email: " + email);
        }

        x.setFirstName(fn);
        x.setLastName(ln);
        x.setEmail(em);
        x.setAge(age);

        text_log(x,"Updated: ");
        logger.info("Updated: " + x);
        return x;


    }
    public Integer deleteUser(Integer id){
        int index = -1;
        for (int i = 0 ; i < dataBase.size();i++){
            if (dataBase.get(i).getId() == id){
                index = i;
            }
        }
        if (index != -1){
            dataBase.remove(index);
            return index;
        }
        return index;
    }




















}

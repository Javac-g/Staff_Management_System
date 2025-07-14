package com.pines.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.pines.helpers.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Model {

    private static final List<User> dataBase = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(Model.class);
    private static final Validator validator = new Validator();


    public void text_log(UserPatter user, String method) throws FileNotFoundException {

        byte[] str = ("Method: " + method ).getBytes();

        try(FileOutputStream fos = new FileOutputStream("info.log",true);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(fos))
        {
            bos.write(str);
            bos.writeTo(fos);
            dos.writeUTF(user.toString());

        } catch (IOException e) {
            System.out.println(e.getMessage());
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

    public User addUsed(String first_name,String last_name,String email,Integer age)  {
        User user = new User();

        validator.validateFirstName(first_name);
        validator.validateLastName(last_name);
        validator.validateAge(age);
        validator.validate_email_uniqueness(email,dataBase);
        validator.validateEmailFormat(email);

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
        validator.validateEmailFormat(email);

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
    public User updateUser(String email, String first_name, String last_name, String newEmail,Integer age) throws FileNotFoundException {
        User x = findUser(email);
        validator.validateUser(x);
        validator.validateFirstName(first_name);
        validator.validateLastName(last_name);
        validator.validateEmailFormat(newEmail);
        validator.validateEmailSimilarity(email,newEmail,dataBase);
        validator.validateAge(age);

        x.setFirstName(first_name);
        x.setLastName(last_name);
        x.setEmail(newEmail);
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

    public List<User> getDataBase(){
        return dataBase;
    }



















}

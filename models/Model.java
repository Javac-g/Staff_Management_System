package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Model {

    private static final List<User> dataBase = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Model.class.getName());
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public void text_log(User user, String method) throws FileNotFoundException {

        byte[] str = ("Method: " + method ).getBytes();

        try(FileOutputStream fos = new FileOutputStream("info.log",true);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(fos);)
        {
            bos.write(str);
            bos.writeTo(fos);
            dos.writeUTF(user.toString());

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setID(User user){
        if (!dataBase.isEmpty()) {
            int id = dataBase.get(dataBase.size() - 1).getId() + 1;
            user.setId(id);
        }
        if (dataBase.isEmpty()){
            user.setId(1);
        }

    }
    private boolean email_validation(String email){
        try{
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            return !pattern.matcher(email).matches();
        }catch (Exception e){
            System.out.println("Error validating email: " + e.toString());
            return true;
        }

    }
    public User addUsed(String first_name,String last_name,String email,Integer age) throws FileNotFoundException {
        User user = new User();
        logger.info("First name: " + first_name);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setAge(age);
        dataBase.stream().filter(x -> x.getEmail()
                .equals(email))
                .findAny()
                .ifPresent(x -> {
                    throw new IllegalArgumentException("Email is already in system");
                });

        if (!email_validation(email)){
         user.setEmail(email);
        }
        setID(user);
        text_log(user, "Created: ");
        logger.info("Searched: "+ user.toString());
        dataBase.add(user);
        return user;
    }
    public User findUser(String email){

        return dataBase.stream().filter(x -> x.getEmail().equals(email)).findFirst().orElse(null);
    }
    public User updateUser(String email, String fn, String ln, String em,Integer age) throws FileNotFoundException {
        User x = findUser(email);
        if (x != null){
            x.setFirstName(fn);
            x.setLastName(ln);
            x.setEmail(em);
            x.setAge(age);
            text_log(x,"Updated: ");
            logger.info("Updated: " + x.toString());
            return x;
        }
        logger.info("Not found");
        return null;
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

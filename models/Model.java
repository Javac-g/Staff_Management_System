package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Model {

    private static final List<User> dataBase = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Model.class.getName());
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public void text_log(User user, String method) throws FileNotFoundException {

        byte[] str = ("Method: " + method ).getBytes();

        try(FileOutputStream fos = new FileOutputStream("info.log",true);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos);)
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
    public User addUsed(User data) throws FileNotFoundException {
        User user = new User();
        user.setAge(data.getAge());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        setID(user);
        dataBase.add(user);
        text_log(user, "Created: ");
        logger.info("Searched: "+ user.toString());
        return user;
    }
    public User findUser(String email){
        for (User x: dataBase){
            if (x.getEmail().equals(email)){
                return x;
            }
        }
        return null;
    }
    public User updateUser(String email, User data) throws FileNotFoundException {
        User x = findUser(email);
        if (x != null){
            x.setFirstName(data.getFirstName());
            x.setLastName(data.getLastName());
            x.setEmail(data.getEmail());
            x.setAge(data.getAge());
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
        return 0;
    }




















}

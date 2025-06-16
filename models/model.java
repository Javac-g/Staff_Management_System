package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class model {

    private static final List<User> dataBase = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(model.class.getName());

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

    public User addUsed(User data) throws FileNotFoundException {
        User user = new User();
        user.setAge(data.getAge());
        user.setName(data.getName());
        dataBase.add(user);
        text_log(user, "Created: ");
        return user;
    }
}

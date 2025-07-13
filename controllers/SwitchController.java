package controllers;

import models.Model;
import models.User;
import models.UserPatter;
import models.Validator;
import views.View;

import java.io.FileNotFoundException;

public class SwitchController {
    private final Model model = new Model();
    private final View view = new View();
    private final Validator validator = new Validator();
    public void caseOne() throws FileNotFoundException {

       String firstName = "";
       String lastName = "";
       String email = "";
       int age = -1;
       boolean valid = false;

       while (!valid){
           view.printMsg("User creation menu:");

           firstName = view.getStr("Enter first name: ");
           lastName =  view.getStr("Enter last name: ");
           email = view.getStr("Enter email: ");
           age =   view.getNum("Enter age: ");

           validator.validateName(firstName,lastName);
           validator.validateEmailFormat(email);
           validator.validate_email_uniqueness(email, model.getDataBase());
           validator.validateAge(age);
           valid = true;

       }

       User user = model.addUsed(firstName,lastName,email,age);

       System.out.println("User created");
       view.printData(user);
    }
    public void caseTwo() throws FileNotFoundException {

        System.out.println("Search menu ");
        UserPatter x = model.findUser(view.getStr("Enter email: "));
        if (x != null){
            view.printData(x);
        }else {
            view.printMsg("User not found");
        }

    }
    public void caseThree() throws FileNotFoundException {
        System.out.println("Update user menu: ");
        UserPatter x = model.updateUser(view.getStr("Enter email of user to update:"),
                view.getStr("Enter first name: "),
                view.getStr("Enter last name: "),
                view.getStr("Enter email: "),
                view.getNum("Enter age: "));
        if (x != null){
            view.printData(x);
        }else{
            view.printMsg("Nobody updated");
        }
    }
    public void caseFour(){
        view.printMsg("Delete user menu");
        int id = model.deleteUser(view.getNum("Enter id to delete user"));
        if (id != -1){
            view.printMsg("Deleted user id: " + id);
        }else {
            view.printMsg("Nobody deleted");
        }
    }
    public boolean caseFive(){
        view.printMsg("bye bye");
        return false;
    }
}

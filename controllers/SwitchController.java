package controllers;

import models.Model;
import models.UserPatter;
import views.View;

import java.io.FileNotFoundException;

public class SwitchController {
    private final Model model = new Model();
    private final View view = new View();

    public void caseOne() throws FileNotFoundException {
        System.out.println("User creation menu:");
        UserPatter user = model.addUsed(
                view.getStr("Enter first name: "),
                view.getStr("Enter last name: "),
                view.getStr("Enter email: "),
                view.getNum("Enter age: ")
        );
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

package controllers;

import models.Model;
import models.User;
import views.View;

import java.io.FileNotFoundException;

public class Controller {
    public static void init_controller() throws FileNotFoundException {

        View view = new View();
        Model model = new Model();
        boolean loop = true;

        while (loop){
            view.printMenu();
            int choice = view.getNum("Enter: ");

            switch (choice){
                case 1 -> {
                    System.out.println("User creation menu:");
                    User user = model.addUsed(
                            view.getStr("Enter first name: "),
                            view.getStr("Enter last name: "),
                            view.getStr("Enter email: "),
                            view.getNum("Enter age: ")
                    );
                    System.out.println("User created");
                    view.printData(user);
                }
                case 2 ->{
                    System.out.println("Search menu ");
                    User x = model.findUser(view.getStr("Enter email: "));
                    if (x != null){
                        view.printData(x);
                    }else {
                        view.printMsg("User not found");
                    }

                }
                case 3 -> {
                    System.out.println("Update user menu: ");
                    User x = model.updateUser(view.getStr("Enter email of user to update:"),
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
                case 4 -> {
                    view.printMsg("Delete user menu");
                    int id = model.deleteUser(view.getNum("Enter id to delete user"));
                    if (id != -1){
                        view.printMsg("Deleted user id: " + id);
                    }else {
                        view.printMsg("Nobody deleted");
                    }
                }
                case 5 -> {
                    view.printMsg("bye bye");
                    loop = false;
                }
                default -> {view.printMsg("Wrong menu number");}
            }
        }





    }
}

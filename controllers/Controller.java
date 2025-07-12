package controllers;

import models.Model;
import models.User;
import models.UserPatter;
import views.View;

import java.io.FileNotFoundException;

public class Controller {
    private static final SwitchController swithController = new SwitchController();
    public static void init_controller() throws FileNotFoundException {
        try {
            View view = new View();
            Model model = new Model();
            boolean loop = true;

            while (loop){
                view.printMenu();
                int choice = view.getNum("Enter: ");

                switch (choice){
                    case 1 -> swithController.caseOne();
                    case 2 -> swithController.caseTwo();
                    case 3 -> swithController.caseThree();
                    case 4 -> swithController.caseFour();
                    case 5 -> loop =  swithController.caseFive();
                    default -> {view.printMsg("Wrong menu number");}
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }






    }
}

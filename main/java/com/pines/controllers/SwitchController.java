package com.pines.controllers;

import com.pines.helpers.InputHelper;
import com.pines.helpers.Tester;
import com.pines.helpers.Validator;
import com.pines.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pines.views.View;

import java.io.FileNotFoundException;

public class SwitchController {
    private final Model model = new Model();
    private final View view = new View();
    private final Validator validator = new Validator();
    private final static Logger logger = LoggerFactory.getLogger(SwitchController.class);


    public void caseOne() throws FileNotFoundException {
        Tester.logTest();
        view.printMsg("User creation menu:");
        String firstName = InputHelper.promptWithValidation(
                () -> view.getStr("Enter first name: "),
                validator::validateFirstName,
                "Invalid first name"
        );
        String lastName = InputHelper.promptWithValidation(
                () -> view.getStr("Enter last name: "),
                validator::validateLastName,
                "Invalid last name"
        );
        String email = InputHelper.promptWithValidation(
                () -> view.getStr("Enter email: "),
                val -> {
                    validator.validateEmailFormat(val);
                    validator.validate_email_uniqueness(val, model.getDataBase());
                },
                "Invalid email"
        );
        int age = InputHelper.promptWithValidation(
                () -> view.getNum("Enter age: "),
                validator::validateAge,
                "Invalid age"
        );

        User user = model.addUsed(firstName, lastName, email, age);
        logger.warn("User added");
        view.printData(user);
    }

    public void caseTwo() throws FileNotFoundException {

        view.printMsg("Search menu ");
        User x = model.findUser(view.getStr("Enter email: "));
        validator.validateUser(x);
        view.printData(x);
    }

    public void caseThree() throws FileNotFoundException {
        view.printMsg("Update user menu:");
        String email = InputHelper.prompt(new InputHelper.FieldPrompt<>(
                "Enter email of user to update",
                () -> view.getStr("Enter email of user to update: "),
                validator::validateEmailFormat
        ));

        User existingUser = model.findUser(email);
        validator.validateUser(existingUser);
        InputHelper.FieldPrompt<String> firstNamePrompt = new InputHelper.FieldPrompt<>(
                "Enter first name",
                () -> view.getStr("Enter first name: "),
                validator::validateFirstName
        );
        InputHelper.FieldPrompt<String> lastNamePrompt = new InputHelper.FieldPrompt<>(
                "Enter last name",
                () -> view.getStr("Enter last name: "),
                validator::validateLastName
        );
        InputHelper.FieldPrompt<String> newEmailPrompt = new InputHelper.FieldPrompt<>(
                "Enter new email",
                () -> view.getStr("Enter new email: "),
                val -> {
                    validator.validateEmailFormat(val);
                    validator.validateEmailSimilarity(email, val, model.getDataBase());
                }
        );
        InputHelper.FieldPrompt<Integer> agePrompt = new InputHelper.FieldPrompt<>(
                "Enter age",
                () -> view.getNum("Enter age: "),
                validator::validateAge
        );


        String firstName = InputHelper.prompt(firstNamePrompt);
        String lastName = InputHelper.prompt(lastNamePrompt);
        String newEmail = InputHelper.prompt(newEmailPrompt);
        int age = InputHelper.prompt(agePrompt);
        User updatedUser = model.updateUser(email, firstName, lastName, newEmail, age);
        view.printMsg("User updated: " + updatedUser.getUuid());
        view.printData(updatedUser);
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

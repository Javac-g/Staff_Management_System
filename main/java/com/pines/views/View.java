package com.pines.views;

import com.pines.models.UserPatter;

import java.util.Scanner;

public class View {
    private static final Scanner scanner = new Scanner(System.in);

    public void printMenu(){
        System.out.println("--- M E N U ---");
        System.out.println("1 - add user");
        System.out.println("2 - find user");
        System.out.println("3 - update user");
        System.out.println("4 - delete user");
        System.out.println("5 - exit");
    }
    public void printData(UserPatter user){
        System.out.println(user.toString());
    }
    public void printMsg(String msg){
        System.out.println(msg);
    }

    public String getStr(String msg){
        System.out.println(msg);
        return scanner.next();
    }
    public Integer getNum(String msg){
        System.out.println(msg);
        return scanner.nextInt();
    }


}

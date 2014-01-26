package com.nerj.oop.realty;

import com.nerj.oop.realty.exception.*;
import com.nerj.oop.realty.model.*;
import com.nerj.oop.realty.service.RealtyService;
import com.nerj.oop.realty.service.RealtyServiceImpl;
import com.nerj.oop.realty.service.UserService;
import com.nerj.oop.realty.service.UserServiceImpl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class App {
    private static RealtyService realtyService = new RealtyServiceImpl();
    private static UserService userService = new UserServiceImpl();

//    private static final String FILE_NAME = "users.dat";

    public static void main(String[] args){
        if (System.getProperty("os.name").toLowerCase().contains("win"))
            try {
                System.setOut(new java.io.PrintStream(System.out, true, "Cp866"));
            } catch (UnsupportedEncodingException e) {
                System.out.println(e.getMessage());
            }

        if (args.length == 2)
            userService.addUser(args[0], args[1]);

//        try {
//            File file = new File(FILE_NAME);
//            if (!file.exists()) file.createNewFile();
//        } catch (IOException e){
//            e.printStackTrace();
//        }

        User user = null;

        System.out.println("Добро пожаловать! Пожалуйста, авторизируйтесь.");
        while (true) {
            System.out.print("Имя пользователя: ");
            String username = System.console().readLine();
            System.out.print("Пароль: ");
            String password = System.console().readLine();

            try {
                user = userService.login(username, password);
                break;
            } catch (LoginFailedException ex){
                System.out.println(ex.getMessage());
            }
        }

        boolean isQuit = false;
        while (!isQuit){
            System.out.println(user.getName() + ", выберите требуемое действие:");
            System.out.println("1 - список недвижимости");
            System.out.println("2 - клиенты");
            System.out.println("3 - пользователи");
            System.out.println("q - выход");
            String choice = System.console().readLine();

            try {
                isQuit = mainMenuChooser(choice, user.getName());
            } catch (IncorrectChoiceException ex) {
                System.out.println(ex.getMessage());
            } catch (EmptyStringException ex) {
                System.out.println(ex.getMessage());
            } catch (NotExistsException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static boolean mainMenuChooser(String choice, String username) throws IncorrectChoiceException, EmptyStringException, NotExistsException {
        boolean isQuit = false;

        if (choice == null || choice.equals(""))
            throw new EmptyStringException();
        try {
            switch (choice.charAt(0)){
                case 'q' :  isQuit = true;
                            System.out.println("До свидания, " + username + ".");
                            break;
                case '1' :  realtyService.showRealty();
                            break;
                case '2' :  userService.showCustomers();
                            break;
                case '3' :  userService.showEmployees();
                            break;
                default :   throw new IncorrectChoiceException();
            }
        } catch (EmptyResultException ex){
            System.out.println(ex.getMessage());
            switch (choice.charAt(0)){
                case '1' :  System.out.print("Желаете добавить новый объект недвижимости? (y - да)");
                            String answer1 = System.console().readLine();
                            if (answer1 == null || answer1.equals("")) throw new EmptyStringException();
                            else if (answer1.charAt(0) == 'y') realtyService.addRealty();
                            break;
                case '2' :  System.out.print("Желаете добавить нового клиента? (y - да)");
                            String answer2 = System.console().readLine();
                            if (answer2 == null || answer2.equals("")) throw new EmptyStringException();
                            else if (answer2.charAt(0) == 'y') userService.addCustomer();
                            break;
                case '3' :  System.out.print("Желаете добавить нового пользователя? (y - да)");
                            String answer3 = System.console().readLine();
                            if (answer3 == null || answer3.equals("")) throw new EmptyStringException();
                            else if (answer3.charAt(0) == 'y') userService.addEmployee();
                            break;
                default :   throw new IncorrectChoiceException();
            }
        } catch (EmptyStringException ex) {
            System.out.println(ex.getMessage());
        } catch (IncorrectChoiceException ex) {
            System.out.println(ex.getMessage());
        } catch (NegativeNumberException ex) {
            System.out.println(ex.getMessage());
        }
        return isQuit;
    }
}

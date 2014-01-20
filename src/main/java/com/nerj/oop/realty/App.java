package com.nerj.oop.realty;

import com.nerj.oop.realty.exception.EmptyStringException;
import com.nerj.oop.realty.exception.IncorrectChoiceException;
import com.nerj.oop.realty.exception.LoginFailedException;
import com.nerj.oop.realty.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by vlad on 15.01.14.
 */
public class App {
    private static SessionFactory sessionFactory = null;
    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    private static Session session = null;
    private static Transaction tx = null;

    public static void main(String[] args){

        try {
            configureSessionFactory();
            session = sessionFactory.openSession();
        } catch (HibernateException ex){
            ex.printStackTrace();
            session = null;
        }

        User user = null;

        System.out.println("Добро пожаловать! Пожалуйста, авторизируйтесь.");
        while (true) {
            System.out.print("Имя пользователя: ");
            String username = System.console().readLine();
            System.out.print("Пароль:");
            String password = System.console().readLine();

            try {
                user = login(username, password);
                break;
            } catch (LoginFailedException ex){
                ex.printStackTrace();
                tx.rollback();
            }
        }

        boolean isQuit = false;
        while (!isQuit){
            System.out.println(user.getName() + ", выберите требуемое действие:");
            System.out.println("1 - квартиры");
            System.out.println("2 - частный сектор");
            System.out.println("3 - нежилые помещения");
            System.out.println("4 - клиенты");
            System.out.println("5 - пользователи");
            System.out.println("q - выход");
            String choice = System.console().readLine();

            try {
                isQuit = mainMenuChooser(choice, user.getName());
            } catch (IncorrectChoiceException ex) {
                ex.printStackTrace();
            } catch (EmptyStringException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static User login(String username, String password) throws LoginFailedException{
        tx = session.beginTransaction();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("username", username.toLowerCase()))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
        if (user == null)
            throw new LoginFailedException("Ошибка авторизации! Пользователь " + username + " не найден.");

        tx.commit();
        return user;
    }

    public static boolean mainMenuChooser(String choice, String username) throws IncorrectChoiceException, EmptyStringException {
        boolean isQuit = false;

        if (choice == null || choice.equals(""))
            throw new EmptyStringException();
        switch (choice.charAt(0)){
            case 'q' :  isQuit = true;
                        System.out.println("До свидания, " + username + ".");
                        break;
            case '1' :  getResidentialRealty();
                        break;
            case '2' :  getPrivateSectorRealty();
                        break;
            case '3' :  getCommercialRealty();
                        break;
            case '4' :  getCustomers();
                        break;
            case '5' :  getEmployees();
                        break;
            default :   throw new IncorrectChoiceException();
        }
        return isQuit;
    }

    public static void getResidentialRealty(){}

    public static void getPrivateSectorRealty(){}

    public static void getCommercialRealty(){}

    public static void getCustomers(){}

    public static void getEmployees(){}
}

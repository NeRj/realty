package com.nerj.oop.realty;

import com.nerj.oop.realty.dao.CustomerDAO;
import com.nerj.oop.realty.dao.CustomerDAOImpl;
import com.nerj.oop.realty.dao.RealtyDAO;
import com.nerj.oop.realty.dao.RealtyDAOImpl;
import com.nerj.oop.realty.exception.EmptyResultException;
import com.nerj.oop.realty.exception.EmptyStringException;
import com.nerj.oop.realty.exception.IncorrectChoiceException;
import com.nerj.oop.realty.exception.LoginFailedException;
import com.nerj.oop.realty.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.IOException;
import java.util.List;

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


    private static RealtyDAO realtyDAO = new RealtyDAOImpl();
    private static CustomerDAO customerDAO = new CustomerDAOImpl();


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

//        try {
//            Runtime.getRuntime().exec("clear");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
        try {
            switch (choice.charAt(0)){
                case 'q' :  isQuit = true;
                            System.out.println("До свидания, " + username + ".");
                            break;
                case '1' :  showResidentialRealty();
                            break;
                case '2' :  showPrivateSectorRealty();
                            break;
                case '3' :  showCommercialRealty();
                            break;
                case '4' :  showCustomers();
                            break;
                case '5' :  showEmployees();
                            break;
                default :   throw new IncorrectChoiceException();
            }
        } catch (EmptyResultException ex){
            ex.printStackTrace();
        }
        return isQuit;
    }

    public static void showResidentialRealty() throws EmptyResultException{
        List<ResidentialRealty> list = realtyDAO.getResidentialRealty();
        if (list == null) throw new EmptyResultException();
    }

    public static void showPrivateSectorRealty() throws EmptyResultException{
        List<PrivateSectorRealty> list = realtyDAO.getPrivateSectorRealty();
        if (list == null) throw new EmptyResultException();
    }

    public static void showCommercialRealty() throws EmptyResultException{
        List<CommercialRealty> list = realtyDAO.getCommercialRealty();
        if (list == null) throw new EmptyResultException();
    }

    public static void showCustomers() throws EmptyResultException{
        List<Customer> list = customerDAO.getCustomers();
        if (list == null) throw new EmptyResultException();
    }

    public static void showEmployees() throws EmptyResultException{
        List<Employee> list = customerDAO.getEmployees();
        if (list == null) throw new EmptyResultException();
        System.out.println("id\tимя\tдолжность");
        for (Employee employee : list)
            System.out.println(employee.getId() + "\t" + employee.getName() + "\t" + employee.getPosition());
    }
}

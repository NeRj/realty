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
            System.out.print("Пароль: ");
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
            System.out.println("1 - список недвижимости");
            System.out.println("2 - клиенты");
            System.out.println("3 - пользователи");
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
                case '1' :  showRealty();
                            break;
                case '2' :  showCustomers();
                            break;
                case '3' :  showEmployees();
                            break;
                default :   throw new IncorrectChoiceException();
            }
        } catch (EmptyResultException ex){
            ex.printStackTrace();
            switch (choice.charAt(0)){
                case '1' :  System.out.print("Желаете добавить новый объект недвижимости? (y - да)");
                            String answer1 = System.console().readLine();
                            if (answer1 == null || answer1.equals("")) throw new EmptyStringException();
                            else if (answer1.charAt(0) == 'y') addRealty();
                            break;
                case '2' :  System.out.print("Желаете добавить нового клиента? (y - да)");
                            String answer2 = System.console().readLine();
                            if (answer2 == null || answer2.equals("")) throw new EmptyStringException();
                            else if (answer2.charAt(0) == 'y') addCustomer();
                            break;
                case '3' :  System.out.print("Желаете добавить нового пользователя? (y - да)");
                            String answer3 = System.console().readLine();
                            if (answer3 == null || answer3.equals("")) throw new EmptyStringException();
                            else if (answer3.charAt(0) == 'y') addEmployee();
                            break;
                default :   throw new IncorrectChoiceException();
            }
        }
        return isQuit;
    }

    public static void showRealty() throws EmptyResultException{
        List<ResidentialRealty> residentialRealties = realtyDAO.getResidentialRealty();
        List<PrivateSectorRealty> privateSectorRealties = realtyDAO.getPrivateSectorRealty();
        List<CommercialRealty> commercialRealties = realtyDAO.getCommercialRealty();

        if (residentialRealties.isEmpty() && privateSectorRealties.isEmpty() && commercialRealties.isEmpty())
            throw new EmptyResultException();
        if (!residentialRealties.isEmpty()){
            System.out.println("КВАРТИРЫ:");
            System.out.println("id\tназвание\tадрес\tплощадь\tкомнаты\tжил. площадь\tплощадь кухни\tсанузел\tтип");
            for (ResidentialRealty residentialRealty : residentialRealties)
                System.out.println(residentialRealty.getId() + "\t" + residentialRealty.getName() + "\t" +
                                    residentialRealty.getAddress() + "\t" + residentialRealty.getArea() + "\t" +
                                    residentialRealty.getNumberOfRooms() + "\t" + residentialRealty.getResidentialArea() + "\t" +
                                    residentialRealty.getKitchenArea() + "\t" + residentialRealty.getTypeWC() + "\t" +
                                    residentialRealty.getSubtype());
        }
        if (!privateSectorRealties.isEmpty()){
            System.out.println("ЧАСТНЫЙ СЕКТОР:");
            System.out.println("id\tназвание\tадрес\tплощадь\tкомнаты\tжил. площадь\tприлегающая площадь\tэтажность\tсанузлы");
            for (PrivateSectorRealty privateSectorRealty : privateSectorRealties)
                System.out.println(privateSectorRealty.getId() + "\t" + privateSectorRealty.getName() + "\t" +
                        privateSectorRealty.getAddress() + "\t" + privateSectorRealty.getArea() + "\t" +
                        privateSectorRealty.getNumberOfRooms() + "\t" + privateSectorRealty.getResidentialArea() + "\t" +
                        privateSectorRealty.getNeighborhoodArea() + "\t" + privateSectorRealty.getNumberOfStoreys() + "\t" +
                        privateSectorRealty.getNumberOfWC());
        }
        if (!residentialRealties.isEmpty()){
            System.out.println("НЕЖИЛАЯ НЕДВИЖИМОСТЬ:");
            System.out.println("id\tназвание\tадрес\tплощадь\tкомнаты\tэтаж\tтип");
            for (CommercialRealty commercialRealty : commercialRealties)
                System.out.println(commercialRealty.getId() + "\t" + commercialRealty.getName() + "\t" +
                        commercialRealty.getAddress() + "\t" + commercialRealty.getArea() + "\t" +
                        commercialRealty.getNumberOfRooms() + "\t" + commercialRealty.getStorey() + "\t" +
                        commercialRealty.getSubtype());
        }
    }

    public static void showCustomers() throws EmptyResultException{
        List<CorporatePersonhood> corporatePersonhoods = customerDAO.getCorporatePersonhoods();
        List<NaturalPerson> naturalPersons = customerDAO.getNaturalPersons();
        if (corporatePersonhoods.isEmpty() && naturalPersons.isEmpty()) throw new EmptyResultException();
        if (!naturalPersons.isEmpty()){
            System.out.println("КЛИЕНТЫ (ЧАСТНЫЕ ЛИЦА):");
            System.out.println("id\tимя\tтелефон\tпаспорт\tдата рождения\tдоп. сведения");
            for (NaturalPerson naturalPerson : naturalPersons)
                System.out.println(naturalPerson.getId() + "\t" + naturalPerson.getName() + "\t" + naturalPerson.getPhone() + "\t" +
                                    naturalPerson.getPassport() + "\t" + naturalPerson.getBirthDate() + "\t" +
                                    naturalPerson.getAdditionalInfo());
        }
        if (!corporatePersonhoods.isEmpty()){
            System.out.println("КЛИЕНТЫ (ЮРИДИЧЕСКИЕ ЛИЦА):");
            System.out.println("id\tимя\tтелефон\tорганизация\tдата регистрации\tюр. адрес\tдоп. сведения");
            for (CorporatePersonhood corporatePersonhood : corporatePersonhoods)
                System.out.println(corporatePersonhood.getId() + "\t" + corporatePersonhood.getName() + "\t" +
                        corporatePersonhood.getPhone() + "\t" + corporatePersonhood.getOrganization() + "\t" +
                        corporatePersonhood.getFoundationDate() + "\t" + corporatePersonhood.getCorporateAddress() + "\t" +
                        corporatePersonhood.getAdditionalInfo());
        }
    }

    public static void showEmployees() throws EmptyResultException, EmptyStringException{
        List<Employee> list = customerDAO.getEmployees();
        if (list.isEmpty()) throw new EmptyResultException();
        System.out.println("ПОЛЬЗОВАТЕЛИ:");
        System.out.println("id\tимя\tдолжность");
        for (Employee employee : list)
            System.out.println(employee.getId() + "\t" + employee.getName() + "\t" + employee.getPosition());
        System.out.print("Для редактирования введите ID пользователя или '+' для добавления нового: ");
        String choice = System.console().readLine();
        if (choice == null || choice.equals(""))
            throw new EmptyStringException();
        else if (choice.charAt(0) == '+')
            addEmployee();
        else {
            try {
                Integer id = Integer.parseInt(choice);
                editEmployee(id);
            } catch (Exception ex){}
        }
    }

    public static void addRealty(){}

    public static void addCustomer(){}

    public static void addEmployee(){
        Employee employee = new Employee();
        System.out.print("Имя пользователя: ");
        employee.setUsername(System.console().readLine());
        System.out.print("Пароль: ");
        employee.setPassword(System.console().readLine());
        System.out.print("Имя: ");
        employee.setName(System.console().readLine());
        System.out.print("Должность: ");
        employee.setPosition(System.console().readLine());

        tx = session.beginTransaction();
        session.save(employee);
        tx.commit();
    }

    public static void editEmployee(int id){}
}

package com.nerj.oop.realty;

import com.nerj.oop.realty.dao.*;
import com.nerj.oop.realty.dao.UserDAOImpl;
import com.nerj.oop.realty.dao.UserDAO;
import com.nerj.oop.realty.exception.EmptyResultException;
import com.nerj.oop.realty.exception.EmptyStringException;
import com.nerj.oop.realty.exception.IncorrectChoiceException;
import com.nerj.oop.realty.exception.LoginFailedException;
import com.nerj.oop.realty.model.*;
import dnl.utils.text.table.TextTable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

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
    private static UserDAO userDAO = new UserDAOImpl();


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
        session.close();
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
        } catch (EmptyStringException ex) { ex.printStackTrace(); }
        catch (IncorrectChoiceException ex) { ex.printStackTrace(); }
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
            Object[][] data = new Object[residentialRealties.size()][ResidentialRealty.FIELD_NAMES.length];
            for (int i = 0; i < residentialRealties.size(); i++)
                data[i] = residentialRealties.get(i).toArray();
            printTable(ResidentialRealty.FIELD_NAMES, data);
        }
        if (!privateSectorRealties.isEmpty()){
            System.out.println("ЧАСТНЫЙ СЕКТОР:");
            Object[][] data = new Object[privateSectorRealties.size()][PrivateSectorRealty.FIELD_NAMES.length];
            for (int i = 0; i < privateSectorRealties.size(); i++)
                data[i] = privateSectorRealties.get(i).toArray();
            printTable(PrivateSectorRealty.FIELD_NAMES, data);
        }
        if (!residentialRealties.isEmpty()){
            System.out.println("НЕЖИЛАЯ НЕДВИЖИМОСТЬ:");
            Object[][] data = new Object[residentialRealties.size()][ResidentialRealty.FIELD_NAMES.length];
            for (int i = 0; i < residentialRealties.size(); i++)
                data[i] = residentialRealties.get(i).toArray();
            printTable(ResidentialRealty.FIELD_NAMES, data);
        }
    }

    public static void showCustomers() throws EmptyResultException{
        List<CorporatePersonhood> corporatePersonhoods = userDAO.getCorporatePersonhoods();
        List<NaturalPerson> naturalPersons = userDAO.getNaturalPersons();
        if (corporatePersonhoods.isEmpty() && naturalPersons.isEmpty()) throw new EmptyResultException();
        if (!naturalPersons.isEmpty()){
            System.out.println("КЛИЕНТЫ (ЧАСТНЫЕ ЛИЦА):");
            Object[][] data = new Object[naturalPersons.size()][NaturalPerson.FIELD_NAMES.length];
            for (int i = 0; i < naturalPersons.size(); i++)
                data[i] = naturalPersons.get(i).toArray();
            printTable(NaturalPerson.FIELD_NAMES, data);
        }
        if (!corporatePersonhoods.isEmpty()){
            System.out.println("КЛИЕНТЫ (ЮРИДИЧЕСКИЕ ЛИЦА):");
            Object[][] data = new Object[corporatePersonhoods.size()][CorporatePersonhood.FIELD_NAMES.length];
            for (int i = 0; i < corporatePersonhoods.size(); i++)
                data[i] = corporatePersonhoods.get(i).toArray();
            printTable(CorporatePersonhood.FIELD_NAMES, data);
        }
    }

    public static void showEmployees() throws EmptyResultException, EmptyStringException, IncorrectChoiceException{
        List<Employee> list = userDAO.listEmployees();
        if (list.isEmpty()) throw new EmptyResultException();
        System.out.println("ПОЛЬЗОВАТЕЛИ:");
        Object[][] data = new Object[list.size()][Employee.FIELD_NAMES.length];
        for (int i = 0; i < list.size(); i++)
            data[i] = list.get(i).toArray();
        printTable(Employee.FIELD_NAMES, data);
        System.out.print("e - изменить; d - удалить, a - добавить: ");
        String choice = System.console().readLine();
        if (choice == null || choice.equals(""))
            throw new EmptyStringException();
        else if (choice.charAt(0) == 'a')
            addEmployee();
        else if (choice.charAt(0) == 'd'){
            System.out.print("Введите ID пользователя: ");
            String subChoice = System.console().readLine();
            if (subChoice == null || choice.equals(""))
                throw new EmptyStringException();
            try {
                Integer id = Integer.parseInt(subChoice);
                deleteEmployee(id);
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
        } else if (choice.charAt(0) == 'e'){
            System.out.print("Введите ID пользователя: ");
            String subChoice = System.console().readLine();
            if (subChoice == null || choice.equals(""))
                throw new EmptyStringException();
            try {
                Integer id = Integer.parseInt(subChoice);
                editEmployee(id);
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
        } else throw new IncorrectChoiceException();
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

        userDAO.addEmployee(employee);
        System.out.println("Пользователь добавлен!");
    }

    public static void editEmployee(int id){
        Employee employee = userDAO.getEmployee(id);

        String in = null;
        System.out.print("Имя пользователя [" + employee.getUsername() + "]: ");
        in = System.console().readLine();
        if (in != null && !in.isEmpty() && !in.equals(" "))
            employee.setUsername(in);
        System.out.print("Пароль [" + employee.getPassword() + "]: ");
        in = System.console().readLine();
        if (in != null && !in.isEmpty() && !in.equals(" "))
            employee.setPassword(in);
        System.out.print("Имя [" + employee.getName() + "]: ");
        in = System.console().readLine();
        if (in != null && !in.isEmpty() && !in.equals(" "))
            employee.setName(in);
        System.out.print("Должность [" + employee.getPosition() + "]: ");
        in = System.console().readLine();
        if (in != null && !in.isEmpty() && !in.equals(" "))
            employee.setPosition(in);

        userDAO.updateEmployee(employee);
        System.out.println("Пользователь изменен!");
    }

    public static void deleteEmployee(int id){
        userDAO.removeEmployee(id);
        System.out.println("Пользователь удален!");
    }


    public static void printTable(String[] columnNames, Object[][] data){
        TextTable tt = new TextTable(columnNames, data);
        tt.setSort(0);
        tt.printTable();
    }
}

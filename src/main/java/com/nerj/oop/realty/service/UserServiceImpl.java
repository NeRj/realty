package com.nerj.oop.realty.service;

import com.nerj.oop.realty.dao.UserDAO;
import com.nerj.oop.realty.dao.UserDAOImpl;
import com.nerj.oop.realty.exception.*;
import com.nerj.oop.realty.model.*;
import dnl.utils.text.table.TextTable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl(){
        userDAO = new UserDAOImpl();
    }

    @Override
    public void addUser(String username, String password) {
        User user = new User("Admin", username, password);
        userDAO.addUser(user);
    }

    @Override
    public User login(String username, String password) throws LoginFailedException {
        User user = userDAO.loginUser(username, password);
        if (user == null)
            throw new LoginFailedException("Ошибка авторизации! Пользователь " + username + " не найден.");
        return user;
    }

    @Override
    public void showCustomers() throws EmptyResultException, EmptyStringException, IncorrectChoiceException, NotExistsException, NegativeNumberException {
        List<CorporatePersonhood> corporatePersonhoods = userDAO.listCorporatePersonhoods();
        List<NaturalPerson> naturalPersons = userDAO.listNaturalPersons();
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

        System.out.print("e - изменить; d - удалить, a - добавить: ");
        String choice = System.console().readLine();
        if (choice == null || choice.equals(""))
            throw new EmptyStringException();
        else if (choice.charAt(0) == 'a')
            addEmployee();
        else if (choice.charAt(0) == 'd'){
            System.out.print("Введите ID клиента: ");
            String subChoice = System.console().readLine();
            if (subChoice == null || choice.equals(""))
                throw new EmptyStringException();
            try {
                Integer id = Integer.parseInt(subChoice);
                if (id < 0) throw new NegativeNumberException();
                if (userDAO.isExists(id))
                    deleteCustomer(id);
                else throw new NotExistsException();
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        } else if (choice.charAt(0) == 'e'){
            System.out.print("Введите ID клиента: ");
            String subChoice = System.console().readLine();
            if (subChoice == null || choice.equals(""))
                throw new EmptyStringException();
            try {
                Integer id = Integer.parseInt(subChoice);
                if (id < 0) throw new NegativeNumberException();
                if (userDAO.isExists(id))
                    editCustomer(id);
                else throw new NotExistsException();
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        } else throw new IncorrectChoiceException();
    }

    @Override
    public void showEmployees() throws EmptyResultException, EmptyStringException, IncorrectChoiceException, NotExistsException, NegativeNumberException {
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
                if (id < 0) throw new NegativeNumberException();
                if (userDAO.isExists(id))
                    deleteEmployee(id);
                else throw new NotExistsException();
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        } else if (choice.charAt(0) == 'e'){
            System.out.print("Введите ID пользователя: ");
            String subChoice = System.console().readLine();
            if (subChoice == null || choice.equals(""))
                throw new EmptyStringException();
            try {
                Integer id = Integer.parseInt(subChoice);
                if (id < 0) throw new NegativeNumberException();
                if (userDAO.isExists(id))
                    editEmployee(id);
                else throw new NotExistsException();
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        } else throw new IncorrectChoiceException();
    }

    @Override
    public void addCustomer(){
        Customer customer = new Customer();
        System.out.print("Имя: ");
        customer.setName(System.console().readLine());
        System.out.print("Номер телефона: ");
        customer.setPhone(System.console().readLine());
        while (true){
            System.out.print("Тип (c - юр. лицо, n - физ. лицо): ");
            String choice = System.console().readLine();
            if (choice == null || choice.isEmpty())
                System.out.println("Выберите тип!");
            else if (choice.charAt(0) == 'c'){
                customer.setType("corporate");
                CorporatePersonhood corporatePersonhood = new CorporatePersonhood(customer);
                System.out.print("Организация: ");
                corporatePersonhood.setOrganization(System.console().readLine());
                while (true){
                    System.out.print("Дата регистрации (ДД.ММ.ГГГГ): ");
                    try {
                        corporatePersonhood.setFoundationDate(parseDate(System.console().readLine()));
                        break;
                    } catch (IncorrectDateFormatException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.print("Юридический адрес: ");
                corporatePersonhood.setCorporateAddress(System.console().readLine());
                System.out.print("Доп. информация: ");
                corporatePersonhood.setAdditionalInfo(System.console().readLine());

                userDAO.addCorporatePersonhood(corporatePersonhood);
                break;
            } else if (choice.charAt(0) == 'n'){
                customer.setType("natural");
                NaturalPerson naturalPerson = new NaturalPerson(customer);
                System.out.print("Номер паспорта: ");
                naturalPerson.setPassport(System.console().readLine());
                while (true){
                    System.out.print("Дата рождения (ДД.ММ.ГГГГ): ");
                    try {
                        naturalPerson.setBirthDate(parseDate(System.console().readLine()));
                        break;
                    } catch (IncorrectDateFormatException e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.print("Доп. информация: ");
                naturalPerson.setAdditionalInfo(System.console().readLine());

                userDAO.addNaturalPerson(naturalPerson);
                break;
            } else System.out.println("Выберите тип!");
        }
        System.out.println("Пользователь добавлен!");
    }

    @Override
    public void addEmployee(){
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

    @Override
    public void editCustomer(int id){
        String type = userDAO.getCustomerType(id);
        if (type.equals("corporate")){
            CorporatePersonhood corporatePersonhood = userDAO.getCorporatePersonhood(id);

            String in = null;
            System.out.print("Имя [" + corporatePersonhood.getName() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                corporatePersonhood.setName(in);
            System.out.print("Номер телефона [" + corporatePersonhood.getPhone() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                corporatePersonhood.setPhone(in);
            System.out.print("Организация [" + corporatePersonhood.getOrganization() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                corporatePersonhood.setOrganization(in);
            while (true){
                System.out.print("Дата регистрации [" + corporatePersonhood.getFoundationDate() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" "))
                        corporatePersonhood.setFoundationDate(parseDate(in));
                    break;
                } catch (IncorrectDateFormatException e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.print("Юридический адрес [" + corporatePersonhood.getCorporateAddress() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                corporatePersonhood.setCorporateAddress(in);
            System.out.print("Доп. информация [" + corporatePersonhood.getAdditionalInfo() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                corporatePersonhood.setAdditionalInfo(in);

            userDAO.updateCorporatePersonhood(corporatePersonhood);
        }
        else if (type.equals("natural")){
            NaturalPerson naturalPerson = userDAO.getNaturalPerson(id);

            String in = null;
            System.out.print("Имя [" + naturalPerson.getName() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                naturalPerson.setName(in);
            System.out.print("Номер телефона [" + naturalPerson.getPhone() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                naturalPerson.setPhone(in);
            System.out.print("Номер паспорта [" + naturalPerson.getPassport() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                naturalPerson.setPassport(in);
            while (true){
                System.out.print("Дата рождения [" + naturalPerson.getBirthDate() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" "))
                        naturalPerson.setBirthDate(parseDate(in));
                    break;
                } catch (IncorrectDateFormatException e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.print("Доп. информация [" + naturalPerson.getAdditionalInfo() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                naturalPerson.setAdditionalInfo(in);

            userDAO.updateNaturalPerson(naturalPerson);
        }

        System.out.println("Клиент изменен!");
    }

    @Override
    public void editEmployee(int id){
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

    @Override
    public void deleteCustomer(int id){
        userDAO.removeCustomer(id);
        System.out.println("Клиент удален!");
    }

    @Override
    public void deleteEmployee(int id){
        userDAO.removeEmployee(id);
        System.out.println("Пользователь удален!");
    }


    public void printTable(String[] columnNames, Object[][] data){
        TextTable tt = new TextTable(columnNames, data);
        tt.setSort(0);
        tt.printTable();
    }

    public Date parseDate(String dateString) throws IncorrectDateFormatException{
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = format.parse(dateString);
            return date;
        } catch (ParseException e){
            throw new IncorrectDateFormatException();
        }
    }
}

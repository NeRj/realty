package com.nerj.oop.realty.service;

import com.nerj.oop.realty.exception.*;
import com.nerj.oop.realty.model.*;

public interface UserService {
    public void addUser(String username, String password);
    public User login(String username, String password) throws LoginFailedException;
    public void showCustomers() throws EmptyResultException, EmptyStringException, IncorrectChoiceException, NotExistsException, NegativeNumberException;
    public void showEmployees() throws EmptyResultException, EmptyStringException, IncorrectChoiceException, NotExistsException, NegativeNumberException;
    public void addCustomer();
    public void addEmployee();
    public void editCustomer(int id);
    public void editEmployee(int id);
    public void deleteCustomer(int id);
    public void deleteEmployee(int id);
}

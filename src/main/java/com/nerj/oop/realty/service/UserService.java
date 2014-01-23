package com.nerj.oop.realty.service;

import com.nerj.oop.realty.exception.EmptyResultException;
import com.nerj.oop.realty.exception.EmptyStringException;
import com.nerj.oop.realty.exception.IncorrectChoiceException;
import com.nerj.oop.realty.exception.LoginFailedException;
import com.nerj.oop.realty.model.*;

/**
 * Created by LipenVK on 23.01.14.
 */
public interface UserService {
    public User login(String username, String password) throws LoginFailedException;
    public void showCustomers() throws EmptyResultException, EmptyStringException, IncorrectChoiceException;
    public void showEmployees() throws EmptyResultException, EmptyStringException, IncorrectChoiceException ;
    public void addCustomer();
    public void addEmployee();
    public void editCustomer(int id);
    public void editEmployee(int id);
    public void deleteCustomer(int id);
    public void deleteEmployee(int id);
}

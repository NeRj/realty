package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.Customer;
import com.nerj.oop.realty.model.Employee;

import java.util.List;

/**
 * Created by vlad on 21.01.14.
 */
public interface CustomerDAO {
    public List<Customer> getCustomers();
    public List<Employee> getEmployees();
}

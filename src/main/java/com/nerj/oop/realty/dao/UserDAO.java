package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.CorporatePersonhood;
import com.nerj.oop.realty.model.Customer;
import com.nerj.oop.realty.model.Employee;
import com.nerj.oop.realty.model.NaturalPerson;

import java.util.List;

/**
 * Created by vlad on 21.01.14.
 */
public interface UserDAO {
    public List<NaturalPerson> getNaturalPersons();
    public List<CorporatePersonhood> getCorporatePersonhoods();
    public List<Employee> listEmployees();
    public Employee getEmployee(int id);
    public void addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void removeEmployee(int id);
}

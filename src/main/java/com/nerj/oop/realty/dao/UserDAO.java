package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.*;

import java.util.List;

public interface UserDAO {
    public User loginUser(String username, String password);
    public String getCustomerType(int id);
    public List<NaturalPerson> listNaturalPersons();
    public NaturalPerson getNaturalPerson(int id);
    public void addNaturalPerson(NaturalPerson naturalPerson);
    public void updateNaturalPerson(NaturalPerson naturalPerson);
    public List<CorporatePersonhood> listCorporatePersonhoods();
    public CorporatePersonhood getCorporatePersonhood(int id);
    public void addCorporatePersonhood(CorporatePersonhood corporatePersonhood);
    public void updateCorporatePersonhood(CorporatePersonhood corporatePersonhood);
    public void removeCustomer(int id);
    public List<Employee> listEmployees();
    public Employee getEmployee(int id);
    public void addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void removeEmployee(int id);
}

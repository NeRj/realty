package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.*;

import java.util.List;

public interface UserDAO {
    public boolean isExists(int id);
    public void addUser(User user);
    public User loginUser(String username, String password);
    public String getCustomerType(int id);
    public List<NaturalPerson> listNaturalPersons();
    public NaturalPerson getNaturalPerson(int id);
    public void addNaturalPerson(NaturalPerson naturalPerson);
    public void updateNaturalPerson(int id, NaturalPerson naturalPerson);
    public List<CorporatePersonhood> listCorporatePersonhoods();
    public CorporatePersonhood getCorporatePersonhood(int id);
    public void addCorporatePersonhood(CorporatePersonhood corporatePersonhood);
    public void updateCorporatePersonhood(int id, CorporatePersonhood corporatePersonhood);
    public List<Employee> listEmployees();
    public Employee getEmployee(int id);
    public void addEmployee(Employee employee);
    public void updateEmployee(int id, Employee employee);
    public void removeUser(int id);
}

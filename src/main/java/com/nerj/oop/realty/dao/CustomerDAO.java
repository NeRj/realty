package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.CorporatePersonhood;
import com.nerj.oop.realty.model.Customer;
import com.nerj.oop.realty.model.Employee;
import com.nerj.oop.realty.model.NaturalPerson;

import java.util.List;

/**
 * Created by vlad on 21.01.14.
 */
public interface CustomerDAO {
    public List<NaturalPerson> getNaturalPersons();
    public List<CorporatePersonhood> getCorporatePersonhoods();
    public List<Employee> getEmployees();
}

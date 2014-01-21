package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.CorporatePersonhood;
import com.nerj.oop.realty.model.Customer;
import com.nerj.oop.realty.model.Employee;
import com.nerj.oop.realty.model.NaturalPerson;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

/**
 * Created by vlad on 21.01.14.
 */
public class CustomerDAOImpl implements CustomerDAO{
    private static SessionFactory sessionFactory = null;
    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    private static Session session = null;
    private static Transaction tx = null;


    public CustomerDAOImpl(){
        try {
            configureSessionFactory();
            session = sessionFactory.openSession();
        } catch (HibernateException ex){
            ex.printStackTrace();
            session = null;
        }
    }

    @Override
    public List<NaturalPerson> getNaturalPersons() {
        tx = session.beginTransaction();
        List<NaturalPerson> list = session.createCriteria(NaturalPerson.class).list();
        tx.commit();
        return list;
    }

    @Override
    public List<CorporatePersonhood> getCorporatePersonhoods() {
        tx = session.beginTransaction();
        List<CorporatePersonhood> list = session.createCriteria(CorporatePersonhood.class).list();
        tx.commit();
        return list;
    }

    @Override
    public List<Employee> getEmployees() {
        tx = session.beginTransaction();
        List<Employee> list = session.createCriteria(Employee.class).list();
        tx.commit();
        return list;
    }
}

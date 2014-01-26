package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
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

//    private static final String FILE_NAME = "users.dat";


    public UserDAOImpl(){
        try {
            configureSessionFactory();
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
            session = null;
        }
    }

    @Override
    public boolean isExists(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        tx.commit();
        session.close();
        return user != null;
    }

    @Override
    public void addUser(User user) {
//        try {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
//            int size = 0;
//            List<User> list = new ArrayList<User>();
//            try {
//                size = ois.readInt();
//                for (int i = 0; i < size; i++)
//                    list.add((User) ois.readObject());
//            } catch (EOFException e){
//                e.printStackTrace();
//            } finally {
//                ois.close();
//            }
//            user.setId(size);
//            list.add(user);
//
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
//            oos.writeInt(size + 1);
//            for (int i = 0; i < size + 1; i++)
//                oos.writeObject(list.get(i));
//            oos.flush();
//            oos.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

    @Override
    public User loginUser(String username, String password) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("username", username.toLowerCase()))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
        tx.commit();
        session.close();
        return user;
    }

    @Override
    public String getCustomerType(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        String type = ((Customer) session.load(Customer.class, id)).getType();
        tx.commit();
        session.close();
        return type;
    }

    @Override
    public List<NaturalPerson> listNaturalPersons() {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<NaturalPerson> list = session.createCriteria(NaturalPerson.class).list();
        tx.commit();
        session.close();
        return list;
    }

    @Override
    public NaturalPerson getNaturalPerson(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        NaturalPerson naturalPerson = new NaturalPerson((NaturalPerson) session.load(NaturalPerson.class, id), true);
        tx.commit();
        session.close();
        return naturalPerson;
    }

    @Override
    public void addNaturalPerson(NaturalPerson naturalPerson) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(naturalPerson);
        tx.commit();
        session.close();
    }

    @Override
    public void updateNaturalPerson(NaturalPerson naturalPerson) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.update(naturalPerson);
        tx.commit();
        session.close();
    }

    @Override
    public List<CorporatePersonhood> listCorporatePersonhoods() {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<CorporatePersonhood> list = session.createCriteria(CorporatePersonhood.class).list();
        tx.commit();
        session.close();
        return list;
    }

    @Override
    public CorporatePersonhood getCorporatePersonhood(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        CorporatePersonhood corporatePersonhood = new CorporatePersonhood((CorporatePersonhood) session.load(CorporatePersonhood.class, id), true);
        tx.commit();
        session.close();
        return corporatePersonhood;
    }

    @Override
    public void addCorporatePersonhood(CorporatePersonhood corporatePersonhood) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(corporatePersonhood);
        tx.commit();
        session.close();
    }

    @Override
    public void updateCorporatePersonhood(CorporatePersonhood corporatePersonhood) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.update(corporatePersonhood);
        tx.commit();
        session.close();
    }

    @Override
    public void removeCustomer(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Customer customer = (Customer) session.load(Customer.class, id);
        if (customer.getType().equals("corporate"))
            session.delete(session.load(CorporatePersonhood.class, id));
        else if (customer.getType().equals("natural"))
            session.delete(session.load(NaturalPerson.class, id));
        tx.commit();
        session.close();
    }

    @Override
    public List<Employee> listEmployees() {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<Employee> list = session.createCriteria(Employee.class).list();
        tx.commit();
        session.close();
        return list;
    }

    @Override
    public Employee getEmployee(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Employee employee = new Employee((Employee) session.load(Employee.class, id));
        tx.commit();
        session.close();
        return employee;
    }

    @Override
    public void addEmployee(Employee employee) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(employee);
        tx.commit();
        session.close();
    }

    @Override
    public void updateEmployee(Employee employee) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.update(employee);
        tx.commit();
        session.close();
    }

    @Override
    public void removeEmployee(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.delete(session.load(Employee.class, id));
        tx.commit();
        session.close();
    }
}

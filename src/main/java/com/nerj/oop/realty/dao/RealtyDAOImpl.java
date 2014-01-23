package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.CommercialRealty;
import com.nerj.oop.realty.model.PrivateSectorRealty;
import com.nerj.oop.realty.model.ResidentialRealty;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

public class RealtyDAOImpl implements RealtyDAO {
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


    public RealtyDAOImpl(){
        try {
            configureSessionFactory();
        } catch (HibernateException ex){
            ex.printStackTrace();
            session = null;
        }
    }

    @Override
    public List<ResidentialRealty> listResidentialRealty() {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<ResidentialRealty> list = session.createCriteria(ResidentialRealty.class).list();
        tx.commit();
        session.close();
        return list;
    }

    @Override
    public List<PrivateSectorRealty> listPrivateSectorRealty() {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<PrivateSectorRealty> list = session.createCriteria(PrivateSectorRealty.class).list();
        tx.commit();
        session.close();
        return list;
    }

    @Override
    public List<CommercialRealty> listCommercialRealty() {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<CommercialRealty> list = session.createCriteria(CommercialRealty.class).list();
        tx.commit();
        session.close();
        return list;
    }

    @Override
    public String getRealtyType(int id) {
        return null;
    }

    @Override
    public ResidentialRealty getResidentialRealty(int id) {
        return null;
    }

    @Override
    public void addResidentialRealty(ResidentialRealty residentialRealty) {

    }

    @Override
    public void updateResidentialRealty(ResidentialRealty residentialRealty) {

    }

    @Override
    public PrivateSectorRealty getPrivateSectorRealty(int id) {
        return null;
    }

    @Override
    public void addPrivateSectorRealty(PrivateSectorRealty privateSectorRealty) {

    }

    @Override
    public void updatePrivateSectorRealty(PrivateSectorRealty privateSectorRealty) {

    }

    @Override
    public CommercialRealty getCommercialRealty(int id) {
        return null;
    }

    @Override
    public void addCommercialRealty(CommercialRealty commercialRealty) {

    }

    @Override
    public void updateCommercialRealty(CommercialRealty commercialRealty) {

    }

    @Override
    public void removeRealty(int id) {

    }
}

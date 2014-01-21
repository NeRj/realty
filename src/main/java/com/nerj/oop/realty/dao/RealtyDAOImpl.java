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

/**
 * Created by vlad on 21.01.14.
 */
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
            session = sessionFactory.openSession();
        } catch (HibernateException ex){
            ex.printStackTrace();
            session = null;
        }
    }

    @Override
    public List<ResidentialRealty> getResidentialRealty() {
        tx = session.beginTransaction();
        List<ResidentialRealty> list = session.createCriteria(ResidentialRealty.class).list();
        tx.commit();
        return list;
    }

    @Override
    public List<PrivateSectorRealty> getPrivateSectorRealty() {
        tx = session.beginTransaction();
        List<PrivateSectorRealty> list = session.createCriteria(PrivateSectorRealty.class).list();
        tx.commit();
        return list;
    }

    @Override
    public List<CommercialRealty> getCommercialRealty() {
        tx = session.beginTransaction();
        List<CommercialRealty> list = session.createCriteria(CommercialRealty.class).list();
        tx.commit();
        return list;
    }
}

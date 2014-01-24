package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.CommercialRealty;
import com.nerj.oop.realty.model.PrivateSectorRealty;
import com.nerj.oop.realty.model.Realty;
import com.nerj.oop.realty.model.ResidentialRealty;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
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
            System.out.println(ex.getMessage());
            session = null;
        }
    }

    @Override
    public boolean isExists(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Realty realty = (Realty) session.createCriteria(Realty.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        tx.commit();
        session.close();
        return realty != null;
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
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        String type = ((Realty) session.load(Realty.class, id)).getType();
        tx.commit();
        session.close();
        return type;
    }

    @Override
    public ResidentialRealty getResidentialRealty(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        ResidentialRealty residentialRealty = new ResidentialRealty((ResidentialRealty) session.load(ResidentialRealty.class, id), true);
        tx.commit();
        session.close();
        return residentialRealty;
    }

    @Override
    public void addResidentialRealty(ResidentialRealty residentialRealty) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(residentialRealty);
        tx.commit();
        session.close();
    }

    @Override
    public void updateResidentialRealty(ResidentialRealty residentialRealty) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.update(residentialRealty);
        tx.commit();
        session.close();
    }

    @Override
    public PrivateSectorRealty getPrivateSectorRealty(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        PrivateSectorRealty privateSectorRealty = new PrivateSectorRealty((PrivateSectorRealty) session.load(PrivateSectorRealty.class, id), true);
        tx.commit();
        session.close();
        return privateSectorRealty;
    }

    @Override
    public void addPrivateSectorRealty(PrivateSectorRealty privateSectorRealty) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(privateSectorRealty);
        tx.commit();
        session.close();
    }

    @Override
    public void updatePrivateSectorRealty(PrivateSectorRealty privateSectorRealty) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.update(privateSectorRealty);
        tx.commit();
        session.close();
    }

    @Override
    public CommercialRealty getCommercialRealty(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        CommercialRealty commercialRealty = new CommercialRealty((CommercialRealty) session.load(CommercialRealty.class, id), true);
        tx.commit();
        session.close();
        return commercialRealty;
    }

    @Override
    public void addCommercialRealty(CommercialRealty commercialRealty) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(commercialRealty);
        tx.commit();
        session.close();
    }

    @Override
    public void updateCommercialRealty(CommercialRealty commercialRealty) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.update(commercialRealty);
        tx.commit();
        session.close();
    }

    @Override
    public void removeRealty(int id) {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Realty customer = (Realty) session.load(Realty.class, id);
        if (customer.getType().equals("residential"))
            session.delete(session.load(ResidentialRealty.class, id));
        else if (customer.getType().equals("private"))
            session.delete(session.load(PrivateSectorRealty.class, id));
        else if (customer.getType().equals("commercial"))
            session.delete(session.load(CommercialRealty.class, id));
        tx.commit();
        session.close();
    }
}

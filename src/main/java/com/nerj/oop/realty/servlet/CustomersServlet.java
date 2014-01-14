package com.nerj.oop.realty.servlet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by LipenVK on 08.01.14.
 */
public class CustomersServlet extends javax.servlet.http.HttpServlet {
    private static SessionFactory sessionFactory = null;
    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    private static String page = "customers.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userid") == null)
            response.sendRedirect("index.jsp");
        else {
            configureSessionFactory();

            Session session = null;
            Transaction tx = null;

            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");

            try {
                session = sessionFactory.openSession();
                tx = session.beginTransaction();



                session.flush();
                tx.commit();
            } catch (Exception ex){
                ex.printStackTrace();
                if (tx != null)
                    tx.rollback();
            } finally {
                if (session != null)
                    session.close();
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            if (dispatcher != null)
                dispatcher.forward(request, response);
        }
    }
}

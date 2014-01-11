package com.nerj.oop.realty.servlet;

import com.nerj.oop.realty.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by LipenVK on 11.01.14.
 */
public class LoginServlet extends HttpServlet {
    private static SessionFactory sessionFactory = null;
    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    private static String page = "realty.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        configureSessionFactory();

        Session session = null;
        Transaction tx = null;

        response.setContentType("text/html");
        request.setAttribute("username", request.getParameter("username"));
        request.setAttribute("password", request.getParameter("password"));

        try {
            session = sessionFactory.openSession();
            request.setAttribute("log", "session");
            tx = session.beginTransaction();
            request.setAttribute("log", "tx");

            User user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("username", request.getParameter("username").toLowerCase()))
                    .add(Restrictions.eq("password", request.getParameter("password")))
                    .uniqueResult();
            request.setAttribute("user", user);

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
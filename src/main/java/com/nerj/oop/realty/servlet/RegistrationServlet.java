package com.nerj.oop.realty.servlet;

import com.nerj.oop.realty.model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LipenVK on 13.01.14.
 */
public class RegistrationServlet extends HttpServlet {
    private static SessionFactory sessionFactory = null;
    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

//    private static String page = "register.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        configureSessionFactory();

        Session session = null;
        Transaction tx = null;

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Employee employee = new Employee(request.getParameter("username"), request.getParameter("password"),
                                                request.getParameter("name"), request.getParameter("position"));
            session.save(employee);

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

//        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
//        if (dispatcher != null)
//            dispatcher.forward(request, response);

        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

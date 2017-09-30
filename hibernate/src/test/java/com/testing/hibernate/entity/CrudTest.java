package com.testing.hibernate.entity;

import com.testing.hibernate.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by davidqi on 9/30/17.
 */
// test cases are executed in a sequential way based on their names
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CrudTest extends AbstractOnH2Test{

    private static final Logger logger = LoggerFactory.getLogger(EntityTest.class);
    private static Server h2Server = null;
    private static Server h2WebServer = null;

    @Test
    public void test001Create() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        for (int i = 0;  i < 10; i++) {
            User user = new User();
            user.setName("User" + i);

        }

        session.getTransaction().commit();
        session.close();
    }


    @Test
    public void test002Get() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 10);
        System.out.println(user.getName());

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void test003Update() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 12);
        user.setName("New Name");

        session.update(user);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void test004Remove() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 11);

        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }



}

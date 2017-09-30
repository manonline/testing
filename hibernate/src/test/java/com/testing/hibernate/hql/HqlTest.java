package com.testing.hibernate.hql;

import com.testing.hibernate.entity.EntityTest;
import com.testing.hibernate.entity.User;
import com.testing.hibernate.test.TestUtil;
import com.testing.hibernate.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class HqlTest {

    private static final Logger logger = LoggerFactory.getLogger(EntityTest.class);
    private static Server h2Server = null;
    private static Server h2WebServer = null;

    @BeforeClass
    public static void setup() {
        logger.info("Setup for Test {} ...", EntityTest.class);

        // enable remote access to H2 Server
        h2Server = TestUtil.startTcpServerForH2(9092);
        h2WebServer = TestUtil.startWebServerForH2(8082);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // create 10 entities
        for (int i = 1;  i <= 30; i++) {
            if ((i % 2 == 0)) {
                continue;
            }

            User user = new User();
            user.setName("User" + i);

            session.save(user);
        }

        session.getTransaction().commit();
        session.close();

    }

    @AfterClass
    public static void cleanup() {
        logger.info("Cleanup for Test {}", EntityTest.class);

        // shutdown remote access to H2 Server
        if (h2Server != null) {
            h2Server.stop();
        }

        if (h2WebServer != null) {
            h2WebServer.stop();
        }
    }

    @Before
    public void beforeTestCase() {

    }

    @After
    public void afterTestCase() {
        //server.stop();

    }

    @Test
    public void testHqlSimple() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // use the entity name rather than the class or table name
        // use property name rather than column name, column name also works but without mapping, i.e. table.column
        Query query = session.createQuery("from UserEntity where name is NOT NULL");

        // elements in the list is determined by the result set, in this case is User; Should have been specified
        List users = query.list();

        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(5, users.size());
    }

    @Test
    public void testSelectObjects() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query query = session.createQuery("from UserEntity where name is NOT NULL");
        List<User> users = (List<User>) query.list();

        session.getTransaction().commit();
        session.close();


        for (User u : users) {
            System.out.println(u.getName());
        }
    }

    @Test
    public void testSelectColumn() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query query = session.createQuery("select name from UserEntity where name is NOT NULL");

        // element in the result set is String
        List<String> userNames = (List<String>) query.list();

        session.getTransaction().commit();
        session.close();

        String name;
        int i = 0;

        Iterator itr = userNames.iterator();
        while (itr.hasNext()) {
            name = (String) itr.next();
            i++;
            Assert.assertEquals("User" + (i * 2 - 1), name);
        }
    }

    @Test
    public void testSelectColumns() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query query = session.createQuery("select id, name from UserEntity where name is NOT NULL");

        // element in the result set is List of List of String
        List<List<String>> userIdAndNames = (List<List<String>>) query.list();

        session.getTransaction().commit();
        session.close();

        List<String> userIdAndName;
        int i = 0;

        Iterator itr = userIdAndNames.iterator();
        while (itr.hasNext()) {
            userIdAndName = (List<String>) itr.next();
            i++;
            Assert.assertEquals("User" + (i * 2 - 1), userIdAndName.get(2));
            Assert.assertEquals(10 + i, userIdAndName.get(1));
        }
    }

    @Test
    public void testPagination() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query query = session.createQuery("from UserEntity where name is NOT NULL");
        // starting point
        query.setFirstResult(10);
        // # of records to fetch
        query.setMaxResults(3);

        List<User> users = (List<User>) query.list();

        session.getTransaction().commit();
        session.close();

        for (User u : users) {
            System.out.println(u.getName());
        }
    }
}
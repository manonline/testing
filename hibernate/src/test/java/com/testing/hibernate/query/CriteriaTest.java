package com.testing.hibernate.query;

import com.testing.hibernate.entity.EntityTest;
import com.testing.hibernate.entity.User;
import com.testing.hibernate.test.TestUtil;
import com.testing.hibernate.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CriteriaTest {

    private static final Logger logger = LoggerFactory.getLogger(HqlTest.class);
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
        logger.info("Cleanup for Test {}", HqlTest.class);

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
    public void testCriteria() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // what objects to retrieve
        Criteria criteria = session.createCriteria(User.class);
        // what conditions/restrictions to apply
        criteria.add(Restrictions.eq("name", "User29"));

        List<User> users = (List<User>) criteria.list();

        session.getTransaction().commit();
        session.close();

        // retrieval and testing
        Assert.assertEquals("User29", users.get(0).getName());
    }

    @Test
    public void testCriteriaChaining() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // what objects to retrieve
        Criteria criteria = session.createCriteria(User.class);
        // what conditions/restrictions to apply
        criteria.add(Restrictions.eq("name", "User29"))
                // chaining
                .add(Restrictions.gt("id", 12));
                // Restrictions.or(Restrictions.eq()....

        List<User> users = (List<User>) criteria.list();

        session.getTransaction().commit();
        session.close();

        // retrieval and testing
        Assert.assertEquals("User29", users.get(0).getName());
    }

    @Test
    public void testCriteriaProjection() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // what objects to retrieve
        Criteria criteria = session.createCriteria(User.class)
                // return columns
                .setProjection(Projections.property("id"));
                // aggregation ...
                // .setProjection(Projections.max("id"));
                // Ordering ...
                // .addOrder(Order.desc("id"))

        List ids = criteria.list();

        session.getTransaction().commit();
        session.close();

        // retrieval and testing
        Assert.assertEquals(15, ids.size());
    }

    @Test
    public void testCriteriaByExample() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User exampleUser = new User();
        exampleUser.setName("User29");

        Example example = Example.create(exampleUser);
        // ignore fields
        // .excludeProperty("field name")
        // using like exampleUser.setName("User%")
        // .enableLike()

        Criteria criteria = session.createCriteria(User.class)
                .add(example);

        // Null field and PK will be ignored when building query
        List users = criteria.list();

        session.getTransaction().commit();
        session.close();

        // retrieval and testing
        Assert.assertEquals(1, users.size());
    }
}
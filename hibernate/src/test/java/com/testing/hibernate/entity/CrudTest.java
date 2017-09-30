package com.testing.hibernate.entity;

import com.testing.hibernate.test.TestUtil;
import com.testing.hibernate.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by davidqi on 9/30/17.
 */
// test cases are executed in a sequential way based on their names
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CrudTest {

    private static final Logger logger = LoggerFactory.getLogger(EntityTest.class);
    private static Server h2Server = null;
    private static Server h2WebServer = null;

    @BeforeClass
    public static void setup() {
        logger.info("Setup for Test {} ...", EntityTest.class);

        // enable remote access to H2 Server
        h2Server = TestUtil.startTcpServerForH2(9092);
        h2WebServer = TestUtil.startWebServerForH2(8082);
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
    public void test001Create() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // create 10 entities
        for (int i = 1;  i <= 10; i++) {
            User user = new User();
            user.setName("User" + i);

            session.save(user);
        }

        session.getTransaction().commit();
        session.close();

        // check persistence in test002Retrieve
    }

    @Test
    public void test002Retrieve() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        for (int i = 1; i <= 10; i++) {
            User user = (User) session.get(User.class, 9 + i);
            Assert.assertEquals("User" + i, user.getName());
        }

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void test003Update() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user1 = (User) session.get(User.class, 10);
        user1.setName("New Name");
        session.update(user1);

        // in a transaction, value always the same, though not committed to database yet
        User user2 = (User) session.get(User.class, 10);
        Assert.assertEquals("New Name", user2.getName());

        session.getTransaction().commit();
        session.close();

        // check if entity is actually updated in database from another transaction
        session = HibernateUtil.getSession();
        session.beginTransaction();

        User user3 = (User) session.get(User.class, 10);
        Assert.assertEquals("New Name", user3.getName());

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void test004Delete() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // delete one entity
        User user1 = (User) session.get(User.class, 11);
        session.delete(user1);

        // original entity still alive
        Assert.assertEquals("User2", user1.getName());

        // not able to get new entity with the same id from within the same transaction.
        User user2 = (User) session.get(User.class, 11);
        Assert.assertNull(user2);

        session.getTransaction().commit();
        session.close();

        // check if the entity is actually deleted from the database
        session = HibernateUtil.getSession();
        session.beginTransaction();

        User user3 = (User) session.get(User.class, 11);
        Assert.assertNull(user3);

        session.getTransaction().commit();
        session.close();

    }
}

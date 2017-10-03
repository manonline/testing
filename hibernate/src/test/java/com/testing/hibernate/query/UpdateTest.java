package com.testing.hibernate.query;

import com.testing.hibernate.entity.EntityTest;
import com.testing.hibernate.entity.User;
import com.testing.hibernate.test.TestUtil;
import com.testing.hibernate.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UpdateTest {

    private static final Logger logger = LoggerFactory.getLogger(UpdateTest.class);
    private static Server h2Server = null;
    private static Server h2WebServer = null;

    @BeforeClass
    public static void setup() {
        logger.info("Setup for Test {} ...", UpdateTest.class);

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
        logger.info("Cleanup for Test {}", UpdateTest.class);

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
    public void testUpdate() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query query = session.createQuery("update UserEntity set name = :username where id = :id");
        query.setString("username", "Updated User");
        query.setInteger("id", 10);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();

        session = HibernateUtil.getSession();
        session.beginTransaction();

        query = session.createQuery("from UserEntity where id = :id");
        query.setInteger("id", 10);
        List<User> users = (List<User>) query.list();

        Assert.assertEquals("Updated User", users.get(0).getName());

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testBatchUpdate() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query query = session.createQuery("update UserEntity set name = :username where id > :id");
        query.setString("username", "Updated User");
        query.setInteger("id", 15);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();

        session = HibernateUtil.getSession();
        session.beginTransaction();

        query = session.createQuery("from UserEntity where name = :username");
        query.setString("username", "Updated User");
        List<User> users = (List<User>) query.list();

        Assert.assertEquals(9, users.size());

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testBatchDelete() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query query = session.createQuery("delete UserEntity where id > :id");
        query.setInteger("id", 15);
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();

        session = HibernateUtil.getSession();
        session.beginTransaction();

        query = session.createQuery("from UserEntity where id <= :id");
        query.setInteger("id", 15);
        List<User> users = (List<User>) query.list();

        Assert.assertEquals(6, users.size());

        session.getTransaction().commit();
        session.close();
    }
}

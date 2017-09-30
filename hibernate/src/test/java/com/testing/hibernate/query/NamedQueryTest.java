package com.testing.hibernate.query;

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

import java.util.List;

public class NamedQueryTest {

    private static final Logger logger = LoggerFactory.getLogger(HqlTest.class);
    private static Server h2Server = null;
    private static Server h2WebServer = null;

    @BeforeClass
    public static void setup() {
        logger.info("Setup for Test {} ...", EntityTest.class);

        // enable remote access to H2 Server
        h2Server = TestUtil.startTcpServerForH2(9092);
        h2WebServer = TestUtil.startWebServerForH2(8082);

        // cleanData();

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

        // cleanData();

        // shutdown remote access to H2 Server
        if (h2Server != null) {
            h2Server.stop();
        }

        if (h2WebServer != null) {
            h2WebServer.stop();
        }
    }

    private static void cleanData() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query query = session.createQuery("from UserEntity");

        List<User> usersToClean = (List<User>) query.list();

        if (usersToClean.size() > 0) {
            for (User u : usersToClean) {
                session.delete(u);
            }
        }

        session.getTransaction().commit();
        session.close();
    }

    @Before
    public void beforeTestCase() {

    }

    @After
    public void afterTestCase() {
        //server.stop();

    }

    @Test
    public void testNamedQuerySimple() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // get the named query defined on entity
        Query query = session.getNamedQuery("UserEntity.byId");
        query.setInteger("userId", 12);

        List<User> users = (List<User>) query.list();

        session.getTransaction().commit();
        session.close();

        // retrieval and testing
        Assert.assertEquals("User5", users.get(0).getName());
    }

    @Test
    public void testNamedNativeQuerySimple() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // get the named native query defined on entity
        Query query = session.getNamedQuery("UserEntity.byName");
        query.setString("name", "User29");

        List<User> users = (List<User>) query.list();

        session.getTransaction().commit();
        session.close();

        // retrieval and testing
        Assert.assertEquals("User29", users.get(0).getName());
    }
}

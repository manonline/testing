package com.testing.hibernate.entity.cache;

import com.testing.hibernate.entity.CrudTest;
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

public class CacheTest {

    private static final Logger logger = LoggerFactory.getLogger(EntityTest.class);
    private static Server h2Server = null;
    private static Server h2WebServer = null;

    @BeforeClass
    public static void setup() {
        logger.info("Setup for Test {} ...", CrudTest.class);

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
        logger.info("Cleanup for Test {}", CrudTest.class);

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
    public void testEntityCache() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class, 11);

        session.getTransaction().commit();
        session.close();

        System.out.println(user.getName());

        Session anotherSession = HibernateUtil.getSession();
        anotherSession.beginTransaction();

        User user2 = (User) anotherSession.get(User.class, 11);

        anotherSession.getTransaction().commit();
        anotherSession.close();

        System.out.println(user.getName());
    }

    @Test
    public void testQueryCache() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Query query = session.createQuery("from UserEntity ");
        User user = (User) session.get(User.class, 10);
        session.saveOrUpdate(user);

        session.getTransaction().commit();
        session.close();

        Session anotherSession = HibernateUtil.getSession();
        anotherSession.beginTransaction();

        User user2 = (User) anotherSession.get(User.class, 10);

        anotherSession.getTransaction().commit();
        anotherSession.close();
    }

    @Test
    public void testNPlus1() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<User> users = (List<User>) session.createQuery("from UserEntity")
                .setFirstResult(0)
                .setMaxResults(10)
                .list();

        System.out.println("Size of the List: " + users.size());
        Iterator<User> userItr = users.iterator();
        for(;userItr.hasNext();)
        {
            User user = (User) userItr.next();
            System.out.println(user.getName());
        }
    }
}

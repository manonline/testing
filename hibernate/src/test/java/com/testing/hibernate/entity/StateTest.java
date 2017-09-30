package com.testing.hibernate.entity;

import com.testing.hibernate.test.TestUtil;
import com.testing.hibernate.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StateTest {

    private static final Logger logger = LoggerFactory.getLogger(EntityTest.class);
    private static Server h2Server = null;
    private static Server h2WebServer = null;

    @BeforeClass
    public static void setup() {
        logger.info("Setup for Test {} ...", StateTest.class);

        // enable remote access to H2 Server
        h2Server = TestUtil.startTcpServerForH2(9092);
        h2WebServer = TestUtil.startWebServerForH2(8082);
    }

    @AfterClass
    public static void cleanup() {
        logger.info("Cleanup for Test {}", StateTest.class);

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
    public void testState() {

        User user = new User();
        user.setName("Initial Name");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // insert only - with end state
        user.setName("Change 1");
        session.save(user); // user become persistent

        // no update
        user.setName("Change 2");
        user.setName("Change 3");
        session.save(user);

        // one update - with end state
        user.setName("Change 4");
        user.setName("Change 5");
        session.flush();

        // one update - with end state
        user.setName("Change 6");
        user.setName("Change 7");

        session.getTransaction().commit();
        session.close(); // user become detached
    }

    @Test
    public void testUpdate() {

        User user = new User();
        user.setName("Initial Name");

        // initial setup
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();

        // retrieve object form database
        session = HibernateUtil.getSession();
        session.beginTransaction();

        User userRetrieved = (User) session.get(User.class, 10);

        session.getTransaction().commit();
        session.close();

        // do the update
        userRetrieved.setName("Updated Name After Session Closed");

        // merge it back
        session = HibernateUtil.getSession();
        session.beginTransaction();

        // session.save() will insert a new record, regardless whether id is given or not
        // session.save(userRetrieved);

        // update the previous record, since the id is already given
        session.update(userRetrieved);

        session.getTransaction().commit();
        session.close();
    }
}

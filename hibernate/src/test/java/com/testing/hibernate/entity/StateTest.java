package com.testing.hibernate.entity;

import com.testing.hibernate.test.TestUtil;
import com.testing.hibernate.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class StateTest {

    private static final Logger logger = LoggerFactory.getLogger(EntityTest.class);
    private static Server h2Server = null;
    private static Server h2WebServer = null;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

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

    @Test
    public void testJpaPersist() {
        /**
         * save()   : assign a new ID every time
         * persist(): assign a ID only when is missing, otherwise duplciated exception
         * update() : use existing id, if missing, exception
         * merge()  : assign a new ID if it's missing,
         * saveOrupdate() : use existing id, if missing, assign a new one
         */
        User user = new User();
        user.setName("Transitent Name");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // Transient -> Persistent
        session.persist(user);
        System.out.println(user.getId());

        // Persistent -> Detached
        session.evict(user);
        System.out.println(user.getId());

        user.setId(0);

        session.persist(user);

        // PERSIST: Detached -> Exception :
/*
        session.persist(user);
        System.out.println(user.getId());
        thrown.expect(org.hibernate.PersistentObjectException);
*/

        // SAVE: Detached -> Persistent : A NEW ID
        session.save(user);
        System.out.println(user.getId());

        session.getTransaction().commit();
        session.close();


    }

    @Test
    public void testFlushAndEvict() {
        User user = new User();
        user.setName("Transient");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // Transient -> Persistent
        user.setName("Persistent");
        session.save(user);

        // Flush to Database
        user.setName("Persistent Flush");
        session.flush();

        // Persistent -> Detached : A New ID is assigned
        user.setName("Persistent Removed");
        session.evict(user);

        // Detached -> Persistent
        user.setName("Reattached");
        session.save(user);

        session.beginTransaction().commit();
        session.close();
    }

    @Test
    public void testJpaMerge() {
        /**
         *
         */
        User user = new User();
        user.setName("Transitent Name");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // Transient -> Persistent
        session.persist(user);
        System.out.println(user.getId());

        // Persistent -> Detached
        session.evict(user);
        System.out.println(user.getId());

/*        // Merge: Detached -> Persistent
        user.setName("Detached User");
        User mergedUser = (User) session.merge(user);*/

/*
        // Merge: Transient -> Persistent
        User user2 = new User();
        user.setName("User 2");
        mergedUser = (User) session.merge(user2);
*/

        session.getTransaction().commit();
        session.close();


    }
}
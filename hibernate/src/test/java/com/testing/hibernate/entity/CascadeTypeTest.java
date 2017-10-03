package com.testing.hibernate.entity;

import com.testing.hibernate.query.HqlTest;
import com.testing.hibernate.test.TestUtil;
import com.testing.hibernate.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CascadeTypeTest {

    private static final Logger logger = LoggerFactory.getLogger(CascadeTypeTest.class);
    private static Server h2Server = null;
    private static Server h2WebServer = null;

    @BeforeClass
    public static void setup() {
        logger.info("Setup for Test {} ...", CascadeTypeTest.class);

        // enable remote access to H2 Server
        h2Server = TestUtil.startTcpServerForH2(9092);
        h2WebServer = TestUtil.startWebServerForH2(8082);
    }

    @AfterClass
    public static void cleanup() {
        logger.info("Cleanup for Test {}", CascadeTypeTest.class);

        // shutdown remote access to H2 Server
        if (h2Server != null) {
            h2Server.stop();
        }

        if (h2WebServer != null) {
            h2WebServer.stop();
        }
    }

    @Test
    public void testPersist() {
        Order order1 = new Order();
        order1.setOrdNum("Order 1");

        OrderLine orderLine1 = new OrderLine();
        OrderLine orderLine2 = new OrderLine();
        OrderLine orderLine3 = new OrderLine();
        OrderLine orderLine4 = new OrderLine();

        orderLine1.setDescription("Order Line 1");
        orderLine2.setDescription("Order Line 2");
        orderLine3.setDescription("Order Line 3");
        orderLine4.setDescription("Order Line 4");

        // MUST: set the reference to orderline, this is to ensure cascade works
        // this basically serve as the argument for cascade operation
        order1.getOrdLines().add(orderLine1);
        order1.getOrdLines().add(orderLine2);
        order1.getOrdLines().add(orderLine3);
        order1.getOrdLines().add(orderLine4);

        // MUST: set the reference to order, this is to maintain the relationship
        orderLine1.setOrder(order1);
        orderLine2.setOrder(order1);
        orderLine3.setOrder(order1);
        orderLine4.setOrder(order1);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // use persist instead of save (since CascadeType.PERSIST is a JPA definition)
        session.persist(order1);

        // no need to save OrderLine explicitly, because cascade is specified
        // session.persist(orderLine1);

        session.getTransaction().commit();
        session.close();
        System.out.println("DONE..................................");
        // validate the result
        session = HibernateUtil.getSession();
        session.beginTransaction();

        Order order2 = (Order) session.get(Order.class, 10);
        Assert.assertEquals(4, order2.getOrdLines().size());

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testPersist2() {
        Order order1 = new Order();
        order1.setOrdNum("Order 1");

        OrderLine orderLine1 = new OrderLine();
        OrderLine orderLine2 = new OrderLine();
        OrderLine orderLine3 = new OrderLine();
        OrderLine orderLine4 = new OrderLine();

        orderLine1.setDescription("Order Line 1");
        orderLine2.setDescription("Order Line 2");
        orderLine3.setDescription("Order Line 3");
        orderLine4.setDescription("Order Line 4");

        // MUST: set the reference to orderline, this is to ensure cascade works
        // this basically serve as the argument for cascade operation
        order1.getOrdLines().add(orderLine1);
        order1.getOrdLines().add(orderLine2);
        order1.getOrdLines().add(orderLine3);
        order1.getOrdLines().add(orderLine4);

        // MUST: set the reference to order, this is to maintain the relationship
        orderLine1.setOrder(order1);
        orderLine2.setOrder(order1);
        orderLine3.setOrder(order1);
        orderLine4.setOrder(order1);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        // use persist instead of save (since CascadeType.PERSIST is a JPA definition)
        session.persist(orderLine1);
        // no need to save order, if Cascade is specified on OrderLine
        // thereafter if Cascade is specified on Order, there is even no need to save
        // other Orderlines
        // session.persist(orderLine2);
        // session.persist(orderLine3);
        // session.persist(orderLine4);

        // no need to save OrderLine explicitly, because cascade is specified
        // session.persist(orderLine1);

        session.getTransaction().commit();
        session.close();

        System.out.println("DONE..................................");
        // validate the result
        session = HibernateUtil.getSession();
        session.beginTransaction();

        Order order2 = (Order) session.get(Order.class, 20);
        Assert.assertEquals(4, order2.getOrdLines().size());

        session.getTransaction().commit();
        session.close();
    }
}
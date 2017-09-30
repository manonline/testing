package com.testing.hibernate.entity;

import com.testing.hibernate.entity.valueobject.Address;
import com.testing.hibernate.test.TestUtil;
import com.testing.hibernate.util.HibernateUtil;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by davidqi on 9/25/17.
 */
public class EntityTest {
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
    public void testSingleEntity() {
        logger.info("Testing Started for {}", User.class);
        User user = new User();
        user.setName("David Qi");
        user.setHobbie("Basketball");
        user.setDob(new Date("1980/01/12"));

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testEmbeddedObject() {
        logger.info("Testing Started for {}", UserEmbeddableAddress.class);
        UserEmbeddableAddress userEmbeddableAddress = new UserEmbeddableAddress();
        userEmbeddableAddress.setName("Embeddable Address User");
        userEmbeddableAddress.setHobbie("Foot Ball");

        Address homeAddress = new Address();
        homeAddress.setState("WI");
        homeAddress.setCity("Brookfield");
        homeAddress.setStreet("JDA");

        Address officeAddress = new Address();
        homeAddress.setState("CA");
        homeAddress.setCity("L.A.");
        homeAddress.setStreet("Amada");

        userEmbeddableAddress.setHomeAddress(homeAddress);
        userEmbeddableAddress.setOfficeAddress(officeAddress);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(userEmbeddableAddress);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testEmbeddedColleciton() {
        logger.info("Testing Started for {}", UserCollectionAddress.class.toString());
        UserCollectionAddress userCollectionAddress = new UserCollectionAddress();
        userCollectionAddress.setName("Collection Address User");
        userCollectionAddress.setHobbie("PingPong");

        List<Address> addressList = new ArrayList<Address>();

        Address homeAddress = new Address();
        homeAddress.setState("WI");
        homeAddress.setCity("Brookfield");
        homeAddress.setStreet("JDA");
        Address officeAddress = new Address();
        homeAddress.setState("CA");
        homeAddress.setCity("L.A.");
        homeAddress.setStreet("Amada");
        addressList.add(homeAddress);
        addressList.add(officeAddress);

        userCollectionAddress.setAddress(addressList);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(userCollectionAddress);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testOneToOne() {
        logger.info("Testing Started for {}", UserOneToOneVehicle.class);
        UserOneToOneVehicle user = new UserOneToOneVehicle();
        user.setName("David Qi");
        user.setHobbie("Basketball");
        user.setDob(new Date("1980/01/12"));

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("Honda");

        user.setVehicle(vehicle);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.save(vehicle);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testOneToMany() {
        logger.info("Testing Started for {}", UserOneToManyVehicle.class);
        UserOneToManyVehicle user = new UserOneToManyVehicle();
        user.setName("David Qi");
        user.setHobbie("Basketball");
        user.setDob(new Date("1980/01/12"));

        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        vehicle1.setVehicleName("Honda");
        vehicle2.setVehicleName("BMW");

        // add the relationship
        user.getVehicles().add(vehicle1);
        user.getVehicles().add(vehicle2);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(vehicle1);
        session.save(vehicle2);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testCascade() {
        logger.info("Testing Started for {}", UserOneToManyVehicle.class);
        UserOneToManyVehicle user = new UserOneToManyVehicle();
        user.setName("David Qi");
        user.setHobbie("Basketball");
        user.setDob(new Date("1980/01/12"));

        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        vehicle1.setVehicleName("Honda");
        vehicle2.setVehicleName("BMW");

        // add the relationship
        user.getVehicles().add(vehicle1);
        user.getVehicles().add(vehicle2);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        // not need to save the vehicles manually, since Cascade is specified
        //session.save(vehicle1);
        //session.save(vehicle2);
        // use persist instead of save()
        session.persist(user);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testManyToOneSimple() {
        logger.info("Testing Started for {}", VehicleManyToOneUser.class);
        User user = new User();
        user.setName("David Qi");
        user.setHobbie("Basketball");
        user.setDob(new Date("1980/01/12"));

        VehicleManyToOneUser vehicleManyToOneUser1 = new VehicleManyToOneUser();
        VehicleManyToOneUser vehicleManyToOneUser2 = new VehicleManyToOneUser();
        vehicleManyToOneUser1.setVehicleName("Honda");
        vehicleManyToOneUser2.setVehicleName("BM2");

        // add the relationship
        vehicleManyToOneUser1.setUser(user);
        vehicleManyToOneUser2.setUser(user);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.save(vehicleManyToOneUser1);
        session.save(vehicleManyToOneUser2);
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testManyToOneBiDirection() {
        logger.info("Testing Started for {}", VehicleManyToOneUser.class);

        UserOneToManyVehicle userOneToManyVehicle = new UserOneToManyVehicle();
        userOneToManyVehicle.setName("Mike Ma");
        userOneToManyVehicle.setHobbie("Football");
        userOneToManyVehicle.setDob(new Date("1979/03/22"));

        VehicleManyToOneUser vehicleManyToOneUser1 = new VehicleManyToOneUser();
        VehicleManyToOneUser vehicleManyToOneUser2 = new VehicleManyToOneUser();
        vehicleManyToOneUser1.setVehicleName("Honda");
        vehicleManyToOneUser2.setVehicleName("BM2");

        // add relationship from one side
        vehicleManyToOneUser1.setUserOneToManyVehicle(userOneToManyVehicle);
        vehicleManyToOneUser2.setUserOneToManyVehicle(userOneToManyVehicle);

        // add relationship from the other side
        userOneToManyVehicle.getVehicleManyToOneUser().add(vehicleManyToOneUser1);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(userOneToManyVehicle);
        session.save(vehicleManyToOneUser1);
        session.save(vehicleManyToOneUser2);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testManyToMany() {
        logger.info("Testing Started for {}", VehicleManyToManyUser.class);

        UserManyToManyVehicle userManyToManyVehicle1 = new UserManyToManyVehicle();
        userManyToManyVehicle1.setName("Mike Ma");
        userManyToManyVehicle1.setHobbie("Football");
        userManyToManyVehicle1.setDob(new Date("1979/03/22"));

        UserManyToManyVehicle userManyToManyVehicle2 = new UserManyToManyVehicle();
        userManyToManyVehicle1.setName("David Qi");
        userManyToManyVehicle1.setHobbie("Basketball");
        userManyToManyVehicle1.setDob(new Date("1980/01/12"));

        VehicleManyToManyUser vehicleManyToManyUser1 = new VehicleManyToManyUser();
        vehicleManyToManyUser1.setVehicleName("Honda");

        VehicleManyToManyUser vehicleManyToManyUser2 = new VehicleManyToManyUser();
        vehicleManyToManyUser2.setVehicleName("BM2");

        vehicleManyToManyUser1.getUserManyToManyVehicles().add(userManyToManyVehicle1);
        vehicleManyToManyUser1.getUserManyToManyVehicles().add(userManyToManyVehicle2);

        //userManyToManyVehicle2.getVehicleManyToManyUser().add(vehicleManyToManyUser1);
        //userManyToManyVehicle1.getVehicleManyToManyUser().add(vehicleManyToManyUser2);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.save(userManyToManyVehicle1);
        session.save(userManyToManyVehicle2);

        session.save(vehicleManyToManyUser1);
        session.save(vehicleManyToManyUser2);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testInheritance() {
        logger.info("Testing Started for {}", VehicleManyToOneUser.class);
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("Car");

        TwoWheeler twoWheeler = new TwoWheeler();
        twoWheeler.setVehicleName("Two Wheeler");
        twoWheeler.setSteeringHandle("Bike Steering Handle");

        FourWheeler fourWheeler = new FourWheeler();
        fourWheeler.setVehicleName("Four Wheeler");
        fourWheeler.setSteeringWheel("Car Steering Wheel");

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        session.save(vehicle);
        session.save(twoWheeler);
        session.save(fourWheeler);

        session.getTransaction().commit();
        session.close();
    }
}
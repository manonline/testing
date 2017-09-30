package com.testing.hibernate.test;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by davidqi on 9/28/17.
 */
public class TestUtil {

    private static final Logger logger = LoggerFactory.getLogger(TestUtil.class);

    public static Server startTcpServerForH2(int tcpPort) {
        Server h2Server = null;

        try {

            logger.info("Starting TCP Server for H2 Database ...");
            // TCP Access : jdcb:tcp://localhost:tcpPort/mem:<db_name>
            h2Server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", String.valueOf(tcpPort));
            h2Server.start();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return h2Server;
    }

    public static Server startWebServerForH2(int webPort) {
        Server h2WebServer = null;

        try {
            logger.info("Starting Web Server for H2 Database ...");
            // Web Access : http://localhost:webPort
            h2WebServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", String.valueOf(webPort));
            h2WebServer.start();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return h2WebServer;
    }
}
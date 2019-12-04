
package org.labs.wt.tour.control.db;


import org.apache.commons.dbcp2.BasicDataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Properties;


public class DBSource {

    private static final Logger LOGGER = LogManager.getLogger(DBSource.class);

    private static final DBSource instance = new DBSource();

    public static DBSource getInstance() {
        return instance;
    }

    private BasicDataSource dataSource;


    private DBSource() {
        dataSource = new BasicDataSource();

        Properties properties = new Properties();

        try {
            String propertiesFileName = getClass().getResource("/tour.properties").getFile();
            properties.load(new FileInputStream(propertiesFileName));
        } catch (Exception ex) {
            LOGGER.error("error to read properties", ex);
        }

        dataSource.setDriverClassName(properties.getProperty("driver", "com.mysql.jdbc.Driver"));
        dataSource.setUsername(properties.getProperty("username", "username"));
        dataSource.setPassword(properties.getProperty("password", "password"));
        dataSource.setUrl(properties.getProperty("url", "url"));

        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(5);
        dataSource.setInitialSize(5);
        dataSource.setValidationQuery("SELECT 1");
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

}

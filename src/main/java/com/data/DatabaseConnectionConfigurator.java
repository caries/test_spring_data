package com.data;

import com.arjuna.ats.internal.jdbc.DynamicClass;
import org.postgresql.xa.PGXADataSource;

import javax.sql.XADataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * References Postgressql and JbossTS.
 */
public class DatabaseConnectionConfigurator implements DynamicClass {
    @Override
    public XADataSource getDataSource(String s) throws SQLException {
        PGXADataSource dataSource = new PGXADataSource();

        // TODO: Remove duplicate URL specification - base-beans.xml and here
        Properties properties = readProperties();
        dataSource.setServerName(properties.getProperty("jdbc.host", ""));
        dataSource.setPortNumber(Integer.valueOf(properties.getProperty("jdbc.port", "0")));
        dataSource.setDatabaseName(properties.getProperty("jdbc.db", ""));

        return dataSource;
    }

    private Properties readProperties() {
        Properties properties = new Properties();

        try (InputStream stream =  this.getClass().getClassLoader().getResourceAsStream("configuration.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            System.out.println("Cannot read configuration");
            e.printStackTrace();
        }

        return properties;
    }
}
package com.edutech.progressive.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class DatabaseConnectionManager {
    private static Properties properties = new Properties();
    private static void loadProperties(){    
    try {
            // load application.properties from classpath
            InputStream in = DatabaseConnectionManager.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");

            if (in == null) {
                throw new RuntimeException("application.properties not found in resources folder!");
            }

            properties.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
    }
    public static Connection getConnection() throws RuntimeException{
        loadProperties();
        final String url=properties.getProperty("spring.datasource.url");
        final String username= properties.getProperty("spring.datasource.username");
        final String password= properties.getProperty("spring.datasource.password");
        final String driver=properties.getProperty("spring.datasource.driver-class-name");
      if(driver!=null){
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
      }
      return null; 
    }
}
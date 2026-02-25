package com.edutech.progressive.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class DatabaseConnectionManager {
public static Properties properties=new  Properties();
private static void loadProperties(){
try(InputStream input=DatabaseConnectionManager.class.getClassLoader().getResourceAsStream("application.properties"))
{
    if(input==null){
        throw new RuntimeException("application.properties file not found");

    }
    properties.load(input);
}catch(IOException e)
{
    throw new RuntimeException("Error reading application.properties file",e);
}

}
public static Connection getConnection(){
    try{
        if(properties.isEmpty())
        {
            loadProperties();
        }
  
 loadProperties();
            String url = properties.getProperty("spring.datasource.url");
            String username = properties.getProperty("spring.datasource.username");
            String password = properties.getProperty("spring.datasource.password");
            String driver = properties.getProperty("spring.datasource.driver-class-name");

        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
    catch(Exception e)
    {
        throw new RuntimeException("Failed to create database cannection",e);
    }
}
}

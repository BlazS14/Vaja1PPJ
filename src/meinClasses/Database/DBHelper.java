package meinClasses.Database;

import org.apache.commons.dbcp2.BasicDataSource;


import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;




public final class DBHelper {
    private DBHelper() {
    }



    private static BasicDataSource dataSource;

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka conn! -- " + i.getMessage() + "\n");
            return null;
        }
    }

    public static boolean testConnection() {

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SHOW TABLES;");
            int count = 0;
            while (rs.next()) {
                System.out.println("table: " + rs.getString(1));
                count++;
            }
            if (count > 0)
                return true;
            else
                return false;
        } catch (java.sql.SQLException i) {
            System.out.println("SQL napaka test! -- " + i.getMessage() + "\n");
            return false;
        }
    }

    private static BasicDataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            try {
                InputStream input = new FileInputStream("src/meinClasses/Database/config.properties");
                Properties prop = new Properties();

                // load a properties file
                prop.load(input);
                ds.setUsername(prop.getProperty("db.user"));
                ds.setPassword(prop.getProperty("db.password"));
                ds.setUrl(prop.getProperty("db.url"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);

            dataSource = ds;
        }
        return dataSource;
    }
}

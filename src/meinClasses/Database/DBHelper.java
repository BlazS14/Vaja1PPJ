package meinClasses.Database;

import meinClasses.Izdelek;
import org.apache.commons.dbcp2.BasicDataSource;


import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
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

    public static void fillDb(String path){

        fillDb(path,0);

    }

    public static void fillDb(String path, int max){

        String line;
        Izdelek izdtemp;
        Random r = new Random();
        int count = 0;
        try(Connection conn = getConnection();
            PreparedStatement stat = conn.prepareStatement("INSERT INTO izdelek (idIzdelek,barcode,name,price,vat,stock,created,modified) VALUES (unhex(replace(uuid(),'-','')),?,?,?,?,?,current_timestamp(),current_timestamp())");
            BufferedReader br = new BufferedReader(new FileReader(path));
            ) {

            conn.setAutoCommit(false);

            line = br.readLine();
            while ((line = br.readLine()) != null) {

                String[] izdelek = line.split("\t");
                float randomcena = (float) (0.01 + r.nextFloat() * (1000 - 0.01));
                izdtemp = new Izdelek(izdelek[0],izdelek[7],randomcena, (float) 22.5,1000);
                //System.out.println(izdtemp.toString());
                //System.out.println(izdtemp.getEAN().length());
                System.out.println(izdelek[0]);
                if(izdtemp.getEAN().length() == 14 && izdtemp.checkDigit() == true)         //TODO popravi vstavljanje v sql. napaka v sintaksi INSERT stavka
                {
                    stat.setString(1,izdtemp.getEAN());
                    stat.setString(2,izdtemp.getIme());
                    stat.setFloat(3,izdtemp.getCenaBrezDDV());
                    stat.setFloat(4,izdtemp.getDDV());
                    stat.setInt(5,izdtemp.getZaloga());
                    stat.addBatch();
                    System.out.println("BATCH ADDED");

                }else if(izdtemp.getEAN().length() < 14)
                {
                    izdtemp.setEAN(padLeftZeros(izdtemp.getEAN(),14));
                    stat.setString(1,izdtemp.getEAN());
                    stat.setString(2,izdtemp.getIme());
                    stat.setFloat(3,izdtemp.getCenaBrezDDV());
                    stat.setFloat(4,izdtemp.getDDV());
                    stat.setInt(5,izdtemp.getZaloga());
                    stat.addBatch();
                    System.out.println("BATCH ADDED");
                }
                if (count % 1000 == 0 || count == max){
                    int[] execbatch = stat.executeBatch();
                    System.out.print(execbatch.toString());
                    conn.commit();
                    System.out.println("COMMIT");
                }
                if (count == max && max != 0){
                    break;
                }
                count++;

            }
            conn.commit();

        } catch (SQLException|IOException e) {
            e.printStackTrace();
        }

        System.out.println("Inserted "+ count + " items!");

    }

    public static String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
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

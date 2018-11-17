package com;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String userName = "root";
        String password = "sql123";
        String connectionUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(connectionUrl, userName, password);
             Statement stat = conn.createStatement()) {

            stat.execute ("drop table Users");
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS Users(id MEDIUMINT NOT NULL AUTO_INCREMENT,name CHAR(30) NOT NULL,password CHAR(30) NOT NULL,PRIMARY KEY(id))");
            stat.executeUpdate("insert into Users (name, password)VALUES ('root','sql123')");
            stat.executeUpdate("insert into Users set name = 'otherGuy',password='321'");

          //  String userId="1";
           String userId="1 ' or 1 = ' 1"; //sql injection

//            ResultSet resultSet=stat.executeQuery("select * from Users where id='"+userId+"'");
//            while (resultSet.next()){
//                System.out.println("userName: "+resultSet.getString("name"));
//                System.out.println("userPassword: "+resultSet.getString("password"));
//            }
            PreparedStatement pS=conn.prepareStatement("SELECT * from Users where id = ?");
            pS.setString(1,userId);
        //   pS.setString(2,"userName");
            ResultSet rS =pS.executeQuery();
            while (rS.next()){
                System.out.println("userName: "+rS.getString("name"));
                 System.out.println("userPassword: "+rS.getString("password"));
            }
        }
    }
}


// CREATE TABLE IF NOT EXISTS Book (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, PRIMARY KEY (id))
























package project;

import project.domein.*;
import project.tests.*;
import project.persistence.*;


import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {


        Connection connection = getConnection();

        try {
            ReizigerDAOPsql rdao = new ReizigerDAOPsql(connection);
            test.testReizigerDAO(rdao);
        } catch (Exception e){
            System.out.println("Exception gegeven: "+e);
        }
    }





    // get connection
    protected static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=admin";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException sqle){
            System.out.println("Connection fail"+sqle);
        }
        return connection;
    }
}

package project;

import project.domein.*;
import project.tests.*;
import project.persistence.*;


import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Connection connection;
    public static void main(String[] args) throws SQLException {

        connection = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "admin");
        AdresDAOPsql adao = new AdresDAOPsql(connection);
        ReizigerDAOPsql rdao = new ReizigerDAOPsql(connection);

            adao.setRdao(rdao);
            rdao.setAdao(adao);

        Reiziger reiziger = new Reiziger(100,"m", "","steen",java.sql.Date.valueOf("2010-01-02"));
        Reiziger reiziger2 = new Reiziger(101,"m", "","verstappen",java.sql.Date.valueOf("1997-02-02"));
        Adres adres = new Adres(17,"7890AB", "12", "Straatweg", "Utrecht", reiziger);


        System.out.println(adres);
        System.out.println("---------------------------------------------------------------------");

        List<Reiziger> alleReizigers = rdao.findAll();
        for (Reiziger r : alleReizigers){
            System.out.println(r);
        }
        System.out.println("-----------------------------------------------");
        rdao.save(reiziger);
        rdao.save(reiziger2);

        List<Reiziger> alleReizigers2 = rdao.findAll();

        for (Reiziger r : alleReizigers2){
            System.out.println(r);
        }
        rdao.delete(reiziger);
        System.out.println("--------------------------------");
        System.out.println(adao.findAll());
        System.out.println("-----------------------------------------------------------");
        System.out.println(rdao.findAll());
    }
}

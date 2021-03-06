package project;

import project.domein.*;
import project.tests.*;
import project.persistence.*;


import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Connection connection;
    public static void main(String[] args) throws SQLException {

        connection = DriverManager.getConnection("jdbc:postgresql:ovchip", "postgres", "admin");
        AdresDAOPsql adao = new AdresDAOPsql(connection);
        ReizigerDAOPsql rdao = new ReizigerDAOPsql(connection);
        OVChipkaartDAOPsql odao = new OVChipkaartDAOPsql(connection);
        ProductDAOPsql pdao = new ProductDAOPsql(connection);

            adao.setRdao(rdao);
            rdao.setAdao(adao);
            odao.setRdao(rdao);
            rdao.setOdao(odao);
            pdao.setPdao(pdao);

        Reiziger reiziger = new Reiziger(100,"m", "","steen",java.sql.Date.valueOf("2010-01-02"));
        Adres adres = new Adres(17,"7890AB", "12", "Straatweg", "Utrecht", reiziger);
        OVChipkaart ovChipkaart1 = new OVChipkaart(11,java.sql.Date.valueOf("2023-01-01"), 1,10.00,reiziger);
        OVChipkaart ovChipkaart2 = new OVChipkaart(15,java.sql.Date.valueOf("2023-01-01"), 2, 99.00,reiziger);
        Product product = new Product(12345,"dagkaart","een dag kaart", 9.90);

        pdao.save(product);
        System.out.println(pdao.findAll());
        pdao.delete(product);
//        System.out.println("---------------------------------------------------------------------deletes");
//        rdao.delete(reiziger);
//        odao.delete(ovChipkaart1);
//        adao.delete(adres);
//        System.out.println("----------------------------------------------- saves");
//        rdao.save(reiziger);
//        odao.save(ovChipkaart1);
//        odao.save(ovChipkaart2);
//        adao.save(adres);
//        System.out.println("------------------------------ find by id ");
//        System.out.println(rdao.findAll());


    }
}

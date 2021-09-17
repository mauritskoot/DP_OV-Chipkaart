package project.tests;

import project.domein.Adres;
import project.domein.Reiziger;
import project.persistence.AdresDAOPsql;
import project.persistence.ReizigerDAO;
import project.persistence.ReizigerDAOPsql;

import java.sql.*;
import java.util.List;

public class test {

    public static void testReizigerDAO(ReizigerDAOPsql rdao) throws SQLException {
        System.out.println("test findAll reiziger: -----------------------------------------------------------");
        // get reizigers
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println("test save reiziger: -----------------------------------------------------------");

        // create reiziger
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        System.out.println(sietske);
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        System.out.println("test update reiziger: -----------------------------------------------------------");
        // update reiziger
        String gbdatumupdate = "1997-05-31";
        Reiziger reizigerToUpdate = new Reiziger(79, "m g f j", "", "koot", java.sql.Date.valueOf(gbdatumupdate));
        for (Reiziger r : reizigers) {
            if (r.getReizigerId() == 77) {
                rdao.update(reizigerToUpdate);
                System.out.println("de volgende gebruiker is aangepast: " + r);
            }
        }
        System.out.println("test delete reiziger: -----------------------------------------------------------");
        //delete reiziger
        int aantalGebruikersVerwijderd = 0;
        int deleteOpId = 77;
        for (Reiziger r : reizigers) {
            if (r.getReizigerId() == deleteOpId) {
                aantalGebruikersVerwijderd++;
                rdao.delete(r);
                System.out.println("delete van reiziger met id" + deleteOpId + " succesvol");
            }
        }
        System.out.println("Aantal gebruikers verwijderd: " + aantalGebruikersVerwijderd);


        System.out.println("test find by id: --------------------------------------------------------------");
        //findById
        int oefenId = 1;
        System.out.println(rdao.findById(oefenId));

        System.out.println("test find by gb datum ----------------------------------------------------------");
        //findByGbdatum
        String oefenDatum = "20-12-03";
        System.out.println(rdao.findByGbdatum(oefenDatum));

    }




    public static void testAdresDAO(AdresDAOPsql adao) throws SQLException {
        // get adres
        List<Adres> adressen = adao.findAll();
        System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
        for (Adres a : adressen) {
            System.out.println(a);
        }
        //create adres
        Reiziger reiziger = new Reiziger(77,"a", "","kaas", Date.valueOf("1990-01-01"));
        Adres nieuwAdres = new Adres(909,"1234AB","17","straatweg","utrecht", reiziger);
        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");
        adao.save(nieuwAdres);
        adressen = adao.findAll();
        System.out.println(adressen.size()+"adressen");

        //update adres
        Adres adresToUpdate = new Adres(909, "4321BA", "71", "wegstraat", "sticht",reiziger);
        adao.update(adresToUpdate);
        for (Adres a : adressen){
            if (a.getReiziger().getReizigerId() == 77){
                System.out.println("De volgende gebruiker is aangepast"+ a);
            } else {
                System.out.println("updaten adres niet gelukt");
            }
        }

        //delete adres
        int deleteOpAdresId = 909;
        for(Adres a : adressen){
            if (a.getAdresId() == deleteOpAdresId){
                adao.delete(a);
                System.out.println("delete van adres succesvol");
            } else {
                System.out.println("Delete van adres niet gelukt");
            }
        }
    }
}

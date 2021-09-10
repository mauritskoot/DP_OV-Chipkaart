package project;

import java.sql.*;

public class Main {

    public static void main(String[] args){

        //verbind met bedrijfscasus
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=admin";

        try {
            Connection conn = DriverManager.getConnection(url);

            //voer query uit
            Statement st = conn.createStatement();
            String query = "SELECT * FROM reiziger r";
            //bevraag resultaten
            ResultSet rs = st.executeQuery(query);

            System.out.println("Alle reizigers:");
            while (rs.next()){
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                Date geboortedatum = rs.getDate("geboortedatum");
                int nummer = rs.getInt(1);

                if (tussenvoegsel==null){
                    tussenvoegsel = "";
                }

                System.out.println("#"+nummer+" "+voorletters+" "+tussenvoegsel+" "+achternaam+" ("+geboortedatum+")");
            }



        }
        catch (SQLException sqlex){
            System.err.println("SQL error");
        }
    }
}

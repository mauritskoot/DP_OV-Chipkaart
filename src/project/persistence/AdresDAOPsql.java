package project.persistence;

import project.domein.Adres;
import project.domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO{
    private Connection conn;
    private ReizigerDAO rdao;

    public void setRdao(ReizigerDAO rdao) {
        this.rdao = rdao;
    }



    public AdresDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            String query = "INSERT INTO adres (adres_id,postcode, huisnummer, straat, woonplaats, reiziger_id)values (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,adres.getAdresId());
            ps.setString(2,adres.getPostcode());
            ps.setString(3,adres.getHuisnummer());
            ps.setString(4,adres.getStraat());
            ps.setString(5,adres.getWoonplaats());
            ps.setInt(6,adres.getReiziger().getReizigerId());
            ps.execute();
            ps.close();


        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Adres adres){
        try {
            String query = "UPDATE adres SET adres_id = ?, postcode = ?, huisnummer = ?, straat = ?, woonplaats = ? WHERE reiziger_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, adres.getAdresId());
            ps.setString(2, adres.getPostcode());
            ps.setString(3, adres.getHuisnummer());
            ps.setString(4, adres.getStraat());
            ps.setString(5, adres.getWoonplaats());
            ps.setInt(6, adres.getReiziger().getReizigerId());
            ps.executeUpdate();
            ps.close();

    } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
    }
        return true;
}

    @Override
    public boolean delete(Adres adres) {
        try {
            String query = "DELETE FROM adres WHERE adres_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, adres.getAdresId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return true;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        Adres adres = null;

        try {
            String query = "SELECT * FROM adres WHERE reiziger_id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1,reiziger.getReizigerId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                int adresId = rs.getInt(1);
                String postcode = rs.getString(2);
                String huisnummer = rs.getString(3);
                String straat = rs.getString(4);
                String woonplaats = rs.getString(5);
                adres = new Adres(adresId, postcode, huisnummer, straat , woonplaats, reiziger);

            }
            pst.close();
            rs.close();


        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return adres;
    }


    @Override
    public List<Adres> findAll() {
        List<Adres> alleAdressen = new ArrayList<>();

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id FROM adres");

            while (rs.next()){
                int adresId = rs.getInt("adres_id");
                String postcode = rs.getString("postcode");
                String huisnummer = rs.getString("huisnummer");
                String straat = rs.getString("straat");
                String woonplaats = rs.getString("woonplaats");
                int reizigerId = rs.getInt("reiziger_id");


                Adres adres = new Adres(adresId, postcode, huisnummer, straat, woonplaats, rdao.findById(reizigerId));
                alleAdressen.add(adres);
            }
            s.close();
            rs.close();

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return alleAdressen;
    }
}



















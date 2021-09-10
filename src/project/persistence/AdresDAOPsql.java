package project.persistence;

import project.domein.Adres;
import project.domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql extends PostgresBaseDao implements AdresDAO{

    @Override
    public boolean save(Adres adres) {
        try (Connection connection = super.getConnection()){
            String query = "INSERT INTO adres (adres_id,postcode, huisnummer, straat, woonplaats, reiziger_id)values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,adres.getAdres_id());
            ps.setInt(2,adres.getHuisnummer());
            ps.setString(3,adres.getStraat());
            ps.setString(4,adres.getWoonplaats());
            ps.setInt(5,adres.getReiziger_id());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Adres adres) {
        try (Connection connection = super.getConnection()) {
            String query = "UPDATE adres SET postcode = ?, huisnummer = ?, straat = ?, woonplaats = ?, reiziger_nummer = ? WHERE reiziger_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, adres.getAdres_id());
            ps.setString(2, adres.getPostcode());
            ps.setInt(3, adres.getHuisnummer());
            ps.setString(4, adres.getStraat());
            ps.setString(5, adres.getWoonplaats());
            ps.setInt(6, adres.getReiziger_id());
            ps.executeUpdate();

    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
        return true;
}

    @Override
    public boolean delete(Adres adres) {
        try (Connection connection = super.getConnection()) {
            String query = "DELETE FROM adres WHERE adres_id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, adres.getAdres_id());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        Adres adres = null;

        try (Connection connection = super.getConnection()) {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id FROM adres WHERE reiziger_id = '"
                    + reiziger.getReiziger_id() + "';");

            while(rs.next()){
                adres = new Adres(rs.getInt("adres_id"),
                        rs.getString("postcode"),
                        rs.getInt("huisnummer"),
                        rs.getString("straat"),
                        rs.getString("woonplaats"),
                        rs.getInt("reiziger_id"));

            }
            rs.close();
            s.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return adres;
    }


    @Override
    public List<Adres> findAll() {
        List<Adres> alleAdressen = new ArrayList<>();

        try(Connection connection = super.getConnection()){
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) FROM adres");

            while (rs.next()){
                int adres_id = rs.getInt("adres_id");
                String postcode = rs.getString("postcode");
                int huisnummer = rs.getInt("huisnummer");
                String straat = rs.getString("straat");
                String woonplaats = rs.getString("woonplaats");
                int reiziger_id = rs.getInt("reiziger_id");

                Adres adres = new Adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id);
                alleAdressen.add(adres);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alleAdressen;
    }
}



















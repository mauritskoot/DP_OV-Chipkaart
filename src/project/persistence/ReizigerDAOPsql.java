package project.persistence;


import project.domein.Reiziger;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection conn;

    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try  {
            String query = "INSERT INTO reiziger(reiziger_id,voorletters,tussenvoegsel,achternaam,geboortedatum)values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, reiziger.getReiziger_id());
            ps.setString(2, reiziger.getVoorletters());
            ps.setString(3, reiziger.getTussenvoegsel());
            ps.setString(4, reiziger.getAchternaam());
            ps.setDate(5, reiziger.getGeboortedatum());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try  {
            String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ? WHERE reiziger_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, reiziger.getReiziger_id());
            ps.setString(2, reiziger.getVoorletters());
            ps.setString(3, reiziger.getTussenvoegsel());
            ps.setString(4, reiziger.getAchternaam());
            ps.setDate(5, reiziger.getGeboortedatum());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try  {
            String query = "DELETE FROM reiziger WHERE reiziger_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, reiziger.getReiziger_id());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public Reiziger findById(int id) {
        Reiziger reiziger = null;

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(
                    "SELECT reiziger_id,voorletters,tussenvoegsel,achternaam,geboortedatum FROM reiziger WHERE reiziger_id = '"
                            + id + "';");
            while (rs.next()) {
                reiziger = new Reiziger(rs.getInt("reiziger_id"),
                        rs.getString("voorletters"),
                        rs.getString("tussenvoegsel"),
                        rs.getString("achternaam"),
                        rs.getDate("geboortedatum"));
            }
            rs.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return reiziger;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        List<Reiziger> reizigers = new ArrayList<>();

        try  {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT reiziger_id,voorletters,tussenvoegsel,achternaam,geboortedatum FROM reiziger WHERE geboortedatum ='" + datum + "');");

            while (rs.next()) {
                int reiziger_id = rs.getInt("reiziger_id");
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                Date geboortedatum = rs.getDate("geboortedatum");
                Reiziger nieuweReiziger = new Reiziger(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum);
                reizigers.add(nieuweReiziger);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() {
        List<Reiziger> alleReizigers = new ArrayList<>();

        try  {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT reiziger_id,voorletters,tussenvoegsel,achternaam,geboortedatum FROM reiziger");

            while (rs.next()){
                int reiziger_id = rs.getInt("reiziger_id");
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                Date geboortedatum = rs.getDate("geboortedatum");
                Reiziger nieuweReiziger = new Reiziger(reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum);
                alleReizigers.add(nieuweReiziger);
            }

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return alleReizigers;
    }
}
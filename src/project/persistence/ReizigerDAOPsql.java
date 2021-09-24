package project.persistence;


import project.domein.Adres;
import project.domein.OVChipkaart;
import project.domein.Reiziger;

import java.sql.Connection;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {
    private Connection conn;
    private AdresDAO adao;
    private OVChipkaartDAO odao;

    public void setAdao(AdresDAO adao) {
        this.adao = adao;
    }
    public void setOdao(OVChipkaartDAO odao){this.odao = odao;}

    public ReizigerDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try  {
            String query = "INSERT INTO reiziger(reiziger_id,voorletters,tussenvoegsel,achternaam,geboortedatum)values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, reiziger.getReizigerId());
            ps.setString(2, reiziger.getVoorletters());
            ps.setString(3, reiziger.getTussenvoegsel());
            ps.setString(4, reiziger.getAchternaam());
            ps.setDate(5, reiziger.getGeboortedatum());
            ps.execute();
            ps.close();

            Adres adres = reiziger.getAdres();
            if (adres != null){
                adao.save(adres);
            }

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try  {
            String query = "UPDATE reiziger SET voorletters = ?, tussenvoegsel = ?, achternaam = ?, geboortedatum = ?, reiziger_id = ? WHERE reiziger_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, reiziger.getVoorletters());
            ps.setString(2, reiziger.getTussenvoegsel());
            ps.setString(3, reiziger.getAchternaam());
            ps.setDate(4, reiziger.getGeboortedatum());
            ps.setInt(5, reiziger.getReizigerId());
            ps.setInt(6, reiziger.getReizigerId());

            Adres adres = reiziger.getAdres();
            if(adres!=null) {
                adao.update(adres);
            }

            ps.executeUpdate();
            ps.close();

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try  {
            String query = "DELETE FROM reiziger WHERE reiziger_id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, reiziger.getReizigerId());
            Adres adres = adao.findByReiziger(reiziger);
            List<OVChipkaart> ovChipkaarten = odao.findByReiziger(reiziger);

            if(adres != null){
                adao.delete(adres);
            }
            //klopt deze forloop zo in geval van meerdere ov chipkaarten op 1 gebruiker?
            for(OVChipkaart ovChipkaart : ovChipkaarten){
                odao.delete(ovChipkaart);
            }

            ps.executeUpdate();
            ps.close();

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return true;
    }

    @Override
    public Reiziger findById(int id) {
        Reiziger reiziger = null;

        try {
            String query = "SELECT reiziger_id,voorletters,tussenvoegsel,achternaam,geboortedatum FROM reiziger WHERE reiziger_id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int reizigerId = rs.getInt("reiziger_id");
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                Date geboortedatum = rs.getDate("geboortedatum");
                reiziger = new Reiziger(reizigerId, voorletters,tussenvoegsel, achternaam, geboortedatum);
            }
            pst.close();
            rs.close();

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return reiziger;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        List<Reiziger> reizigers = new ArrayList<>();

        try  {
            String query = "SELECT reiziger_id,voorletters,tussenvoegsel,achternaam,geboortedatum FROM reiziger WHERE geboortedatum =?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setDate(1,Date.valueOf(datum));
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int reizigerId = rs.getInt("reiziger_id");
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                Date geboortedatum = rs.getDate("geboortedatum");
                Reiziger reiziger = new Reiziger(reizigerId, voorletters, tussenvoegsel, achternaam, geboortedatum);
                reiziger.setAdres(adao.findByReiziger(reiziger));
                reizigers.add(reiziger);


            }
            rs.close();
        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() {
        List<Reiziger> alleReizigers = new ArrayList<>();

        try  {
            String query = "SELECT reiziger_id,voorletters,tussenvoegsel,achternaam,geboortedatum FROM reiziger";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int reizigerId = rs.getInt("reiziger_id");
                String voorletters = rs.getString("voorletters");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");
                Date geboortedatum = rs.getDate("geboortedatum");
                Reiziger nieuweReiziger = new Reiziger(reizigerId, voorletters, tussenvoegsel, achternaam, geboortedatum);
                nieuweReiziger.setAdres(adao.findByReiziger(nieuweReiziger));
                alleReizigers.add(nieuweReiziger);

            }
            ps.close();
            rs.close();
        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return alleReizigers;
    }
}
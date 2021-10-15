package project.persistence;

import project.domein.Adres;
import project.domein.OVChipkaart;
import project.domein.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {
    private Connection conn;
    private ReizigerDAO rdao;
    private OVChipkaartDAO odao;

    public void setRdao(ReizigerDAO rdao) {
        this.rdao = rdao;
    }


    public void setOdao(OVChipkaartDAO odao) {
        this.odao = odao;
    }

    public OVChipkaartDAOPsql(Connection conn) {
        this.conn = conn;
    }


    @Override
    public boolean save(OVChipkaart ovchipkaart) throws SQLException {
        try {
            String query = "INSERT INTO ov_chipkaart(kaart_nummer,geldig_tot,klasse,saldo,reiziger_id)values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ovchipkaart.getKaartNummer());
            ps.setDate(2, ovchipkaart.getGeldigTot());
            ps.setInt(3, ovchipkaart.getKlasse());
            ps.setDouble(4, ovchipkaart.getSaldo());
            ps.setInt(5, ovchipkaart.getReiziger().getReizigerId());
            ps.execute();
            ps.close();

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(OVChipkaart ovchipkaart) throws SQLException {
        try {
            String query = "UPDATE ov_chipkaart SET kaart_nummer = ?, geldig_tot = ?, klasse = ?, saldo = ? WHERE reiziger_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ovchipkaart.getKaartNummer());
            ps.setDate(2, ovchipkaart.getGeldigTot());
            ps.setInt(3, ovchipkaart.getKlasse());
            ps.setDouble(4, ovchipkaart.getSaldo());
            ps.setInt(5, ovchipkaart.getReiziger().getReizigerId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(OVChipkaart ovchipkaart) throws SQLException {
        try {
            String query = "DELETE FROM ov_chipkaart WHERE kaart_nummer =?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ovchipkaart.getKaartNummer());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return true;
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {
        List<OVChipkaart> alleOVChipkaarten = new ArrayList<>();


        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT kaart_nummer,geldig_tot,klasse,saldo,reiziger_id FROM ov_chipkaart");

            while (rs.next()) {
                int kaartNummer = rs.getInt("kaart_nummer");
                Date geldigTot = rs.getDate("geldig_tot");
                int klasse = rs.getInt("klasse");
                double saldo = rs.getDouble("saldo");
                Reiziger reiziger = this.rdao.findById(rs.getInt("reiziger_id"));
                OVChipkaart ovChipkaart = new OVChipkaart(kaartNummer, geldigTot, klasse, saldo, reiziger);
                alleOVChipkaarten.add(ovChipkaart);

            }
            s.close();
            rs.close();

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return alleOVChipkaarten;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        List<OVChipkaart> ovChipKaarten = new ArrayList<>();

        try {
            String query = "SELECT kaart_nummer,geldig_tot,klasse,saldo,reiziger_id FROM ov_chipkaart WHERE reiziger_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, reiziger.getReizigerId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int kaartNummer = rs.getInt("kaart_nummer");
                Date geldigTot = rs.getDate("geldig_tot");
                int klasse = rs.getInt("klasse");
                double saldo = rs.getDouble("saldo");
                OVChipkaart ovChipkaart = new OVChipkaart(kaartNummer, geldigTot, klasse, saldo, reiziger);
                ovChipKaarten.add(ovChipkaart);
            }
            ps.close();
            rs.close();
        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return ovChipKaarten;
    }
}



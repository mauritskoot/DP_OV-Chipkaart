package project.persistence;

import jdk.jfr.Percentage;
import project.domein.Adres;
import project.domein.OVChipkaart;
import project.domein.Product;
import project.domein.Reiziger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOPsql implements ProductDAO {
    private Connection conn;
    private OVChipkaartDAO odao;

    public void setPdao(ProductDAO pdao) {
        this.conn = conn;
    }

    public void setOdao(OVChipkaartDAO odao) {
        this.odao = odao;
    }

    public ProductDAOPsql(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean save(Product product) {
        try {
            String query = "INSERT INTO product(product_nummer, naam, beschrijving, prijs)" + "VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, product.getProductNummer());
            ps.setString(2, product.getNaam());
            ps.setString(3, product.getBeschrijving());
            ps.setDouble(4, product.getPrijs());
            ps.execute();
            ps.close();

            String query2 = "INSERT INTO ov_chipkaart_product(kaart_nummer, product_nummer)" + "VALUES (?,?)";
            for (OVChipkaart ovChipkaart : product.getOvChipkaarten()) {
                PreparedStatement ps2 = conn.prepareStatement(query2);
                ps2.setInt(1, ovChipkaart.getKaartNummer());
                ps2.setInt(2, product.getProductNummer());
                ps2.executeUpdate();
                ps2.close();
            }


        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return true;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        try {
            String query = "UPDATE product SET product_nummer = ?, naam = ?, beschrijving = ?, prijs = ? WHERE product_nummer = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, product.getProductNummer());
            ps.setString(2, product.getNaam());
            ps.setString(3, product.getBeschrijving());
            ps.setDouble(4, product.getPrijs());
            ps.setInt(5, product.getProductNummer());
            ps.executeUpdate();
            ps.close();

            String query2 = "UPDATE ov_chipkaart_product SET kaartnummer = ? WHERE product_nummer = ?";
            for (OVChipkaart ovChipkaart : product.getOvChipkaarten()) {
                PreparedStatement ps2 = conn.prepareStatement(query2);
                ps2.setInt(1, ovChipkaart.getKaartNummer());
                ps2.setInt(2, product.getProductNummer());
                ps2.executeUpdate();
                ps2.close();
            }

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        try {
            String query = "DELETE FROM product WHERE product_nummer =?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, product.getProductNummer());
            ps.executeUpdate();
            ps.close();

            String query2 = "DELETE FROM ov_chipkaart_product WHERE kaart_nummer = ? AND product_nummer = ?";
            for (OVChipkaart ovChipkaart : product.getOvChipkaarten()) {
                PreparedStatement ps2 = conn.prepareStatement(query2);
                ps2.setInt(1, ovChipkaart.getKaartNummer());
                ps2.setInt(2, product.getProductNummer());
                ps2.executeUpdate();
                ps2.close();
            }

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return true;
    }

    public ArrayList<Product> findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException {
        ArrayList<Product> producten = new ArrayList<>();
        try {
            String query = "SELECT * FROM PRODUCT p JOIN ov_chipkaart_product op ON p.product_nummer = OP.product_nummer WHERE op.kaart_nummer = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ovChipkaart.getKaartNummer());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                producten.add(product);
            }

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return producten;
    }

    public ArrayList<Product> findAll() throws SQLException {
        ArrayList<Product> alleProducten = new ArrayList<>();
        try {
            String query = "SELECT * FROM product";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
                alleProducten.add(product);
            }

        } catch (SQLException sqe) {
            System.out.println(sqe.getMessage());
        }
        return alleProducten;
    }
}

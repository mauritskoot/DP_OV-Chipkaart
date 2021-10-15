package project.domein;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productNummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    private List<OVChipkaart> ovChipkaarten = new ArrayList<>();


    public Product(int product_nummer, String naam, String beschrijving, double prijs){
        this.productNummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public int getProductNummer() {
        return productNummer;
    }
    public void setProductNummer(int product_nummer) {
        this.productNummer = product_nummer;
    }
    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public String getBeschrijving() {
        return beschrijving;
    }
    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
    public double getPrijs() {
        return prijs;
    }
    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }
    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }
    public void setOvChipkaarten(List<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    public void addKaart(OVChipkaart ovChipkaart){
        if(!ovChipkaarten.contains(ovChipkaart)){
        ovChipkaarten.add(ovChipkaart);
        }
    }
    public void removeOVChipkaart(OVChipkaart ovChipkaart) {
        if (ovChipkaarten.contains(ovChipkaart)) {
            ovChipkaarten.remove(ovChipkaart);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Product{" +
                "product_nummer=" + productNummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                '}');
        for (OVChipkaart ovChipkaart : ovChipkaarten){
            sb.append(ovChipkaart);
        }
        return sb.toString();
    }
}

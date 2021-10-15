package project.domein;

import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaart {
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double saldo;
    private Reiziger reiziger;
    private List<Product> producten = new ArrayList<>();

    public OVChipkaart(int kaartNummer, Date geldigTot, int klasse, double saldo, Reiziger reiziger) {
        this.kaartNummer = kaartNummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
    }

    public int getKaartNummer() {
        return kaartNummer;
    }
    public void setKaartNummer(int kaartNummer) {
        this.kaartNummer = kaartNummer;
    }
    public Date getGeldigTot() {
        return geldigTot;
    }
    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }
    public int getKlasse() {
        return klasse;
    }
    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public Reiziger getReiziger() {
        return reiziger;
    }
    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }
    public List<Product> getProducten() {
        return producten;
    }
    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    public void addProduct(Product product){
        if(!producten.contains(product)){
            producten.add(product);
        }
    }

    public void removeProduct(Product product){
        if(producten.contains(product)){
            producten.remove(product);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OVChipkaart{" +
                "kaartNummer=" + kaartNummer +
                ", geldigTot=" + geldigTot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", reiziger=" + reiziger.getReizigerId() +
                '}');
        for (Product product : producten){
            sb.append(product);
        }
        return sb.toString();
    }
}

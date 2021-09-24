package project.domein;

import java.sql.Date;
import java.util.List;

public class Reiziger {
    private int reizigerId;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;
    private Adres adres;
    //list omdat één reiziger een of meerdere ov chipkaarten kan hebben
    private List<OVChipkaart> ovChipkaarten;

    public Reiziger(int reiziger_id, String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum){
        this.reizigerId = reiziger_id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getReizigerId() {
        return reizigerId;
    }

    public void setReizigerId(int reizigerId) {
        this.reizigerId = reizigerId;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void setOvChipkaarten(List<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }


    public String toString() {
        return "Reiziger{" +
                "reizigerId=" + reizigerId +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", adres=" + adres +
                //moet hier een whileloop voor meerder ovchipkaarten voor 1 reiziger?
                ", ovChipkaarten=" + ovChipkaarten +
                '}';
    }
}

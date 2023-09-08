/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Ucesnik extends AbstractDomainObject {

    private Long ucesnikID;
    private String imeUcesnika;
    private String prezimeUcesnika;
    private String email;
    private String telefon;

    @Override
    public String toString() {
        return imeUcesnika + " " + prezimeUcesnika;
    }

    public Ucesnik(Long putnikID, String imePutnika, String prezimePutnika, String email, String telefon) {
        this.ucesnikID = putnikID;
        this.imeUcesnika = imePutnika;
        this.prezimeUcesnika = prezimePutnika;
        this.email = email;
        this.telefon = telefon;
    }

    public Ucesnik() {
    }

    @Override
    public String nazivTabele() {
        return " Ucesnik ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Ucesnik u = new Ucesnik(rs.getLong("UcesnikID"),
                    rs.getString("ImeUcesnika"), rs.getString("PrezimeUcesnika"),
                    rs.getString("Email"), rs.getString("Telefon"));

            lista.add(u);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ImeUcesnika, PrezimeUcesnika, Email, Telefon) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " UcesnikID = " + ucesnikID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + imeUcesnika + "', '" + prezimeUcesnika + "', "
                + "'" + email + "', '" + telefon + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " email = '" + email + "', telefon = '" + telefon + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Long getUcesnikID() {
        return ucesnikID;
    }

    public void setUcesnikID(Long ucesnikID) {
        this.ucesnikID = ucesnikID;
    }

    public String getImeUcesnika() {
        return imeUcesnika;
    }

    public void setImeUcesnika(String imeUcesnika) {
        this.imeUcesnika = imeUcesnika;
    }

    public String getPrezimeUcesnika() {
        return prezimeUcesnika;
    }

    public void setPrezimeUcesnika(String prezimeUcesnika) {
        this.prezimeUcesnika = prezimeUcesnika;
    }

}

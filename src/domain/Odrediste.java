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
public class Odrediste extends AbstractDomainObject {
    
    private Long odredisteID;
    private String nazivOdredista;

    @Override
    public String toString() {
        return nazivOdredista;
    }

    public Odrediste(Long destinacijaID, String nazivDestinacije) {
        this.odredisteID = destinacijaID;
        this.nazivOdredista = nazivDestinacije;
    }

    public Odrediste() {
    }
    
    @Override
    public String nazivTabele() {
        return " Odrediste ";
    }

    @Override
    public String alijas() {
        return " o ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Odrediste o = new Odrediste(rs.getLong("OdredisteID"),
                    rs.getString("NazivOdredista"));

            lista.add(o);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " OdredisteID = " + odredisteID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

   

    public Long getOdredisteID() {
        return odredisteID;
    }

    public void setOdredisteID(Long odredisteID) {
        this.odredisteID = odredisteID;
    }

    public String getNazivOdredista() {
        return nazivOdredista;
    }

    public void setNazivOdredista(String nazivOdredista) {
        this.nazivOdredista = nazivOdredista;
    }
    
}

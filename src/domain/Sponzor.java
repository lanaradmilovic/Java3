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
public class Sponzor extends AbstractDomainObject {

    private Long sponzorID;
    private String naziv;
    private String grad;

    public Sponzor(Long sponzorID, String naziv, String grad) {
        this.sponzorID = sponzorID;
        this.naziv = naziv;
        this.grad = grad;
    }

    @Override
    public String toString() {
        return "Sponzor(" + "naziv=" + naziv + ", grad=" + grad + ')';
    }

    
    public Sponzor() {
    }

    @Override
    public String nazivTabele() {
        return " Sponzor ";
    }

    @Override
    public String alijas() {
        return " s ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Sponzor s = new Sponzor(rs.getLong("SponzorID"),
                    rs.getString("Naziv"), rs.getString("Grad"));

            lista.add(s);
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
        return " SponzorID = " + sponzorID;
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

    public Long getSponzorID() {
        return sponzorID;
    }

    public void setSponzorID(Long sponzorID) {
        this.sponzorID = sponzorID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

   
    

}

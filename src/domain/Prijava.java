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
public class Prijava extends AbstractDomainObject {

    private Trka trka;
    private int rbPrijave;
    private String napomena;
    private Ucesnik ucesnik;

    public Prijava(Trka trka, int rbPrijave, String napomena, Ucesnik putnik) {
        this.trka = trka;
        this.rbPrijave = rbPrijave;
        this.napomena = napomena;
        this.ucesnik = putnik;
    }

    public Prijava() {
    }

    @Override
    public String nazivTabele() {
        return " Prijava ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN UCESNIK U USING (UCESNIKID) "
                + "JOIN TRKA T USING (TRKAID) "
                + "JOIN ODREDISTE PO ON (PO.ODREDISTEID = T.POCETNOODREDISTEID) "
                + "JOIN ODREDISTE KO ON (KO.ODREDISTEID = T.KRAJNJEODREDISTEID) "
                + "JOIN SPONZOR S ON (S.SPONZORID = T.SPONZORID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = T.ADMINISTRATORID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Sponzor s = new Sponzor(rs.getLong("SponzorID"),
                    rs.getString("Naziv"), rs.getString("Grad"));

            Odrediste po = new Odrediste(rs.getLong("po.OdredisteID"),
                    rs.getString("po.NazivOdredista"));

            Odrediste ko = new Odrediste(rs.getLong("ko.OdredisteID"),
                    rs.getString("ko.NazivOdredista"));

            Trka t = new Trka(rs.getLong("trkaID"), rs.getTimestamp("datumVremePocetka"), rs.getString("opis"), 
                    po, ko, s, a, null);
            
            Ucesnik u = new Ucesnik(rs.getLong("UcesnikID"),
                    rs.getString("ImeUcesnika"), rs.getString("PrezimeUcesnika"),
                    rs.getString("Email"), rs.getString("Telefon"));
            
            Prijava p = new Prijava(t, rs.getInt("rbPrijave"), rs.getString("napomena"), u);

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (trkaID, rbPrijave, napomena, UcesnikID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " trkaID = " + trka.getTrkaID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + trka.getTrkaID()+ ", " + rbPrijave + ", "
                + "'" + napomena + "', " + ucesnik.getUcesnikID()+ " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        
        return " WHERE T.TRKAID = " + trka.getTrkaID()+ " "
                + "ORDER BY P.RBPRIJAVE";
    }

  

    public Trka getTrka() {
        return trka;
    }

    public void setTrka(Trka trka) {
        this.trka = trka;
    }

    public int getRbPrijave() {
        return rbPrijave;
    }

    public void setRbPrijave(int rbPrijave) {
        this.rbPrijave = rbPrijave;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Ucesnik getUcesnik() {
        return ucesnik;
    }

    public void setUcesnik(Ucesnik ucesnik) {
        this.ucesnik = ucesnik;
    }

   

    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Trka extends AbstractDomainObject {

    private Long trkaID;
    private Date datumVremePocetka;
    private String opis;
    private Odrediste pocetnoOdrediste;
    private Odrediste krajnjeOdrediste;
    private Sponzor sponzor;
    private Administrator administrator;
    private ArrayList<Prijava> prijave;

    public Trka(Long trkaID, Date datumVremePocetka, String opis, Odrediste pocetnoOdrediste, Odrediste krajnjeOdrediste, Sponzor sponzor, 
            Administrator administrator, ArrayList<Prijava> prijave) {
        this.trkaID = trkaID;
        this.datumVremePocetka = datumVremePocetka;
        this.opis = opis;
        this.pocetnoOdrediste = pocetnoOdrediste;
        this.krajnjeOdrediste = krajnjeOdrediste;
        this.sponzor = sponzor;
        this.administrator = administrator;
        this.prijave = prijave;
    }

  

    public Trka() {
    }

    @Override
    public String nazivTabele() {
        return " Trka ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN ODREDISTE PO ON (PO.ODREDISTEID = T.POCETNOODREDISTEID) "
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

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVremePocetka, opis, pocetnoOdredisteID,"
                + "krajnjeOdredisteID, sponzorID, administratorID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " trkaID = " + trkaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVremePocetka.getTime()) + "', "
                + "'" + opis + "', " + pocetnoOdrediste.getOdredisteID()+ ", "
                + krajnjeOdrediste.getOdredisteID()+ ", " + sponzor.getSponzorID()
                + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumVremePocetka = '" + new Timestamp(datumVremePocetka.getTime()) + "', "
                + "opis = '" + opis + "', sponzorID = " + sponzor.getSponzorID();
    }

    @Override
    public String uslov() {
       
        return "";
    }

    public Long getTrkaID() {
        return trkaID;
    }

    public void setTrkaID(Long trkaID) {
        this.trkaID = trkaID;
    }

    public Date getDatumVremePocetka() {
        return datumVremePocetka;
    }

    public void setDatumVremePocetka(Date datumVremePocetka) {
        this.datumVremePocetka = datumVremePocetka;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Odrediste getPocetnoOdrediste() {
        return pocetnoOdrediste;
    }

    public void setPocetnoOdrediste(Odrediste pocetnoOdrediste) {
        this.pocetnoOdrediste = pocetnoOdrediste;
    }

    public Odrediste getKrajnjeOdrediste() {
        return krajnjeOdrediste;
    }

    public void setKrajnjeOdrediste(Odrediste krajnjeOdrediste) {
        this.krajnjeOdrediste = krajnjeOdrediste;
    }

    public Sponzor getSponzor() {
        return sponzor;
    }

    public void setSponzor(Sponzor sponzor) {
        this.sponzor = sponzor;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<Prijava> getPrijave() {
        return prijave;
    }

    public void setPrijave(ArrayList<Prijava> prijave) {
        this.prijave = prijave;
    }

    
}

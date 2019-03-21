package com.example.mohammadabdolla.s309856mappe2;

public class Restaurant {

    public long _ID;
    public String Navn;
    public String Adresse;
    public String TelefonNummer;
    public String Type;

    public Restaurant(String navn, String adresse, String telefonNummer, String type) {
        Navn = navn;
        Adresse = adresse;
        TelefonNummer = telefonNummer;
        Type = type;
    }

    public Restaurant(long _ID, String navn, String adresse, String telefonNummer, String type) {
        this._ID = _ID;
        Navn = navn;
        Adresse = adresse;
        TelefonNummer = telefonNummer;
        Type = type;
    }

    public Restaurant(String navn, String addresse, String telefonNummer) {
        Navn = navn;
        Adresse = addresse;
        TelefonNummer = telefonNummer;
    }

    public Restaurant() {
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getNavn() {
        return Navn;
    }

    public void setNavn(String navn) {
        Navn = navn;
    }

    public String getTelefonNummer() {
        return TelefonNummer;
    }

    public void setTelefonNummer(String telefonNummer) {
        TelefonNummer = telefonNummer;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

}

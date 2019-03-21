package com.example.mohammadabdolla.s309856mappe2;

public class Venn {

    public long _ID;
    public String Navn;
    public String TelefonNummer;

    public Venn(String navn, String telefonNummer) {
        Navn = navn;
        TelefonNummer = telefonNummer;
    }

    public Venn(long _ID, String navn, String telefonNummer) {
        this._ID = _ID;
        Navn = navn;
        TelefonNummer = telefonNummer;
    }

    public Venn() {

    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
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
}

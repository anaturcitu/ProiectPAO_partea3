package biblioteca;
import csv.ServiciuAudit;

import static constants.Constants.LUCREAZA;
import static constants.Constants.NU_LUCREAZA;

public class Bibliotecar {
    private int idBibliotecar;
    private String nume;
    private String telefon;

    static final String[] zileDeMunca = {"luni", "marti", "joi", "vineri"}; // vector cu zilele in care lucreaza bibliotecarul

    public Bibliotecar(){}
    public Bibliotecar(int idBibliotecar, String nume, String telefon) {
        this.idBibliotecar = idBibliotecar;
        this.nume = nume;
        this.telefon = telefon;
    }

    public Bibliotecar(Bibliotecar bibliotecar) {}

    public Bibliotecar(String nume, String telefon) {
        this.nume = nume;
        this.telefon = telefon;
    }

    public int getIdBibliotecar() {
        return idBibliotecar;
    }

    public String getNume() {
        return nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public String lucreazaInAceastaZi(String zi) {
        for(String i : zileDeMunca) {
            if(i.equals(zi)) {
                ServiciuAudit.logAudit("Am verificat daca bibliotecarul lucreaza in ziua de " + zi);
                return LUCREAZA;
            }
        }
        ServiciuAudit.logAudit("Am verificat daca bibliotecarul lucreaza in ziua de " + zi);
        return NU_LUCREAZA;
    }

}

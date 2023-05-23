package biblioteca;

import csv_bd.ServiciuAudit;

public class BibliotecarSef extends Bibliotecar {
    int idBibliotecarSef;

    public BibliotecarSef(String nume, String telefon) {
        super(nume, telefon);
    }

    public BibliotecarSef(int idBibliotecarSef, String nume, String telefon) {
        super(nume, telefon);
        this.idBibliotecarSef = idBibliotecarSef;
    }

    public void afiseazaBibliotecarSef(int idBibliotecarSef, String nume, String telefon) {
        System.out.println(idBibliotecarSef + ", " + nume + ", " + telefon);
        ServiciuAudit.logAudit("Bibliotecarul sef a fost afisat.");
    }
}

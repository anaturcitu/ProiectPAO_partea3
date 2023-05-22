package biblioteca;

import csv.FisaImprumutUtilizatorCSV;
import csv.ServiciuAudit;
import exceptii.IdInvalidExceptie;
import service.Validare;

public class FisaImprumutUtilizator implements Validare {
    UtilizatorBiblioteca fisa;
    String telefon;
    int numarUtilizator;

    public FisaImprumutUtilizator(UtilizatorBiblioteca fisa, String telefon, int numarUtilizator) {
        this.fisa = fisa;
        this.telefon = telefon;
        this.numarUtilizator = numarUtilizator;
    }

//    public void afiseazaFisa() {
//        try {
//            if (!this.validare(this.numarUtilizator)) {
//                throw new IdInvalidExceptie("Numarul utilizatorului este invalid.");
//            }
//
//            System.out.println(this.fisa.getIdUtilizator());
//            System.out.println(this.fisa.getNume());
//            System.out.println(this.telefon);
//            System.out.println(this.numarUtilizator);
//        } catch (IdInvalidExceptie var2) {
//            System.out.println(var2.getMessage());
//        }
//
//    }

    public void afiseazaFisa() {
        try {
            if (validare(numarUtilizator)) {
                FisaImprumutUtilizatorCSV fisaImprumutUtilizatorCSV = FisaImprumutUtilizatorCSV.getInstance();
                fisaImprumutUtilizatorCSV.scrieFisaImprumutUtilizatorInCSV(fisa, telefon, numarUtilizator);
                ServiciuAudit.logAudit("Fisa utilizatorului cu numarul de utilizator " + numarUtilizator + " a fost afisata.");
            }
            else throw new IdInvalidExceptie("Numarul utilizatorului este invalid.");
        } catch (IdInvalidExceptie exceptie) {
            System.out.println(exceptie.getMessage());
        }
    }

    public boolean validare(int numar) {
        return numar >= 1000 && numar <= 9999;
    }
}

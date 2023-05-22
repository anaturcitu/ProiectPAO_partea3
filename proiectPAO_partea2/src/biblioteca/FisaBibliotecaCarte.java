package biblioteca;

import csv.FisaBibliotecaCarteCSV;
import exceptii.IdInvalidExceptie;
import service.Validare;

public class FisaBibliotecaCarte implements Validare {
    ExemplarCarte fisa;
    private static int numarCarte; // numarul cartii in biblioteca

    public static int getNumarCarte() {
        return numarCarte;
    }

    public FisaBibliotecaCarte(ExemplarCarte fisa, int numarCarte) {
        this.fisa = fisa;
        this.numarCarte = numarCarte;
    }

    public void afiseazaFisa() {
        try {
            if (validare(numarCarte)) {
                FisaBibliotecaCarteCSV fisaBibliotecaCarteCSV = FisaBibliotecaCarteCSV.getInstance();
                fisaBibliotecaCarteCSV.scrieFisaBibliotecaCarteInCSV(fisa, numarCarte);
            }
            else throw new IdInvalidExceptie("Numarul cartii este invalid.");
        } catch (IdInvalidExceptie exceptie) {
            System.out.println(exceptie.getMessage());
        }
    }
    @Override
    public boolean validare(int numar) {
        if(numar >= 10000 && numar <= 99999)
            return true;
        return false;
    }
}
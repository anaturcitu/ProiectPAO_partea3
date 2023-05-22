package csv;

import biblioteca.UtilizatorBiblioteca;

import java.io.*;

public class FisaImprumutUtilizatorCSV {
    private static FisaImprumutUtilizatorCSV instance;
    private FisaImprumutUtilizatorCSV() {}

    public static synchronized FisaImprumutUtilizatorCSV getInstance() {
        if (instance == null)
            instance = new FisaImprumutUtilizatorCSV();

        return instance;
    }

    public void scrieFisaImprumutUtilizatorInCSV(UtilizatorBiblioteca utilizatorBiblioteca, String telefon, int numarUtilizator) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Ana\\Desktop\\proiectPAO_partea2\\src\\csv\\fisaImprumutUtilizatorCSV.csv"))) {
            String line = utilizatorBiblioteca.getIdUtilizator() + ", " + utilizatorBiblioteca.getNume() + ", " + telefon + ", " + numarUtilizator;
            writer.write(line);
            writer.newLine();
            ServiciuAudit.logAudit("Fisa utilizatorului cu id-ul " + utilizatorBiblioteca.getIdUtilizator() + " a fost creata si afisata.");
        }
        catch (IOException e) {
            System.out.println("eroare la scrierea utilizatorului in fisierul CSV: " + e.getMessage());
        }
    }
}

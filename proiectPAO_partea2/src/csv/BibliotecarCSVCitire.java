package csv;

import biblioteca.Bibliotecar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BibliotecarCSVCitire {
    private static csv.BibliotecarCSVCitire instance;
    private BibliotecarCSVCitire() {}

    public static synchronized BibliotecarCSVCitire getInstance() {
        if (instance == null)
            instance = new BibliotecarCSVCitire();

        return instance;
    }

    public List<Bibliotecar> citesteBibliotecarDinCSV() {
        List<Bibliotecar> bibliotecari = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ana\\Desktop\\proiectPAO_partea2\\src\\csv\\bibliotecarCSVcitire.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(", ");
                int id = parseInt(columns[0]);
                String nume = columns[1];
                String telefon = columns[2];

                Bibliotecar bibliotecar = new Bibliotecar(id, nume, telefon);
                bibliotecari.add(bibliotecar);

                ServiciuAudit.logAudit("S-a verificat daca bibliotecarul lucreaza in ziua citita.");
            }
        } catch (IOException e) {
            System.out.println("eroare la citirea cartilor din CSV: " + e.getMessage());
        }
        return bibliotecari;
    }

//    public int citesteId() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Id bibliotecar: ");
//        int idBibliotecar = scanner.nextInt();
//        return idBibliotecar;
//    }
//
//    public String citesteNume() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Nume bibliotecar: ");
//        String nume = scanner.nextLine();
//        return nume;
//    }
//
//    public String citesteTelefon() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Telefon bibliotecar: ");
//        String telefon = scanner.nextLine();
//        return telefon;
//    }
}

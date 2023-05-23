package csv_bd;

import biblioteca.Bibliotecar;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class BibliotecarCSVCitire {
    private static BibliotecarCSVCitire instance;
    private BibliotecarCSVCitire() {}

    public static synchronized BibliotecarCSVCitire getInstance() {
        if (instance == null)
            instance = new BibliotecarCSVCitire();

        return instance;
    }

    public List<Bibliotecar> citesteBibliotecarDinCSV() {
        List<Bibliotecar> bibliotecari = new ArrayList<>();

//        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ana\\Desktop\\proiectPAO_partea2\\src\\csv_bd\\bibliotecarCSVcitire.csv"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] columns = line.split(", ");
//                int id = parseInt(columns[0]);
//                String nume = columns[1];
//                String telefon = columns[2];
//
//                Bibliotecar bibliotecar = new Bibliotecar(id, nume, telefon);
//                bibliotecari.add(bibliotecar);
//
//                ServiciuAudit.logAudit("S-a verificat daca bibliotecarul lucreaza in ziua citita.");
//            }
//        } catch (IOException e) {
//            System.out.println("eroare la citirea cartilor din CSV: " + e.getMessage());
//        }

        // CIESTE BIBLIOTECARII DIN BAZA DE DATE SI II AFISEAZA PE ECRAN:
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiectpao1", "root", "123456");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from bibliotecar");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id_bibliotecar"));
                System.out.println(resultSet.getString("nume"));
                System.out.println(resultSet.getString("telefon"));

                Bibliotecar bibliotecar = new Bibliotecar(resultSet.getInt("id_bibliotecar"), resultSet.getString("nume"), resultSet.getString("telefon"));
                bibliotecari.add(bibliotecar);
            }

//            Bibliotecar bibliotecar = new Bibliotecar(resultSet.getInt("id_bibliotecar"), resultSet.getString("nume"), resultSet.getString("telefon"));
//            bibliotecari.add(bibliotecar);
            ServiciuAudit.logAudit("Bibliotecarul a fost citit din baza de date si afisat pe ecran.");
        } catch (Exception e) {
            e.printStackTrace();
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

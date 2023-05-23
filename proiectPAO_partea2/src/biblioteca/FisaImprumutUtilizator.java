package biblioteca;

import csv_bd.ServiciuAudit;
import service.Validare;

import java.sql.*;

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

    public void introduceAfiseazaFisaBD() {
//        try {
//            if (validare(numarUtilizator)) {
//                FisaImprumutUtilizatorCSV fisaImprumutUtilizatorCSV = FisaImprumutUtilizatorCSV.getInstance();
//                fisaImprumutUtilizatorCSV.scrieFisaImprumutUtilizatorInCSV(fisa, telefon, numarUtilizator);
//                ServiciuAudit.logAudit("Fisa utilizatorului cu numarul de utilizator " + numarUtilizator + " a fost afisata.");
//            }
//            else throw new IdInvalidExceptie("Numarul utilizatorului este invalid.");
//        } catch (IdInvalidExceptie exceptie) {
//            System.out.println(exceptie.getMessage());
//        }


        // INTRODUCE FISA IN BAZA DE DATE:
        String url = "jdbc:mysql://localhost:3306/proiectpao1";
        String utilizator = "root";
        String parola = "123456";

        String sql = "INSERT INTO fisa_imprumut_utilizator (id_utilizator, nume, telefon, numarUtilizator) VALUES (?, ?, ?, ?)";

        try (Connection conexiune = DriverManager.getConnection(url, utilizator, parola);
             PreparedStatement preparedStatement = conexiune.prepareStatement(sql)) {
            // setarea valorilor parametrilor interogarii
            preparedStatement.setInt(1, fisa.getIdUtilizator());
            preparedStatement.setString(2, fisa.getNume());
            preparedStatement.setString(3, telefon);
            preparedStatement.setInt(4, numarUtilizator);

            // executarea interogarii
            int numarRanduriInserate = preparedStatement.executeUpdate();

            System.out.println(numarRanduriInserate + " rânduri au fost inserate în tabel.");
            ServiciuAudit.logAudit("Fisa utilizatorului a fost introdusa in baza de date.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // AFISEAZA FISA DIN BAZA DE DATE:
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiectpao1", "root", "123456");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from fisa_imprumut_utilizator");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id_utilizator"));
                System.out.println(resultSet.getString("nume"));
                System.out.println(resultSet.getString("telefon"));
                System.out.println(resultSet.getInt("numarUtilizator"));
            }
            ServiciuAudit.logAudit("Fisa utilizatorului a fost citita din baza de date si afisata pe ecran.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validare(int numar) {
        return numar >= 1000 && numar <= 9999;
    }
}

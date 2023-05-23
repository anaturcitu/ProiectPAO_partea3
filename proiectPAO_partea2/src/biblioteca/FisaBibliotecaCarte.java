package biblioteca;

import csv_bd.ServiciuAudit;
import service.Validare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
//        try {
//            if (validare(numarCarte)) {
//                FisaBibliotecaCarteCSV fisaBibliotecaCarteCSV = FisaBibliotecaCarteCSV.getInstance();
//                fisaBibliotecaCarteCSV.scrieFisaBibliotecaCarteInCSV(fisa, numarCarte);
//            }
//            else throw new IdInvalidExceptie("Numarul cartii este invalid.");
//        } catch (IdInvalidExceptie exceptie) {
//            System.out.println(exceptie.getMessage());
//        }



        // AFISEAZA FISA DIN BAZA DE DATE:
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiectpao1", "root", "123456");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from fisa_biblioteca_carte");

            while (resultSet.next()) {
                String titlu = resultSet.getString("titlu");
                String autor = resultSet.getString("autor");
                String editura = resultSet.getString("editura");
                String genLiterar = resultSet.getString("genLiterar");
                int nrPagini = resultSet.getInt("numarPagini");
                int anAparitie = resultSet.getInt("anAparitie");
                int idCarte = resultSet.getInt("idCarte");
                System.out.println(resultSet.getString("titlu"));
                System.out.println(resultSet.getString("autor"));
                System.out.println(resultSet.getString("editura"));
                System.out.println(resultSet.getString("genLiterar"));
                System.out.println(resultSet.getInt("numarPagini"));
                System.out.println(resultSet.getInt("anAparitie"));
                System.out.println(resultSet.getInt("idCarte"));
                System.out.println(resultSet.getInt("numarCarte"));

                ServiciuAudit.logAudit("Fisa cartii din baza de date a fsot citita si afisata.");

//                ExemplarCarte exemplarCarte = new ExemplarCarte(titlu, autor, editura, genLiterar, nrPagini, anAparitie, idCarte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean validare(int numar) {
        if(numar >= 10000 && numar <= 99999)
            return true;
        return false;
    }
}
package csv_bd;

import biblioteca.ExemplarCarte;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarteCSV {
    private static CarteCSV instance;
    private CarteCSV() {}

    public static synchronized CarteCSV getInstance() {
        if (instance == null)
            instance = new CarteCSV();

        return instance;
    }

    public List<ExemplarCarte> citesteCartiDinBD() {
        List<ExemplarCarte> carti = new ArrayList<>();

//        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ana\\Desktop\\proiectPAO_partea2\\src\\csv\\carteCSVcitire.csv"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] columns = line.split(", ");
//                String titlu = columns[0];
//                String autor = columns[1];
//                String editura = columns[2];
//                String genLiterar = columns[3];
//                int numarPagini = Integer.parseInt(columns[4]);
//                int anAparitie = Integer.parseInt(columns[5]);
//                int idCarte = Integer.parseInt(columns[6]);
//
//                ExemplarCarte exemplarCarte = new ExemplarCarte(titlu, autor, editura, genLiterar, numarPagini, anAparitie, idCarte);
//                carti.add(exemplarCarte);
//            }
//        } catch (IOException e) {
//            System.out.println("eroare la citirea cartilor din CSV: " + e.getMessage());
//        }

        // CITESTE CARTILE DIN BAZA DE DATE:
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proiectpao1", "root", "123456");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from carte1");

            while (resultSet.next()) {
                String titlu = resultSet.getString("titlu");
                String autor = resultSet.getString("autor");
                String editura = resultSet.getString("editura");
                String genLiterar = resultSet.getString("genLiterar");
                int nrPagini = resultSet.getInt("nrPagini");
                int anAparitie = resultSet.getInt("anAparitie");
                int idCarte = resultSet.getInt("idCarte");
                System.out.println(resultSet.getString("titlu"));
                System.out.println(resultSet.getString("autor"));
                System.out.println(resultSet.getString("editura"));
                System.out.println(resultSet.getString("genLiterar"));
                System.out.println(resultSet.getInt("nrPagini"));
                System.out.println(resultSet.getInt("anAparitie"));
                System.out.println(resultSet.getInt("idCarte"));

                ExemplarCarte exemplarCarte = new ExemplarCarte(titlu, autor, editura, genLiterar, nrPagini, anAparitie, idCarte);
                carti.add(exemplarCarte);
                            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carti;
    }

    public void scrieCartiInCSV(ExemplarCarte exemplarCarte) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Ana\\Desktop\\facultate an 2\\semestru 2\\PAO - Java\\proiectPAO_partea2.final\\src\\csv_bd\\carteCSVafisare.csv", true))) {
            String line = exemplarCarte.getTitlu() + ", " + exemplarCarte.getAutor() + ", " + exemplarCarte.getEditura() + ", " + exemplarCarte.getGenLiterar() + ", " + exemplarCarte.getNumarPagini() + ", " + exemplarCarte.getAnAparitie() + ", " + exemplarCarte.getIdCarte();
            writer.write(line);
            writer.newLine();
        }
        catch (IOException e) {
            System.out.println("eroare la scrierea produsului in CSV: " + e.getMessage());
        }
    }
}

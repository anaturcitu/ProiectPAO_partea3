package csv_bd;

import biblioteca.ExemplarCarte;

import java.sql.*;

public class FisaBibliotecaCarteCSV {
    private static FisaBibliotecaCarteCSV instance;
    private FisaBibliotecaCarteCSV() {}

    public static synchronized FisaBibliotecaCarteCSV getInstance() {
        if (instance == null)
            instance = new FisaBibliotecaCarteCSV();

        return instance;
    }

    public void scrieFisaBibliotecaCarteInBD(ExemplarCarte exemplarCarte, int numarCarte) { // numarCarte -> numarul cartii din biblioteca
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Ana\\Desktop\\proiectPAO_partea2\\src\\csv\\fisaBibliotecaCarteCSVafisare.csv", true))) {
//            String line = exemplarCarte.getTitlu() + ", " + exemplarCarte.getAutor() + ", " + exemplarCarte.getEditura() + ", " + exemplarCarte.getGenLiterar() + ", " + exemplarCarte.getNumarPagini() + ", " + exemplarCarte.getAnAparitie() + ", " + exemplarCarte.getIdCarte() + ", " + numarCarte;
//            writer.write(line);
//            writer.newLine();
//
//            ServiciuAudit.logAudit("Fisa cartii cu numarul " + numarCarte + " a fost creata si afisata.");
//        }
//        catch (IOException e) {
//            System.out.println("eroare la scrierea cartii in fisierul CSV: " + e.getMessage());
//        }

// scrie fisa cartii in baza de date
        String url = "jdbc:mysql://localhost:3306/proiectpao1";
        String utilizator = "root";
        String parola = "123456";

        String sql = "INSERT INTO fisa_biblioteca_carte (titlu, autor, editura, genLiterar, numarPagini, anAparitie, idCarte, numarCarte) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexiune = DriverManager.getConnection(url, utilizator, parola);
             PreparedStatement preparedStatement = conexiune.prepareStatement(sql)) {
            // setarea valorilor parametrilor interogarii
            preparedStatement.setString(1, exemplarCarte.getTitlu());
            preparedStatement.setString(2, exemplarCarte.getAutor());
            preparedStatement.setString(3, exemplarCarte.getEditura());
            preparedStatement.setString(4, exemplarCarte.getGenLiterar());
            preparedStatement.setInt(5, exemplarCarte.getNumarPagini());
            preparedStatement.setInt(6, exemplarCarte.getAnAparitie());
            preparedStatement.setInt(7, exemplarCarte.getIdCarte());
            preparedStatement.setInt(8, numarCarte);

            // executarea interogarii
            int numarRanduriInserate = preparedStatement.executeUpdate();

            System.out.println(numarRanduriInserate + " randuri au fost inserate în tabel.");
            ServiciuAudit.logAudit("Fisa cartii a fost inserata in tabel.");


        } catch (SQLException e) {
            e.printStackTrace();
        }
//////////////////////


    }
}

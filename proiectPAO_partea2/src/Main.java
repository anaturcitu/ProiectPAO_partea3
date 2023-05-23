import biblioteca.*;
import csv_bd.BibliotecarCSVCitire;
import csv_bd.CarteCSV;
import csv_bd.FisaBibliotecaCarteCSV;
import exceptii.OptiuneGresitaExceptie;

import java.util.List;
import java.util.Scanner;
import utile.CitireUtilizator;

public class Main {
    static int optiune;

    public Main() {
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.citireCarti();
        biblioteca.citireUtilizatori();
        System.out.println("Alegeti o optiune dintre urmatoarele:");
        System.out.println("1. Afisarea cartilor existente in biblioteca.");
        System.out.println("2. Afisarea tuturor utilizatorilor.");
        System.out.println("3. Adaugarea unei noi carti in biblioteca.");
        System.out.println("4. Imprumutarea unei carti de catre un utilizator.");
        System.out.println("5. Afiseaza cartile din baza de date, se introduce numarul cartii de latastatura, se introduce in bd si afiseaza fisa cartii din baza de date.");
        System.out.println("6. Creaza si afiseaza fisa utilizatorului citit de la tastatura. O introduce in baza de date si o afiseaza pe ecran.");
        System.out.println("7. Verifica daca un bibliotecar citit de la tastatura lucreaza intr-o anumita zi.");
        System.out.println("8. Citest un bibliotecar sef de la tastatura si il afiseaza.");
        System.out.println("9. Iesirea din aplicatie. ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Alege optiune: ");
        optiune = scanner.nextInt();
        boolean continua = true;

        while(continua) {
            try {
                switch (optiune) {
                    case 1:
                        biblioteca.afisareCarti();
                        System.out.print("Alege alta optiune: ");
                        optiune = scanner.nextInt();
                        break;
                    case 2:
                        biblioteca.afisareUtilizatori();
                        System.out.print("Alege alta optiune: ");
                        optiune = scanner.nextInt();
                        break;
                    case 3:
                        biblioteca.citireAdaugareCarteNoua();
                        biblioteca.afisareCarti();
                        System.out.print("Alege alta optiune: ");
                        optiune = scanner.nextInt();
                        break;
                    case 4:
                        biblioteca.imprumutareCarte();
                        System.out.print("Alege alta optiune: ");
                        optiune = scanner.nextInt();
                        break;
                    case 5:
                        CarteCSV carteCSV = CarteCSV.getInstance();
                        List<ExemplarCarte> carte = carteCSV.citesteCartiDinBD();
                        System.out.print("Numarul cartii: ");
                        int numarCarte = scanner.nextInt();

                        FisaBibliotecaCarte fisaBibliotecaCarte = new FisaBibliotecaCarte(carte.get(0), numarCarte);
                        FisaBibliotecaCarteCSV fisaBibliotecaCarteCSV = FisaBibliotecaCarteCSV.getInstance();
                        fisaBibliotecaCarteCSV.scrieFisaBibliotecaCarteInBD(carte.get(0), numarCarte);
//                        fisaBibliotecaCarteCSV.scrieFisaBibliotecaCarteInBD

                        fisaBibliotecaCarte.afiseazaFisa();
                        System.out.print("Alege alta optiune: ");
                        optiune = scanner.nextInt();
                        break;
                    case 6:
                        CitireUtilizator utilizator = new CitireUtilizator();
                        UtilizatorBiblioteca utilizatorBiblioteca1 = utilizator.citesteUtilizatorul();
                        System.out.print("Numarul utilizatorului: ");
                        int numarUtilizator = scanner.nextInt();
                        System.out.print("Telefonul utilizatorului: ");
                        String telefonUtilizator = scanner.next();
                        FisaImprumutUtilizator fisaImprumutUtilizator = new FisaImprumutUtilizator(utilizatorBiblioteca1, telefonUtilizator, numarUtilizator);
                        fisaImprumutUtilizator.introduceAfiseazaFisaBD();
                        System.out.print("Alege alta optiune: ");
                        optiune = scanner.nextInt();
                        break;
                    case 7:
                        BibliotecarCSVCitire bibliotecarCSVCitire = BibliotecarCSVCitire.getInstance();
                        List <Bibliotecar> bibliotecar = bibliotecarCSVCitire.citesteBibliotecarDinCSV();

                        System.out.print("Ziua saptamanii de verificat: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String zi = scanner1.nextLine();
                        Bibliotecar bibliotecar1 = new Bibliotecar(bibliotecar.get(0));
                        System.out.println(bibliotecar.get(0).lucreazaInAceastaZi(zi));
                        System.out.print("Alege alta optiune: ");
                        optiune = scanner.nextInt();
                        break;
                    case 8:
                        int id = scanner.nextInt();
                        String nume = scanner.next();
                        String telefon = scanner.next();

                        BibliotecarSef bibliotecarSef = new BibliotecarSef(id, nume, telefon);
                        bibliotecarSef.afiseazaBibliotecarSef(id, nume, telefon);
                        System.out.print("Alege alta optiune: ");
                        optiune = scanner.nextInt();
                        break;
                    default:
                        throw new OptiuneGresitaExceptie("Optiunea aleasa este gresita.");
                    case 9:
                        continua = false;
                }
            } catch (OptiuneGresitaExceptie var20) {
                System.out.println(var20.getMessage());
                break;
            }
        }
    }
}

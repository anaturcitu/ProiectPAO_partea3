package biblioteca;

import csv_bd.ServiciuAudit;
import exceptii.IdInexistentExceptie;
import exceptii.IdInvalidExceptie;
import exceptii.TitluInexistentExceptie;
import service.BibliotecaService;
import utile.Tuple;

import java.io.*;
import java.util.*;

public class Biblioteca implements BibliotecaService {
    private int nrTotalCarti = 0;
    private ExemplarCarte[] toateCartile = new ExemplarCarte[100001];
    private final UtilizatorBiblioteca[] totiUtilizatorii = new UtilizatorBiblioteca[10001];

    public Biblioteca() { }

    public UtilizatorBiblioteca[] getTotiUtilizatorii() {
        return totiUtilizatorii;
    }

    @Override
    public void citireCarti() { // citeste cartile din fisier CSV(toate cartile cu un anumit titlu, cu toate id-urile), diferita de cea din clasa CitireCarte(citeste o singura carte)
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ana\\Desktop\\proiectPAO_partea2\\src\\csv_bd\\carteCSVcitire.csv"))) {
            int i = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(", ");
                String titlu = columns[0];
                String autor = columns[1];
                String editura = columns[2];
                String genLiterar = columns[3];
                int numarPagini = Integer.parseInt(columns[4]);
                int anAparitie = Integer.parseInt(columns[5]);
                String idCarte = columns[6];

                Map<String, ArrayList<Tuple>> dictionarIduriExemplare = new HashMap<>();
                String idExemplar = idCarte;
                String[] id_uri = idExemplar.split(" ");
                ArrayList<Tuple> tupluri = new ArrayList<>();
                for (String id : id_uri) {
                    tupluri.add(new Tuple(Integer.parseInt(id), "disponibil"));
                    dictionarIduriExemplare.put(titlu, tupluri);
                }

                ExemplarCarte exemplarCarte = new ExemplarCarte(titlu, autor, editura, genLiterar, numarPagini, anAparitie, dictionarIduriExemplare);
                toateCartile[i] = exemplarCarte;
                i++;
                ServiciuAudit.logAudit("Cartea cu id-ul " + idCarte + " a fost citita.");
            }
            nrTotalCarti = i - 1;
        } catch (IOException e) {
            System.out.println("eroare la citirea cartilor din CSV: " + e.getMessage());
        }
    }
    @Override
    public void afisareCarti() { // le afiseaza in fisier CSV
        for (ExemplarCarte crt : toateCartile) {
            if (crt == null)
                break;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Ana\\Desktop\\proiectPAO_partea2\\src\\csv_bd\\carteCSVafisare.csv", true))) {
                String line = crt.getTitlu() + ", " + crt.getAutor() + ", " + crt.getEditura() + ", " + crt.getGenLiterar() + ", " + crt.getNumarPagini() + ", " + crt.getAnAparitie();


                for (Tuple element : crt.getDictionarIduriExemplare().get(crt.getTitlu())) {
                    try {
                        if(!crt.validare(element.getId()))
                            throw new IdInvalidExceptie("Id exemplar carte invalid.");
                        else
                            line += ", " + element.getId();

                    } catch (IdInvalidExceptie exceptie) {
                        line += ", " + exceptie.getMessage();
                    }
                }
                writer.write(line);
                writer.newLine();
                ServiciuAudit.logAudit("Cartea a fost citita si afisata.");
            }
            catch (IOException e) {
                System.out.println("eroare la scrierea cartii in CSV: " + e.getMessage());
            }

        }
    }
    @Override
    public void citireUtilizatori() {
        try {
            int i = 0;
            File file1 = new File("C:\\Users\\Ana\\Desktop\\proiectPAO_partea2\\src\\biblioteca\\utilizatori.in");
            Scanner scanner = new Scanner(file1);

            while (scanner.hasNextLine() || scanner.hasNextInt()) {
                String idUtilizatorStr = scanner.nextLine();
                int idUtilizator = Integer.parseInt(idUtilizatorStr);
                String nume = scanner.nextLine();
                UtilizatorBiblioteca utilizatorBiblioteca = new UtilizatorBiblioteca(idUtilizator, nume);

                totiUtilizatorii[i] = utilizatorBiblioteca;
                i++;
                ServiciuAudit.logAudit("Utilizatorul a fost citit.");
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit.");
        }
    }
    @Override
    public void afisareUtilizatori() {
        for (UtilizatorBiblioteca utl : getTotiUtilizatorii()) {
            if (utl == null)
                break;

            try {
                if(!utl.validare(utl.getIdUtilizator()))
                    throw new IdInvalidExceptie("Id utilizator invalid.");
                else
                    System.out.println(utl.getIdUtilizator() + " " + utl.getNume());
            } catch (IdInvalidExceptie exceptie) {
                System.out.println(exceptie.getMessage());
            }
        }
    }
    @Override
    public void citireAdaugareCarteNoua() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Titlu: ");
        String titlu = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Editura: ");
        String editura = scanner.nextLine();
        System.out.print("Gen literar: ");
        String genLiterar = scanner.nextLine();
        System.out.print("Numar pagini: ");
        String numarPaginiStr = scanner.nextLine();
        System.out.print("An aparitie: ");
        String anAparitieStr = scanner.nextLine();

        int numarPagini = Integer.parseInt(numarPaginiStr);
        int anAparitie = Integer.parseInt(anAparitieStr);

        Map<String, ArrayList<Tuple>> dictionarIduriExemplare = new HashMap<>();
        System.out.print("Id exemplar: ");
        String idExemplar = scanner.nextLine();
        String[] id_uri = idExemplar.split(" ");
        ArrayList<Tuple> tupluri = new ArrayList<>();
        for (String id : id_uri) {
            tupluri.add(new Tuple(Integer.parseInt(id), "disponibil"));
            dictionarIduriExemplare.put(titlu, tupluri);
        }

        ExemplarCarte exemplarCarte = new ExemplarCarte(titlu, autor, editura, genLiterar, numarPagini, anAparitie, dictionarIduriExemplare);
        toateCartile[++nrTotalCarti] = exemplarCarte;
        ServiciuAudit.logAudit("Cartea a fost citita si adaugata cu succes.");
    }

    @Override
    public void imprumutareCarte() {
        String titlu;
        int idCarte;
        int idUtilizator = 0;
        String numeUtilizator = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Numarul cartilor imprumutate: ");
        String nrCartiImprumutateStr = scanner.nextLine();
        int nrCartiImprumutate = Integer.parseInt(nrCartiImprumutateStr);

        // citesc fiecare carte imprumutata si ii modific starea din "disponibil" in "indisponibil" in tuplul corespunzator din Map:
        for (int i1 = 1; i1 <= nrCartiImprumutate; i1++) {
            System.out.print("Titlul cartii imprumutate: ");
            titlu = scanner.nextLine();

            System.out.print("Id-ul cartii imprumutate: ");
            String idCarteStr = scanner.nextLine();
            idCarte = Integer.parseInt(idCarteStr);

            // presupunem ca utilizatorul introdus este corect
            System.out.print("Id-ul utilizatorului care a imprumutat cartea: ");
            String idUtilizatorStr = scanner.nextLine();
            idUtilizator = Integer.parseInt(idUtilizatorStr);

            System.out.print("Numele utilizatorului care a imprumutat cartea: ");
            numeUtilizator = scanner.nextLine();

            // schimbarea starii cartii care a fost imprumutata:
            boolean okTitlu = false;
            boolean okId = false;
            try {
                for (ExemplarCarte i : toateCartile) {
                    if (i == null)
                        break;
                    if (i.getTitlu().equals(titlu)) try {
                        okTitlu = true;
                        int k = 0;
                        for (Tuple j : i.getDictionarIduriExemplare().get(titlu)) {
                            if (i.getDictionarIduriExemplare().get(titlu).get(k).getId() == idCarte) {
                                i.getDictionarIduriExemplare().get(titlu).get(k).modificaStare();
                                okId = true;
                                System.out.println("Cartea a fost imprumutata cu succes.");
                                // apelarea functiei care adauga cartea citita in istoricul utilizatorului:
                                UtilizatorBiblioteca utilizatorBiblioteca = new UtilizatorBiblioteca(idUtilizator, numeUtilizator);
                                utilizatorBiblioteca.adaugareIdInCartiImprumutate(idUtilizator, idCarte);
                            }
                            k++;
                        }

                        if (!okId)
                            throw new IdInexistentExceptie("Id-ul cartii citite nu exista in biblioteca.");
                    } catch (IdInexistentExceptie exceptie) {
                        System.out.println(exceptie.getMessage());
                        break;
                    }
                }
                if (!okTitlu)
                    throw new TitluInexistentExceptie("Titlul citit nu exista in biblioteca.");
            } catch (TitluInexistentExceptie e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
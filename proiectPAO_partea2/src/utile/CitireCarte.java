package utile;

import biblioteca.ExemplarCarte;
import java.util.Scanner;

public class CitireCarte {
    public CitireCarte() {
    }

    public ExemplarCarte citesteCartea() {
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
        int numarPagini = scanner.nextInt();
        System.out.print("An aparitie: ");
        int anAparitie = scanner.nextInt();
        System.out.print("Id carte: ");
        int idCarte = scanner.nextInt();
        ExemplarCarte exemplar = new ExemplarCarte(titlu, autor, editura, genLiterar, numarPagini, anAparitie, idCarte);
        return exemplar;
    }
}

package utile;

import biblioteca.UtilizatorBiblioteca;
import java.util.Scanner;

public class CitireUtilizator {
    public CitireUtilizator() {
    }

    public UtilizatorBiblioteca citesteUtilizatorul() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Id utilizator: ");
        int idUtilizator = scanner.nextInt();
        UtilizatorBiblioteca utilizator = new UtilizatorBiblioteca(idUtilizator, nume);
        return utilizator;
    }
}

package biblioteca;

import java.util.ArrayList;
import java.util.Map;
import service.Validare;
import utile.Tuple;

public class ExemplarCarte extends Carte implements Validare {
    int idCarte;

    public int getIdCarte() {
        return this.idCarte;
    }

    public ExemplarCarte() {}

    public ExemplarCarte(String titlu, String autor, String editura, String genLiterar, int numarPagini, int anAparitie, Map<String, ArrayList<Tuple>> dictionarIduriExemplare) {
        super(titlu, autor, editura, genLiterar, numarPagini, anAparitie, dictionarIduriExemplare);
    }

    public ExemplarCarte(String titlu, String autor, String editura, String genLiterar, int numarPagini, int anAparitie, int idCarte) {
        super(titlu, autor, editura, genLiterar, numarPagini, anAparitie);
        this.idCarte = idCarte;
    }

    public ExemplarCarte(String titlu, String autor, String editura, String genLiterar, int numarPagini, int anAparitie) {
        super(titlu, autor, editura, genLiterar, numarPagini, anAparitie);
    }

    public boolean validare(int id) {
        return id >= 1000 && id <= 9999;
    }
}

package biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import utile.Tuple;

abstract class Carte {
    private String titlu;
    private String autor;
    private String editura;
    private String genLiterar;
    private int numarPagini;
    private int anAparitie;
    private Map<String, ArrayList<Tuple>> dictionarIduriExemplare = new HashMap();

    public Carte() {
    }

    public Carte(String titlu, String autor, String editura, String genLiterar, int numarPagini, int anAparitie, Map<String, ArrayList<Tuple>> dictionarIduriExemplare) {
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.genLiterar = genLiterar;
        this.numarPagini = numarPagini;
        this.anAparitie = anAparitie;
        this.dictionarIduriExemplare = dictionarIduriExemplare;
    }

    public Carte(String titlu, String autor, String editura, String genLiterar, int numarPagini, int anAparitie) {
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.genLiterar = genLiterar;
        this.numarPagini = numarPagini;
        this.anAparitie = anAparitie;
    }

    public String getTitlu() {
        return this.titlu;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getEditura() {
        return this.editura;
    }

    public String getGenLiterar() {
        return this.genLiterar;
    }

    public int getNumarPagini() {
        return this.numarPagini;
    }

    public Map<String, ArrayList<Tuple>> getDictionarIduriExemplare() {
        return this.dictionarIduriExemplare;
    }

    public int getAnAparitie() {
        return this.anAparitie;
    }
}

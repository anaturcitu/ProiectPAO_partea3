package biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import csv.ServiciuAudit;
import service.Validare;

public class UtilizatorBiblioteca implements Validare {
    private int idUtilizator;
    private String nume;
    private Map<Integer, List<Integer>> istoricCartiImprumutate = new HashMap();

    public UtilizatorBiblioteca(int idUtilizator, String nume) {
        this.idUtilizator = idUtilizator;
        this.nume = nume;
    }


    public void setIstoricCartiImprumutate(Map<Integer, List<Integer>> istoricCartiImprumutate) {
        this.istoricCartiImprumutate = istoricCartiImprumutate;
    }

    public int getIdUtilizator() {
        return this.idUtilizator;
    }

    public String getNume() {
        return this.nume;
    }

    public void adaugareIdInCartiImprumutate(int idUtilizator, int idCarte) {
        List<Integer> lista = new ArrayList();
        if (this.istoricCartiImprumutate.get(idUtilizator) != null) {
            lista = (List)this.istoricCartiImprumutate.get(idUtilizator);
        }

        ((List)lista).add(idCarte);
        this.istoricCartiImprumutate.put(idUtilizator, lista);
        this.setIstoricCartiImprumutate(this.istoricCartiImprumutate);
        System.out.println("Cartea a fost adaugata in istoricul cartilor imprumutate ale utilizatorului.");
        ServiciuAudit.logAudit("Cartea a fost adaugata in istoricul cartilor imprumutate ale utilizatorului.");
    }

    public boolean validare(int id) {
        return id >= 100 && id <= 999;
    }
}

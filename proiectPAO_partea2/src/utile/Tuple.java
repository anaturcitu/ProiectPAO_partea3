package utile;

public class Tuple {
    private int id;
    private String stare;

    public Tuple(int id, String stare) {
        this.id = id;
        this.stare = stare;
    }

    public int getId() {
        return this.id;
    }

    public String modificaStare() {
        this.stare = "indisponibil";
        return this.stare;
    }
}

package entities;

import java.time.LocalDate;

public class Alimentation extends Carbon{

    private String typeAliment;
    private double poids;


    public Alimentation(double quantity, LocalDate startDate, LocalDate endDate, String typeAliment, double poids) {
        super(quantity, startDate, endDate);
        this.typeAliment = typeAliment;
        this.poids = poids;
    }

    public String getTypeAliment() {
        return typeAliment;
    }

    public void setTypeAliment(String typeAliment) {
        this.typeAliment = typeAliment;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }
}

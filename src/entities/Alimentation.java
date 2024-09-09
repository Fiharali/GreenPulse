package entities;

import entities.enums.TypeCarbon;

import java.time.LocalDate;

public class Alimentation extends Carbon{

    private int id;
    private String typeAliment;
    private double poids;


    public Alimentation(double quantity, LocalDate startDate, LocalDate endDate, String typeAliment, double poids) {
        super(quantity, startDate, endDate , TypeCarbon.ALIMENTATION);
        this.typeAliment = typeAliment;
        this.poids = poids;
    }  public Alimentation(int id ,double quantity, LocalDate startDate, LocalDate endDate, String typeAliment, double poids) {
        super(id ,quantity, startDate, endDate , TypeCarbon.ALIMENTATION);
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

    @Override
    public double calculerImpact() {
        double impactFactor = typeAliment.equalsIgnoreCase("viande") ? 5.0 : 0.5;
        return poids * impactFactor;
    }


    public String toString() {
        return "Transport [ID: " + this.getId() +
                ", Quantity: " + this.quantity +
                ", Start Date: " + this.startDate +
                ", End Date: " + this.endDate +
                ", poids: " + this.poids +
                ", Type de Alimentation : " + this.typeAliment + "]";
    }
}

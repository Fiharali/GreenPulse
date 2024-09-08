package entities;


import entities.enums.TypeCarbon;

import java.time.LocalDate;

public class Logement extends Carbon {
    private double consommationEnergie;
    private String typeEnergie;

    public double getConsommationEnergie() {
        return consommationEnergie;
    }

    public void setConsommationEnergie(double consommationEnergie) {
        this.consommationEnergie = consommationEnergie;
    }

    public String getTypeEnergie() {
        return typeEnergie;
    }

    public void setTypeEnergie(String typeEnergie) {
        this.typeEnergie = typeEnergie;
    }

    public Logement(double quantity, LocalDate startDate, LocalDate endDate, double consommationEnergie, String typeEnergie) {
        super(quantity, startDate, endDate, TypeCarbon.LOGEMENT);
        this.consommationEnergie = consommationEnergie;
        this.typeEnergie = typeEnergie;
    }

    @Override
    public double calculerImpact() {
        double impactFactor = typeEnergie.equalsIgnoreCase("électricité") ? 1.5 : 2.0;
        return consommationEnergie * impactFactor;
    }
}

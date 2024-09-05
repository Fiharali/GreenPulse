package entities;

import java.time.LocalDate;

public class Logement extends Carbon {
    private double consommationEnergie;  String typeEnergie;

    public Logement(double quantity, LocalDate startDate, LocalDate endDate, double consommationEnergie, String typeEnergie) {
        super(quantity, startDate, endDate);
        this.consommationEnergie = consommationEnergie;
        this.typeEnergie = typeEnergie;
    }

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
}

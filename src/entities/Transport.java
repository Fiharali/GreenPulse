package entities;


import entities.enums.TypeCarbon;

import java.time.LocalDate;

public class Transport extends Carbon {
    private double distanceParcourue;
    private String typeDeVehicule;

    public Transport(double quantity, LocalDate startDate, LocalDate endDate, double distanceParcourue, String typeDeVehicule) {
        super(quantity, startDate, endDate, TypeCarbon.TRANSPORT);
        this.distanceParcourue = distanceParcourue;
        this.typeDeVehicule = typeDeVehicule;
    }
    public Transport(int id , double quantity, LocalDate startDate, LocalDate endDate, double distanceParcourue, String typeDeVehicule) {
        super(id , quantity, startDate, endDate, TypeCarbon.TRANSPORT);
        this.distanceParcourue = distanceParcourue;
        this.typeDeVehicule = typeDeVehicule;
    }

    public double getDistanceParcourue() {
        return distanceParcourue;
    }

    public void setDistanceParcourue(double distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }

    public String getTypeDeVehicule() {
        return typeDeVehicule;
    }

    public void setTypeDeVehicule(String typeDeVehicule) {
        this.typeDeVehicule = typeDeVehicule;
    }

    @Override
    public double calculerImpact() {
        double impactFactor = typeDeVehicule.equalsIgnoreCase("voiture") ? 0.5 : 0.1;
        return distanceParcourue * impactFactor;
    }

    public String toString() {
        return "Transport [ID: " + this.getId() +
                ", Quantity: " + this.quantity +
                ", Start Date: " + this.startDate +
                ", End Date: " + this.endDate +
                ", Distance Parcourue: " + this.distanceParcourue +
                ", Type de Vehicule: " + this.typeDeVehicule + "]";
    }
}

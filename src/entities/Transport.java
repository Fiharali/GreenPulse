package entities;

import java.time.LocalDate;

public class Transport extends Carbon {
    private String typeDeVehicule;
    private Integer distanceParcourue;

    public Transport(double quantity, LocalDate startDate, LocalDate endDate, String typeDeVehicule, Integer distanceParcourue) {
        super(quantity, startDate, endDate);
        this.typeDeVehicule = typeDeVehicule;
        this.distanceParcourue = distanceParcourue;
    }


    public String getTypeDeVehicule() {
        return typeDeVehicule;
    }

    public void setTypeDeVehicule(String typeDeVehicule) {
        this.typeDeVehicule = typeDeVehicule;
    }

    public Integer getDistanceParcourue() {
        return distanceParcourue;
    }

    public void setDistanceParcourue(Integer distanceParcourue) {
        this.distanceParcourue = distanceParcourue;
    }


}

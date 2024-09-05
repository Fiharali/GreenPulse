package entities;

import java.time.LocalDate;

public class Carbon {

    private double quantity;
    private LocalDate startDate;
    private LocalDate endDate;

    public Carbon(double quantity, LocalDate startDate, LocalDate endDate) {
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
    }




    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double calculerImpact() {
        return quantity;
    }

}

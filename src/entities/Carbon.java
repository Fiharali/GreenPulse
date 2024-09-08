package entities;

import entities.enums.TypeCarbon;

import java.time.LocalDate;

public abstract  class Carbon {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected double quantity;
    protected LocalDate startDate;
    protected LocalDate endDate;
    public TypeCarbon type;



    public Carbon(double quantity, LocalDate startDate, LocalDate endDate, TypeCarbon type) {
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
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

    public abstract double calculerImpact();

}

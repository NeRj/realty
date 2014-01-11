package com.nerj.oop.realty.model;

/**
 * Модель недвижимости частного сектора
 */
public class PrivateSectorRealty extends Realty {
    private Double residentialArea;
    private Double neighborhoodArea;
    private Integer numberOfStoreys;
    private Integer numberOfWC;

    public Double getResidentialArea() {
        return residentialArea;
    }

    public void setResidentialArea(Double residentialArea) {
        this.residentialArea = residentialArea;
    }

    public Double getNeighborhoodArea() {
        return neighborhoodArea;
    }

    public void setNeighborhoodArea(Double neighborhoodArea) {
        this.neighborhoodArea = neighborhoodArea;
    }

    public Integer getNumberOfStoreys() {
        return numberOfStoreys;
    }

    public void setNumberOfStoreys(Integer numberOfStoreys) {
        this.numberOfStoreys = numberOfStoreys;
    }

    public Integer getNumberOfWC() {
        return numberOfWC;
    }

    public void setNumberOfWC(Integer numberOfWC) {
        this.numberOfWC = numberOfWC;
    }
}
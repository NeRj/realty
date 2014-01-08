package com.nerj.oop.realty.model;

/**
 * Модель жилой недвижимости
 */
public class ResidentialRealty extends Realty {
    private String type;
    private Double residentialArea;
    private Double kitchenArea;
    private String typeWC;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getResidentialArea() {
        return residentialArea;
    }

    public void setResidentialArea(Double residentialArea) {
        this.residentialArea = residentialArea;
    }

    public Double getKitchenArea() {
        return kitchenArea;
    }

    public void setKitchenArea(Double kitchenArea) {
        this.kitchenArea = kitchenArea;
    }

    public String getTypeWC() {
        return typeWC;
    }

    public void setTypeWC(String typeWC) {
        this.typeWC = typeWC;
    }
}

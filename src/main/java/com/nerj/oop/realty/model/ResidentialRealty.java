package com.nerj.oop.realty.model;

/**
 * Модель жилой недвижимости
 */
public class ResidentialRealty extends Realty {
    private String subtype;
    private Double residentialArea;
    private Double kitchenArea;
    private String typeWC;

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
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

package com.nerj.oop.realty.model;

import java.io.Serializable;

/**
 * Модель жилой недвижимости
 */
public class ResidentialRealty extends Realty implements Serializable{
    public static final String[] FIELD_NAMES =
            {"ID", "Название", "Адрес", "Площадь", "Комнаты", "Жил. площадь", "Площадь кухни",	"Санузел",	"Тип", "Цена"};

    private String subtype;
    private Double residentialArea;
    private Double kitchenArea;
    private String typeWC;

    public ResidentialRealty(){
        super();
        setType("residential");
        setResidentialArea(null);
        setKitchenArea(null);
        setTypeWC(null);
        setSubtype(null);
    }

    public ResidentialRealty(Realty realty){
        setId(realty.getId());
        setName(realty.getName());
        setAddress(realty.getAddress());
        setArea(realty.getArea());
        setNumberOfRooms(realty.getNumberOfRooms());
        setPrice(realty.getPrice());
        setType(realty.getType());
        setResidentialArea(null);
        setKitchenArea(null);
        setTypeWC(null);
        setSubtype(null);
    }

    public ResidentialRealty(ResidentialRealty residentialRealty, boolean isCopy){
        setId(residentialRealty.getId());
        setName(residentialRealty.getName());
        setAddress(residentialRealty.getAddress());
        setArea(residentialRealty.getArea());
        setNumberOfRooms(residentialRealty.getNumberOfRooms());
        setPrice(residentialRealty.getPrice());
        setType(residentialRealty.getType());
        setResidentialArea(residentialRealty.getResidentialArea());
        setKitchenArea(residentialRealty.getKitchenArea());
        setTypeWC(residentialRealty.getTypeWC());
        setSubtype(residentialRealty.getSubtype());
    }

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

    public Object[] toArray(){
        return new Object[] {getId(), getName(), getAddress(), getArea(), getNumberOfRooms(),
                getResidentialArea(), getKitchenArea(), getTypeWC(), getSubtype(), getPrice()};
    }
}

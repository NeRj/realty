package com.nerj.oop.realty.model;

/**
 * Модель жилой недвижимости
 */
public class ResidentialRealty extends Realty {
    public static String[] FIELD_NAMES =
            {"ID", "Название", "Адрес", "Площадь", "Комнаты", "Жил. площадь", "Площадь кухни",	"Санузел",	"Тип"};

    private String subtype;
    private Double residentialArea;
    private Double kitchenArea;
    private String typeWC;

    public ResidentialRealty(){
        setId(null);
        setName(null);
        setAddress(null);
        setArea(null);
        setNumberOfRooms(null);
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
        setType(realty.getType());
        setResidentialArea(null);
        setKitchenArea(null);
        setTypeWC(null);
        setSubtype(null);
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
                getResidentialArea(), getKitchenArea(), getTypeWC(), getSubtype()};
    }
}

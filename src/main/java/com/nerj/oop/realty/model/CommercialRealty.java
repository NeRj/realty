package com.nerj.oop.realty.model;

/**
 * Модель коммерческой недвижимости
 */
public class CommercialRealty extends Realty {
    public static String[] FIELD_NAMES = {"ID", "Название", "Адрес", "Площадь", "Комнаты", "Этаж",	"Тип"};

    private String subtype;
    private Integer storey;

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public Integer getStorey() {
        return storey;
    }

    public void setStorey(Integer storey) {
        this.storey = storey;
    }

    public Object[] toArray(){
        return new Object[] {getId(), getName(), getAddress(), getArea(), getNumberOfRooms(), getStorey(), getSubtype()};
    }
}

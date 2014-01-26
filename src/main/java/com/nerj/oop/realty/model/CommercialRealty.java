package com.nerj.oop.realty.model;

/**
 * Модель коммерческой недвижимости
 */
public class CommercialRealty extends Realty {
    public static String[] FIELD_NAMES = {"ID", "Название", "Адрес", "Площадь", "Помещения", "Этаж", "Тип", "Цена"};

    private String subtype;
    private Integer storey;

    public CommercialRealty(){
        super();
        setType("commercial");
        setStorey(null);
        setSubtype(null);
    }

    public CommercialRealty(Realty realty){
        setId(realty.getId());
        setName(realty.getName());
        setAddress(realty.getAddress());
        setArea(realty.getArea());
        setPrice(realty.getPrice());
        setNumberOfRooms(realty.getNumberOfRooms());
        setType(realty.getType());
        setStorey(null);
        setSubtype(null);
    }

    public CommercialRealty(CommercialRealty commercialRealty, boolean isCopy){
        setId(commercialRealty.getId());
        setName(commercialRealty.getName());
        setAddress(commercialRealty.getAddress());
        setArea(commercialRealty.getArea());
        setNumberOfRooms(commercialRealty.getNumberOfRooms());
        setPrice(commercialRealty.getPrice());
        setType(commercialRealty.getType());
        setStorey(commercialRealty.getStorey());
        setSubtype(commercialRealty.getSubtype());
    }

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
        return new Object[] {getId(), getName(), getAddress(), getArea(), getNumberOfRooms(), getStorey(), getSubtype(), getPrice()};
    }
}

package com.nerj.oop.realty.model;

import java.io.Serializable;

/**
 * Модель недвижимости частного сектора
 */
public class PrivateSectorRealty extends Realty implements Serializable{
    public static final String[] FIELD_NAMES =
            {"ID", "Название", "Адрес", "Площадь", "Комнаты", "Жил. площадь", "Прилегающая площадь", "Этажность", "Санузлы", "Цена"};

    private Double residentialArea;
    private Double neighborhoodArea;
    private Integer numberOfStoreys;
    private Integer numberOfWC;

    public PrivateSectorRealty(){
        super();
        setType("private");
        setResidentialArea(null);
        setNeighborhoodArea(null);
        setNumberOfStoreys(null);
        setNumberOfWC(null);
    }

    public PrivateSectorRealty(Realty realty){
        setId(realty.getId());
        setName(realty.getName());
        setAddress(realty.getAddress());
        setArea(realty.getArea());
        setNumberOfRooms(realty.getNumberOfRooms());
        setPrice(realty.getPrice());
        setType(realty.getType());
        setResidentialArea(null);
        setNeighborhoodArea(null);
        setNumberOfStoreys(null);
        setNumberOfWC(null);
    }

    public PrivateSectorRealty(PrivateSectorRealty privateSectorRealty, boolean isCopy){
        setId(privateSectorRealty.getId());
        setName(privateSectorRealty.getName());
        setAddress(privateSectorRealty.getAddress());
        setArea(privateSectorRealty.getArea());
        setNumberOfRooms(privateSectorRealty.getNumberOfRooms());
        setPrice(privateSectorRealty.getPrice());
        setType(privateSectorRealty.getType());
        setResidentialArea(privateSectorRealty.getResidentialArea());
        setNeighborhoodArea(privateSectorRealty.getNeighborhoodArea());
        setNumberOfStoreys(privateSectorRealty.getNumberOfStoreys());
        setNumberOfWC(privateSectorRealty.getNumberOfWC());
    }

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

    public Object[] toArray(){
        return new Object[] {getId(), getName(), getAddress(), getArea(), getNumberOfRooms(),
                getResidentialArea(), getNeighborhoodArea(), getNumberOfStoreys(), getNumberOfWC(), getPrice()};
    }
}

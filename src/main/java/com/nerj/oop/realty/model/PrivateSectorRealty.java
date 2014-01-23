package com.nerj.oop.realty.model;

/**
 * Модель недвижимости частного сектора
 */
public class PrivateSectorRealty extends Realty {
    public static String[] FIELD_NAMES =
            {"ID", "Название", "Адрес", "Площадь", "Комнаты", "Жил. площадь", "Прилегающая площадь", "Этажность", "Санузлы"};

    private Double residentialArea;
    private Double neighborhoodArea;
    private Integer numberOfStoreys;
    private Integer numberOfWC;

    public PrivateSectorRealty(){
        setId(null);
        setName(null);
        setAddress(null);
        setArea(null);
        setNumberOfRooms(null);
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
        setType(realty.getType());
        setResidentialArea(null);
        setNeighborhoodArea(null);
        setNumberOfStoreys(null);
        setNumberOfWC(null);
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
                getResidentialArea(), getNeighborhoodArea(), getNumberOfStoreys(), getNumberOfWC()};
    }
}

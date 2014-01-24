package com.nerj.oop.realty.model;

/**
 * Модель недвижимости
 */
public class Realty {
    public static String[] FIELD_NAMES = {"ID", "Название", "Адрес", "Площадь", "Комнаты", "Цена"};

    private Integer id;
    private String type;
    private String name;
    private String address;
    private Double area;
    private Integer numberOfRooms;
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Object[] toArray(){
        return new Object[] {getId(), getName(), getAddress(), getArea(), getNumberOfRooms()};
    }
}

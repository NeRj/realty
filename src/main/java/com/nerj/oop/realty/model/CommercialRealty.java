package com.nerj.oop.realty.model;

/**
 * Модель коммерческой недвижимости
 */
public class CommercialRealty extends Realty {
    private String type;
    private Integer storey;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStorey() {
        return storey;
    }

    public void setStorey(Integer storey) {
        this.storey = storey;
    }
}

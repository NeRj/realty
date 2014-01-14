package com.nerj.oop.realty.model;

/**
 * Модель коммерческой недвижимости
 */
public class CommercialRealty extends Realty {
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
}

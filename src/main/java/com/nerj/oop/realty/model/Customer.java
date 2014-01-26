package com.nerj.oop.realty.model;

import java.io.Serializable;

/**
 * Модель клиента риелторской организации
 */
public class Customer extends User implements Serializable {
    public static final String[] FIELD_NAMES = {"ID", "Имя", "Телефон", "Доп. инф-ция"};

    private String phone;
    private String additionalInfo;

    public Customer(){
        super();
        setPhone(null);
        setType("customer");
        setAdditionalInfo(null);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Object[] toArray(){
        return new Object[] {getId(), getName(), getPhone(), getAdditionalInfo()};
    }
}

package com.nerj.oop.realty.model;

/**
 * Модель клиента риелторской организации
 */
public class Customer extends User {
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

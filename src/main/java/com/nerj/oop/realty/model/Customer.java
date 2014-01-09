package com.nerj.oop.realty.model;

/**
 * Модель клиента риелторской организации
 */
public class Customer extends User {
    private String phone;
    private String additionalInfo;

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
}

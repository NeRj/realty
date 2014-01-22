package com.nerj.oop.realty.model;

/**
 * Модель клиента риелторской организации
 */
public class Customer extends User {
    public static String[] FIELD_NAMES = {"ID", "Имя", "Телефон", "Доп. инф-ция"};

    private String phone;
    private String type;
    private String additionalInfo;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

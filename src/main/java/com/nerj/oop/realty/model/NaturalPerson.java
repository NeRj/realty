package com.nerj.oop.realty.model;

import java.util.Date;

/**
 * Модель физического лица
 */
public class NaturalPerson extends Customer {
    public static String[] FIELD_NAMES = {"ID", "Имя", "Телефон", "Паспорт", "Дата рождения", "Доп. инф-ция"};

    private String passport;
    private Date birthDate;

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Object[] toArray(){
        return new Object[] {getId(), getName(), getPhone(), getPassport(), getBirthDate(), getAdditionalInfo()};
    }
}

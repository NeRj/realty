package com.nerj.oop.realty.model;

/**
 * Модель физического лица
 */
public class NaturalPerson extends Customer {
    private String passport;

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}

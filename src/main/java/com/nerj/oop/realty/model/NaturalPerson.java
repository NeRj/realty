package com.nerj.oop.realty.model;

import java.util.Date;

/**
 * Модель физического лица
 */
public class NaturalPerson extends Customer {
    public static String[] FIELD_NAMES = {"ID", "Имя", "Телефон", "Паспорт", "Дата рождения", "Доп. инф-ция"};

    private String passport;
    private Date birthDate;

    public NaturalPerson(){
        setId(null);
        setUsername(null);
        setPassword(null);
        setName(null);
        setPhone(null);
        setPassport(null);
        setBirthDate(null);
        setAdditionalInfo(null);
        setType("corporate");
    }

    public NaturalPerson(Customer customer){
        setId(customer.getId());
        setUsername(customer.getUsername());
        setPassword(customer.getPassword());
        setName(customer.getName());
        setPhone(customer.getPhone());
        setPassport(null);
        setBirthDate(null);
        setAdditionalInfo(customer.getAdditionalInfo());
        setType(customer.getType());
    }

    public NaturalPerson(NaturalPerson naturalPerson, boolean isCopy){
        setId(naturalPerson.getId());
        setUsername(naturalPerson.getUsername());
        setPassword(naturalPerson.getPassword());
        setName(naturalPerson.getName());
        setPhone(naturalPerson.getPhone());
        setPassport(naturalPerson.getPassport());
        setBirthDate(naturalPerson.getBirthDate());
        setAdditionalInfo(naturalPerson.getAdditionalInfo());
        setType(naturalPerson.getType());
    }

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
        return new Object[] {getId(), getName(), getPhone(), getPassport(),
                String.format("%td.%tm.%tY", getBirthDate(), getBirthDate(), getBirthDate()), getAdditionalInfo()};
    }
}

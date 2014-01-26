package com.nerj.oop.realty.model;

import java.util.Date;

/**
 * Модель юридического лица
 */
public class CorporatePersonhood extends Customer {
    public static String[] FIELD_NAMES =
            {"ID", "Имя", "Телефон", "Организация", "Дата регистрации", "Юр. адрес", "Доп. инф-ция"};

    private String organization;
    private Date foundationDate;
    private String corporateAddress;

    public CorporatePersonhood(){
        super();
        setCorporateAddress(null);
        setFoundationDate(null);
        setOrganization(null);
        setType("corporate");
    }

    public CorporatePersonhood(Customer customer){
        setId(customer.getId());
        setUsername(customer.getUsername());
        setPassword(customer.getPassword());
        setName(customer.getName());
        setPhone(customer.getPhone());
        setCorporateAddress(null);
        setFoundationDate(null);
        setOrganization(null);
        setAdditionalInfo(customer.getAdditionalInfo());
        setType(customer.getType());
    }

    public CorporatePersonhood(CorporatePersonhood corporatePersonhood, boolean isCopy){
        setId(corporatePersonhood.getId());
        setUsername(corporatePersonhood.getUsername());
        setPassword(corporatePersonhood.getPassword());
        setName(corporatePersonhood.getName());
        setPhone(corporatePersonhood.getPhone());
        setCorporateAddress(corporatePersonhood.getCorporateAddress());
        setFoundationDate(corporatePersonhood.getFoundationDate());
        setOrganization(corporatePersonhood.getOrganization());
        setAdditionalInfo(corporatePersonhood.getAdditionalInfo());
        setType(corporatePersonhood.getType());
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    public String getCorporateAddress() {
        return corporateAddress;
    }

    public void setCorporateAddress(String corporateAddress) {
        this.corporateAddress = corporateAddress;
    }

    public Object[] toArray(){
        return new Object[] {getId(), getName(), getPhone(), getOrganization(),
                String.format("%td.%tm.%tY", getFoundationDate(), getFoundationDate(), getFoundationDate()),
                getCorporateAddress(), getAdditionalInfo()};
    }
}

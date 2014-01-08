package com.nerj.oop.realty.model;

/**
 * Модель юридического лица
 */
public class CorporatePersonhood extends Customer {
    private String organization;
    private String corporateAddress;

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getCorporateAddress() {
        return corporateAddress;
    }

    public void setCorporateAddress(String corporateAddress) {
        this.corporateAddress = corporateAddress;
    }
}

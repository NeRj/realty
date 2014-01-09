package com.nerj.oop.realty.model;

import java.util.Date;

/**
 * Модель юридического лица
 */
public class CorporatePersonhood extends Customer {
    private String organization;
    private Date foundationDate;
    private String corporateAddress;

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
}

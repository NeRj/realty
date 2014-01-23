package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.CommercialRealty;
import com.nerj.oop.realty.model.PrivateSectorRealty;
import com.nerj.oop.realty.model.ResidentialRealty;

import java.util.List;

public interface RealtyDAO {
    public String getRealtyType(int id);
    public List<ResidentialRealty> listResidentialRealty();
    public ResidentialRealty getResidentialRealty(int id);
    public void addResidentialRealty(ResidentialRealty residentialRealty);
    public void updateResidentialRealty(ResidentialRealty residentialRealty);
    public List<PrivateSectorRealty> listPrivateSectorRealty();
    public PrivateSectorRealty getPrivateSectorRealty(int id);
    public void addPrivateSectorRealty(PrivateSectorRealty privateSectorRealty);
    public void updatePrivateSectorRealty(PrivateSectorRealty privateSectorRealty);
    public List<CommercialRealty> listCommercialRealty();
    public CommercialRealty getCommercialRealty(int id);
    public void addCommercialRealty(CommercialRealty commercialRealty);
    public void updateCommercialRealty(CommercialRealty commercialRealty);
    public void removeRealty(int id);
}

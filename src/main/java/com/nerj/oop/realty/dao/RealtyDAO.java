package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.CommercialRealty;
import com.nerj.oop.realty.model.PrivateSectorRealty;
import com.nerj.oop.realty.model.ResidentialRealty;

import java.util.List;

/**
 * Created by vlad on 21.01.14.
 */
public interface RealtyDAO {
    public List<ResidentialRealty> getResidentialRealty();
    public List<PrivateSectorRealty> getPrivateSectorRealty();
    public List<CommercialRealty> getCommercialRealty();
}

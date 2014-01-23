package com.nerj.oop.realty.service;

import com.nerj.oop.realty.exception.EmptyResultException;
import com.nerj.oop.realty.exception.EmptyStringException;
import com.nerj.oop.realty.exception.IncorrectChoiceException;

public interface RealtyService {
    public void showRealty() throws EmptyResultException, EmptyStringException, IncorrectChoiceException;
    public void addRealty();
    public void editRealty(int id);
    public void deleteRealty(int id);
}

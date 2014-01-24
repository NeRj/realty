package com.nerj.oop.realty.service;

import com.nerj.oop.realty.exception.EmptyResultException;
import com.nerj.oop.realty.exception.EmptyStringException;
import com.nerj.oop.realty.exception.IncorrectChoiceException;
import com.nerj.oop.realty.exception.NotExistsException;

public interface RealtyService {
    public void showRealty() throws EmptyResultException, EmptyStringException, IncorrectChoiceException, NotExistsException;
    public void addRealty();
    public void editRealty(int id);
    public void deleteRealty(int id);
}

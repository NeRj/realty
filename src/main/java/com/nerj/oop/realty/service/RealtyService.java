package com.nerj.oop.realty.service;

import com.nerj.oop.realty.exception.*;

public interface RealtyService {
    public void showRealty() throws EmptyResultException, EmptyStringException, IncorrectChoiceException, NotExistsException, NegativeNumberException;
    public void addRealty();
    public void editRealty(int id);
    public void deleteRealty(int id);
}

package com.nerj.oop.realty.model;

/**
 * Модель работника риелторской организации
 */
public class Employee extends User {
    private String position;

    public Employee(){
        setId(null);
        setUsername(null);
        setPassword(null);
        setUsername(null);
        setPosition(null);
    }

    public Employee(String username, String password, String name, String position){
        setId(null);
        setUsername(username);
        setPassword(password);
        setName(name);
        setPosition(position);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

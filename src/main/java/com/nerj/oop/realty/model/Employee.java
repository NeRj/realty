package com.nerj.oop.realty.model;

/**
 * Модель работника риелторской организации
 */
public class Employee extends User {
    public static final String[] FIELD_NAMES = {"ID", "Имя", "Должность"};

    private String position;

    public Employee(){
        super();
        setPosition(null);
    }

    public Employee(String username, String password, String name, String position){
        super(name, username, password);
        setPosition(position);
    }

    public Employee(Employee employee){
        setId(employee.getId());
        setUsername(employee.getUsername());
        setPassword(employee.getPassword());
        setName(employee.getName());
        setPosition(employee.getPosition());
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Object[] toArray(){
        return new Object[] {getId(), getName(), getPosition()};
    }
}

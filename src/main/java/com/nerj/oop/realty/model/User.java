package com.nerj.oop.realty.model;

/**
 * Модель пользователя системы
 */
public class User {
    public static String[] FIELD_NAMES = {"ID", "Имя"};

    private Integer id;
    private String name;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object[] toArray(){
        return new Object[] {getId(), getName()};
    }
}

package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.*;

import java.io.*;
import java.util.*;

public class UserDAOImpl implements UserDAO {

    private static final String FILE_NAME = "users.dat";

    @Override
    public boolean isExists(int id) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        User user = null;
        if (map != null)
            user = (User) map.get(id);
        return user != null;
    }

    @Override
    public void addUser(User user) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
            if (map != null){
                int lastID = getLastKey(map.keySet());
                user.setId(lastID + 1);
                map.put(lastID + 1, user);
            }
        } catch (EOFException e){
            map = new HashMap<Integer, Serializable>();
            user.setId(0);
            map.put(0, user);
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public User loginUser(String username, String password) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        User user = null;
        if (map != null)
            for (Map.Entry<Integer, Serializable> entry : map.entrySet()){
                User tempUser = (User) entry.getValue();
                if (tempUser.getUsername() != null && tempUser.getPassword() != null)
                    if (tempUser.getUsername().equals(username) && tempUser.getPassword().equals(password))
                        user = tempUser;
            }
        return user;
    }

    @Override
    public String getCustomerType(int id) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Customer customer = null;
        if (map != null)
            customer = (Customer) map.get(id);
        return customer.getType();
    }

    @Override
    public List<NaturalPerson> listNaturalPersons() {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        List<NaturalPerson> list = new ArrayList<NaturalPerson>();
        if (map != null)
            for (Map.Entry<Integer, Serializable> entry : map.entrySet())
                if (((User) entry.getValue()).getType().equals("natural"))
                    list.add((NaturalPerson) entry.getValue());
        return list;
    }

    @Override
    public NaturalPerson getNaturalPerson(int id) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        NaturalPerson naturalPerson = null;
        if (map != null)
            naturalPerson = (NaturalPerson) map.get(id);
        return naturalPerson;
    }

    @Override
    public void addNaturalPerson(NaturalPerson naturalPerson) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (map != null){
            int lastID = getLastKey(map.keySet());
            naturalPerson.setId(lastID + 1);
            map.put(lastID + 1, naturalPerson);
        } else {
            map = new HashMap<Integer, Serializable>();
            naturalPerson.setId(0);
            map.put(0, naturalPerson);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateNaturalPerson(int id, NaturalPerson naturalPerson) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (map != null){
            map.remove(id);
            naturalPerson.setId(id);
            map.put(id, naturalPerson);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<CorporatePersonhood> listCorporatePersonhoods() {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        List<CorporatePersonhood> list = new ArrayList<CorporatePersonhood>();
        if (map != null)
            for (Map.Entry<Integer, Serializable> entry : map.entrySet())
                if (((User) entry.getValue()).getType().equals("corporate"))
                    list.add((CorporatePersonhood) entry.getValue());
        return list;
    }

    @Override
    public CorporatePersonhood getCorporatePersonhood(int id) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        CorporatePersonhood corporatePersonhood = null;
        if (map != null)
            corporatePersonhood = (CorporatePersonhood) map.get(id);
        return corporatePersonhood;
    }

    @Override
    public void addCorporatePersonhood(CorporatePersonhood corporatePersonhood) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (map != null){
            int lastID = getLastKey(map.keySet());
            corporatePersonhood.setId(lastID + 1);
            map.put(lastID + 1, corporatePersonhood);
        } else {
            map = new HashMap<Integer, Serializable>();
            corporatePersonhood.setId(0);
            map.put(0, corporatePersonhood);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateCorporatePersonhood(int id, CorporatePersonhood corporatePersonhood) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (map != null){
            map.remove(id);
            corporatePersonhood.setId(id);
            map.put(id, corporatePersonhood);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> listEmployees() {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        List<Employee> list = new ArrayList<Employee>();
        if (map != null)
            for (Map.Entry<Integer, Serializable> entry : map.entrySet())
                if (((User) entry.getValue()).getType().equals("employee"))
                    list.add((Employee) entry.getValue());
        return list;
    }

    @Override
    public Employee getEmployee(int id) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Employee employee = null;
        if (map != null)
            employee = (Employee) map.get(id);
        return employee;
    }

    @Override
    public void addEmployee(Employee employee) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (map != null){
            int lastID = getLastKey(map.keySet());
            employee.setId(lastID + 1);
            map.put(lastID + 1, employee);
        } else {
            map = new HashMap<Integer, Serializable>();
            employee.setId(0);
            map.put(0, employee);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(int id, Employee employee) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (map != null){
            map.remove(id);
            employee.setId(id);
            map.put(id, employee);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(int id) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        if (map != null){
            map.remove(id);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private Map<Integer, Serializable> readObjectsFromFile() throws IOException, ClassNotFoundException, EOFException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
        Map<Integer, Serializable> map = null;
        try {
            map = (Map<Integer, Serializable>) ois.readObject();
        } catch (EOFException e){
            System.out.println("Файл, содержащий пользователей, пуст!");
        } finally {
            ois.close();
        }
        return map;
    }

    private void writeObjectsToFile(Map<Integer, Serializable> users) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        oos.writeObject(users);
        oos.flush();
        oos.close();
    }

    private int getLastKey(Set<Integer> keys){
        int maxKey = 0;
        for (Integer key : keys)
            if (key >= maxKey) maxKey = key;
        return maxKey;
    }
}

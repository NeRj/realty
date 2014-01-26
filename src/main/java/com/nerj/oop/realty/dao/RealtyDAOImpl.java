package com.nerj.oop.realty.dao;

import com.nerj.oop.realty.model.CommercialRealty;
import com.nerj.oop.realty.model.PrivateSectorRealty;
import com.nerj.oop.realty.model.Realty;
import com.nerj.oop.realty.model.ResidentialRealty;

import java.io.*;
import java.util.*;

public class RealtyDAOImpl implements RealtyDAO{

    private static final String FILE_NAME = "realty.dat";

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
        Realty realty = null;
        if (map != null)
            realty = (Realty) map.get(id);
        return realty != null;
    }

    @Override
    public List<ResidentialRealty> listResidentialRealty() {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (EOFException e){
            System.out.println("Файл пуст!");
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        List<ResidentialRealty> list = new ArrayList<ResidentialRealty>();
        if (map != null)
            for (Map.Entry<Integer, Serializable> entry : map.entrySet())
                if (((Realty) entry.getValue()).getType().equals("residential"))
                    list.add((ResidentialRealty) entry.getValue());
        return list;
    }

    @Override
    public List<PrivateSectorRealty> listPrivateSectorRealty() {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (EOFException e){
            System.out.println("Файл пуст!");
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        List<PrivateSectorRealty> list = new ArrayList<PrivateSectorRealty>();
        if (map != null)
            for (Map.Entry<Integer, Serializable> entry : map.entrySet())
                if (((Realty) entry.getValue()).getType().equals("private"))
                    list.add((PrivateSectorRealty) entry.getValue());
        return list;
    }

    @Override
    public List<CommercialRealty> listCommercialRealty() {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (EOFException e){
            System.out.println("Файл пуст!");
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        List<CommercialRealty> list = new ArrayList<CommercialRealty>();
        if (map != null)
            for (Map.Entry<Integer, Serializable> entry : map.entrySet())
                if (((Realty) entry.getValue()).getType().equals("commercial"))
                    list.add((CommercialRealty) entry.getValue());
        return list;
    }

    @Override
    public String getRealtyType(int id) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Realty realty = null;
        if (map != null)
            realty = (Realty) map.get(id);
        return realty.getType();
    }

    @Override
    public ResidentialRealty getResidentialRealty(int id) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        ResidentialRealty residentialRealty = null;
        if (map != null)
            residentialRealty = (ResidentialRealty) map.get(id);
        return residentialRealty;
    }

    @Override
    public void addResidentialRealty(ResidentialRealty residentialRealty) {
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
            residentialRealty.setId(lastID + 1);
            map.put(lastID + 1, residentialRealty);
        } else {
            map = new HashMap<Integer, Serializable>();
            residentialRealty.setId(0);
            map.put(0, residentialRealty);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateResidentialRealty(int id, ResidentialRealty residentialRealty) {
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
            residentialRealty.setId(id);
            map.put(id, residentialRealty);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public PrivateSectorRealty getPrivateSectorRealty(int id) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        PrivateSectorRealty privateSectorRealty = null;
        if (map != null)
            privateSectorRealty = (PrivateSectorRealty) map.get(id);
        return privateSectorRealty;
    }

    @Override
    public void addPrivateSectorRealty(PrivateSectorRealty privateSectorRealty) {
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
            privateSectorRealty.setId(lastID + 1);
            map.put(lastID + 1, privateSectorRealty);
        } else {
            map = new HashMap<Integer, Serializable>();
            privateSectorRealty.setId(0);
            map.put(0, privateSectorRealty);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePrivateSectorRealty(int id, PrivateSectorRealty privateSectorRealty) {
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
            privateSectorRealty.setId(id);
            map.put(id, privateSectorRealty);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public CommercialRealty getCommercialRealty(int id) {
        Map<Integer, Serializable> map = null;
        try {
            map = readObjectsFromFile();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        CommercialRealty commercialRealty = null;
        if (map != null)
            commercialRealty = (CommercialRealty) map.get(id);
        return commercialRealty;
    }

    @Override
    public void addCommercialRealty(CommercialRealty commercialRealty) {
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
            commercialRealty.setId(lastID + 1);
            map.put(lastID + 1, commercialRealty);
        } else {
            map = new HashMap<Integer, Serializable>();
            commercialRealty.setId(0);
            map.put(0, commercialRealty);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateCommercialRealty(int id, CommercialRealty commercialRealty) {
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
            commercialRealty.setId(id);
            map.put(id, commercialRealty);
        }
        try {
            writeObjectsToFile(map);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeRealty(int id) {
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

    private Map<Integer, Serializable> readObjectsFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
        Map<Integer, Serializable> map = null;
        try {
            map = (Map<Integer, Serializable>) ois.readObject();
        } catch (EOFException e){
            System.out.println("Файл, содержащий объекты недвижимости, пуст!");
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

package com.nerj.oop.realty.service;

import com.nerj.oop.realty.dao.RealtyDAO;
import com.nerj.oop.realty.dao.RealtyDAOImpl;
import com.nerj.oop.realty.exception.*;
import com.nerj.oop.realty.model.CommercialRealty;
import com.nerj.oop.realty.model.PrivateSectorRealty;
import com.nerj.oop.realty.model.Realty;
import com.nerj.oop.realty.model.ResidentialRealty;
import dnl.utils.text.table.TextTable;

import java.util.List;

public class RealtyServiceImpl implements RealtyService {
    private RealtyDAO realtyDAO;

    public RealtyServiceImpl(){
        realtyDAO = new RealtyDAOImpl();
    }

    @Override
    public void showRealty() throws EmptyResultException, EmptyStringException, IncorrectChoiceException, NotExistsException, NegativeNumberException {
        List<ResidentialRealty> residentialRealties = realtyDAO.listResidentialRealty();
        List<PrivateSectorRealty> privateSectorRealties = realtyDAO.listPrivateSectorRealty();
        List<CommercialRealty> commercialRealties = realtyDAO.listCommercialRealty();

        if (residentialRealties.isEmpty() && privateSectorRealties.isEmpty() && commercialRealties.isEmpty())
            throw new EmptyResultException();
        if (!residentialRealties.isEmpty()){
            System.out.println("КВАРТИРЫ:");
            Object[][] data = new Object[residentialRealties.size()][ResidentialRealty.FIELD_NAMES.length];
            for (int i = 0; i < residentialRealties.size(); i++)
                data[i] = residentialRealties.get(i).toArray();
            printTable(ResidentialRealty.FIELD_NAMES, data);
        }
        if (!privateSectorRealties.isEmpty()){
            System.out.println("ЧАСТНЫЙ СЕКТОР:");
            Object[][] data = new Object[privateSectorRealties.size()][PrivateSectorRealty.FIELD_NAMES.length];
            for (int i = 0; i < privateSectorRealties.size(); i++)
                data[i] = privateSectorRealties.get(i).toArray();
            printTable(PrivateSectorRealty.FIELD_NAMES, data);
        }
        if (!commercialRealties.isEmpty()){
            System.out.println("НЕЖИЛАЯ НЕДВИЖИМОСТЬ:");
            Object[][] data = new Object[residentialRealties.size()][ResidentialRealty.FIELD_NAMES.length];
            for (int i = 0; i < residentialRealties.size(); i++)
                data[i] = residentialRealties.get(i).toArray();
            printTable(ResidentialRealty.FIELD_NAMES, data);
        }

        System.out.print("e - изменить; d - удалить, a - добавить: ");
        String choice = System.console().readLine();
        if (choice == null || choice.equals(""))
            throw new EmptyStringException();
        else if (choice.charAt(0) == 'a')
            addRealty();
        else if (choice.charAt(0) == 'd'){
            System.out.print("Введите ID объекта недвижимости: ");
            String subChoice = System.console().readLine();
            if (subChoice == null || choice.equals(""))
                throw new EmptyStringException();
            try {
                Integer id = Integer.parseInt(subChoice);
                if (id < 0) throw new NegativeNumberException();
                if (realtyDAO.isExists(id))
                    deleteRealty(id);
                else throw new NotExistsException("Объект недвижимости с указанным ID не найден!");
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        } else if (choice.charAt(0) == 'e'){
            System.out.print("Введите ID объекта недвижимости: ");
            String subChoice = System.console().readLine();
            if (subChoice == null || choice.equals(""))
                throw new EmptyStringException();
            try {
                Integer id = Integer.parseInt(subChoice);
                if (id < 0) throw new NegativeNumberException();
                if (realtyDAO.isExists(id))
                    editRealty(id);
                else throw new NotExistsException("Объект недвижимости с указанным ID не найден!");
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        } else throw new IncorrectChoiceException();
    }

    @Override
    public void addRealty(){
        Realty realty = new Realty();
        System.out.print("Название: ");
        realty.setName(System.console().readLine());
        System.out.print("Адрес: ");
        realty.setAddress(System.console().readLine());
        while (true){
            System.out.print("Общая площадь: ");
            try {
                Double in = Double.parseDouble(System.console().readLine());
                if (in < 0) in = (double) 0;
                realty.setArea(in);
                break;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        while (true){
            System.out.print("Количество комнат/помещений: ");
            try {
                Integer in = Integer.parseInt(System.console().readLine());
                if (in < 0) in = 0;
                realty.setNumberOfRooms(in);
                break;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        while (true){
            System.out.print("Тип (r - квартира, p - частаная, c - нежилая): ");
            String choice = System.console().readLine();
            if (choice == null || choice.isEmpty())
                System.out.println("Выберите тип!");
            else if (choice.charAt(0) == 'r'){
                realty.setType("residential");
                ResidentialRealty residentialRealty = new ResidentialRealty(realty);
                while (true){
                    System.out.print("Жилая площадь: ");
                    try {
                        Double in = Double.parseDouble(System.console().readLine());
                        if (in < 0) in = (double) 0;
                        residentialRealty.setResidentialArea(in);
                        break;
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                while (true){
                    System.out.print("Площадь кухни: ");
                    try {
                        Double in = Double.parseDouble(System.console().readLine());
                        if (in < 0) in = (double) 0;
                        residentialRealty.setKitchenArea(in);
                        break;
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.print("Тип санузла: ");
                residentialRealty.setTypeWC(System.console().readLine());
                System.out.print("Тип квартиры: ");
                residentialRealty.setSubtype(System.console().readLine());

                realtyDAO.addResidentialRealty(residentialRealty);
                break;
            } else if (choice.charAt(0) == 'p'){
                realty.setType("private");
                PrivateSectorRealty privateSectorRealty = new PrivateSectorRealty(realty);
                System.out.print("Жилая площадь: ");
                privateSectorRealty.setResidentialArea(Double.parseDouble(System.console().readLine()));
                while (true){
                    System.out.print("Площадь прилегающей территории: ");
                    try {
                        Double in = Double.parseDouble(System.console().readLine());
                        if (in < 0) in = (double) 0;
                        privateSectorRealty.setNeighborhoodArea(in);
                        break;
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                while (true){
                    System.out.print("Количество этажей: ");
                    try {
                        Integer in = Integer.parseInt(System.console().readLine());
                        if (in < 0) in = 0;
                        privateSectorRealty.setNumberOfStoreys(in);
                        break;
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                }
                while (true){
                    System.out.print("Количество санузлов: ");
                    try {
                        Integer in = Integer.parseInt(System.console().readLine());
                        if (in < 0) in = 0;
                        privateSectorRealty.setNumberOfWC(in);
                        break;
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                }

                realtyDAO.addPrivateSectorRealty(privateSectorRealty);
                break;
            } else if (choice.charAt(0) == 'c'){
                realty.setType("commercial");
                CommercialRealty commercialRealty = new CommercialRealty(realty);
                while (true){
                    System.out.print("Этаж: ");
                    try {
                        Integer in = Integer.parseInt(System.console().readLine());
                        if (in < 0) in = 0;
                        commercialRealty.setStorey(in);
                        break;
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                System.out.print("Тип помещения: ");
                commercialRealty.setSubtype(System.console().readLine());

                realtyDAO.addCommercialRealty(commercialRealty);
                break;
            } else System.out.println("Выберите тип!");
        }
        while (true){
            System.out.print("Цена: ");
            try {
                Double in = Double.parseDouble(System.console().readLine());
                if (in < 0) in = (double) 0;
                realty.setArea(in);
                break;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Объект недвижимости добавлен!");
    }

    @Override
    public void editRealty(int id){
        String type = realtyDAO.getRealtyType(id);
        if (type.equals("residential")){
            ResidentialRealty residentialRealty = realtyDAO.getResidentialRealty(id);

            String in = null;
            System.out.print("Название [" + residentialRealty.getName() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                residentialRealty.setName(in);
            System.out.print("Адрес [" + residentialRealty.getAddress() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                residentialRealty.setAddress(in);
            while (true){
                System.out.print("Общая площадь [" + residentialRealty.getArea() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Double d = Double.parseDouble(in);
                        if (d < 0) d = (double) 0;
                        residentialRealty.setArea(d);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            while (true){
                System.out.print("Количество комнат [" + residentialRealty.getNumberOfRooms() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Integer i = Integer.parseInt(in);
                        if (i < 0) i = 0;
                        residentialRealty.setNumberOfRooms(i);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            while (true){
                System.out.print("Жилая площадь [" + residentialRealty.getResidentialArea() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Double d = Double.parseDouble(in);
                        if (d < 0) d = (double) 0;
                        residentialRealty.setResidentialArea(d);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            while (true){
                System.out.print("Площадь кухни [" + residentialRealty.getKitchenArea() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Double d = Double.parseDouble(in);
                        if (d < 0) d = (double) 0;
                        residentialRealty.setKitchenArea(d);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.print("Тип санузла [" + residentialRealty.getTypeWC() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                residentialRealty.setTypeWC(in);
            System.out.print("Тип квартиры [" + residentialRealty.getSubtype() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                residentialRealty.setSubtype(in);
            while (true){
                System.out.print("Цена [" + residentialRealty.getPrice() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Double d = Double.parseDouble(in);
                        if (d < 0) d = (double) 0;
                        residentialRealty.setPrice(d);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

            realtyDAO.updateResidentialRealty(id, residentialRealty);
        } else if (type.equals("private")){
            PrivateSectorRealty privateSectorRealty = realtyDAO.getPrivateSectorRealty(id);

            String in = null;
            System.out.print("Название [" + privateSectorRealty.getName() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                privateSectorRealty.setName(in);
            System.out.print("Адрес [" + privateSectorRealty.getAddress() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                privateSectorRealty.setAddress(in);
            while (true){
                System.out.print("Общая площадь [" + privateSectorRealty.getArea() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Double d = Double.parseDouble(in);
                        if (d < 0) d = (double) 0;
                        privateSectorRealty.setArea(d);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            while (true){
                System.out.print("Количество комнат [" + privateSectorRealty.getNumberOfRooms() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Integer i = Integer.parseInt(in);
                        if (i < 0) i = 0;
                        privateSectorRealty.setNumberOfRooms(i);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            while (true){
                System.out.print("Жилая площадь [" + privateSectorRealty.getResidentialArea() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Double d = Double.parseDouble(in);
                        if (d < 0) d = (double) 0;
                        privateSectorRealty.setResidentialArea(d);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            while (true){
                System.out.print("Площадь прилегающей территории [" + privateSectorRealty.getNeighborhoodArea() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Double d = Double.parseDouble(in);
                        if (d < 0) d = (double) 0;
                        privateSectorRealty.setNeighborhoodArea(d);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            while (true){
                System.out.print("Количество этажей [" + privateSectorRealty.getNumberOfStoreys() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Integer i = Integer.parseInt(in);
                        if (i < 0) i = 0;
                        privateSectorRealty.setNumberOfStoreys(i);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            while (true){
                System.out.print("Количество санузлов [" + privateSectorRealty.getNumberOfWC() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Integer i = Integer.parseInt(in);
                        if (i < 0) i = 0;
                        privateSectorRealty.setNumberOfWC(i);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            while (true){
                System.out.print("Цена [" + privateSectorRealty.getPrice() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Double d = Double.parseDouble(in);
                        if (d < 0) d = (double) 0;
                        privateSectorRealty.setPrice(d);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

            realtyDAO.updatePrivateSectorRealty(id, privateSectorRealty);
        } else if (type.equals("commercial")){
            CommercialRealty commercialRealty = realtyDAO.getCommercialRealty(id);

            String in = null;
            System.out.print("Название [" + commercialRealty.getName() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                commercialRealty.setName(in);
            System.out.print("Адрес [" + commercialRealty.getAddress() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                commercialRealty.setAddress(in);
            while (true){
                System.out.print("Площадь [" + commercialRealty.getArea() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Double d = Double.parseDouble(in);
                        if (d < 0) d = (double) 0;
                        commercialRealty.setArea(d);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            while (true){
                System.out.print("Количество помещений [" + commercialRealty.getNumberOfRooms() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Integer i = Integer.parseInt(in);
                        if (i < 0) i = 0;
                        commercialRealty.setNumberOfRooms(i);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            while (true){
                System.out.print("Этаж [" + commercialRealty.getStorey() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Integer i = Integer.parseInt(in);
                        if (i < 0) i = 0;
                        commercialRealty.setStorey(i);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.print("Тип помещения [" + commercialRealty.getSubtype() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                commercialRealty.setSubtype(in);
            while (true){
                System.out.print("Цена [" + commercialRealty.getPrice() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" ")){
                        Double d = Double.parseDouble(in);
                        if (d < 0) d = (double) 0;
                        commercialRealty.setPrice(d);
                    }
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

            realtyDAO.updateCommercialRealty(id, commercialRealty);
        }

        System.out.println("Объект недвижимости изменен изменен!");
    }

    @Override
    public void deleteRealty(int id){
        realtyDAO.removeRealty(id);
        System.out.println("Объект недвижимости удален!");
    }

    public void printTable(String[] columnNames, Object[][] data){
        TextTable tt = new TextTable(columnNames, data);
        tt.setSort(0);
        tt.printTable();
    }
}

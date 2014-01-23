package com.nerj.oop.realty.service;

import com.nerj.oop.realty.dao.RealtyDAO;
import com.nerj.oop.realty.dao.RealtyDAOImpl;
import com.nerj.oop.realty.exception.EmptyResultException;
import com.nerj.oop.realty.exception.EmptyStringException;
import com.nerj.oop.realty.exception.IncorrectChoiceException;
import com.nerj.oop.realty.exception.IncorrectDateFormatException;
import com.nerj.oop.realty.model.CommercialRealty;
import com.nerj.oop.realty.model.PrivateSectorRealty;
import com.nerj.oop.realty.model.Realty;
import com.nerj.oop.realty.model.ResidentialRealty;
import dnl.utils.text.table.TextTable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RealtyServiceImpl implements RealtyService {
    private RealtyDAO realtyDAO;

    public RealtyServiceImpl(){
        realtyDAO = new RealtyDAOImpl();
    }

    @Override
    public void showRealty() throws EmptyResultException, EmptyStringException, IncorrectChoiceException {
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
        if (!residentialRealties.isEmpty()){
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
                deleteRealty(id);
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
        } else if (choice.charAt(0) == 'e'){
            System.out.print("Введите ID объекта недвижимости: ");
            String subChoice = System.console().readLine();
            if (subChoice == null || choice.equals(""))
                throw new EmptyStringException();
            try {
                Integer id = Integer.parseInt(subChoice);
                editRealty(id);
            } catch (NumberFormatException e){
                e.printStackTrace();
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
        System.out.print("Общая площадь: ");
        realty.setArea(Double.parseDouble(System.console().readLine()));
        System.out.print("Количество комнат/помещений: ");
        realty.setNumberOfRooms(Integer.parseInt(System.console().readLine()));
        while (true){
            System.out.print("Тип (r - квартира, p - частаная, c - нежилая): ");
            String choice = System.console().readLine();
            if (choice == null || choice.isEmpty())
                System.out.println("Выберите тип!");
            else if (choice.charAt(0) == 'r'){
                realty.setType("residential");
                ResidentialRealty residentialRealty = new ResidentialRealty(realty);
                System.out.print("Жилая площадь: ");
                residentialRealty.setResidentialArea(Double.parseDouble(System.console().readLine()));
//                while (true){
                    System.out.print("Площадь кухни: ");
//                    try {
                        residentialRealty.setKitchenArea(Double.parseDouble(System.console().readLine()));
//                        break;
//                    } catch (IncorrectDateFormatException e){
//                        e.printStackTrace();
//                    }
//                }
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
//                while (true){
                    System.out.print("Площадь прилегающей территории: ");
//                    try {
                        privateSectorRealty.setNeighborhoodArea(Double.parseDouble(System.console().readLine()));
//                        break;
//                    } catch (IncorrectDateFormatException e){
//                        e.printStackTrace();
//                    }
//                }
                System.out.print("Количество этажей: ");
                privateSectorRealty.setNumberOfStoreys(Integer.parseInt(System.console().readLine()));
                System.out.print("Количество санузлов: ");
                privateSectorRealty.setNumberOfWC(Integer.parseInt(System.console().readLine()));

                realtyDAO.addPrivateSectorRealty(privateSectorRealty);
                break;
            } else if (choice.charAt(0) == 'c'){
                realty.setType("commercial");
                CommercialRealty commercialRealty = new CommercialRealty(realty);
//                while (true){
                    System.out.print("Этаж: ");
//                    try {
                        commercialRealty.setStorey(Integer.parseInt(System.console().readLine()));
//                        break;
//                    } catch (IncorrectDateFormatException e){
//                        e.printStackTrace();
//                    }
//                }
                System.out.print("Тип помещения: ");
                commercialRealty.setSubtype(System.console().readLine());

                realtyDAO.addCommercialRealty(commercialRealty);
                break;
            } else System.out.println("Выберите тип!");
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
            System.out.print("Общая площадь [" + residentialRealty.getArea() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                residentialRealty.setArea(in);
            while (true){
                System.out.print("Количество комнат [" + residentialRealty.getNumberOfRooms() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" "))
                        residentialRealty.setNumberOfRooms(parseDate(in));
                    break;
                } catch (IncorrectDateFormatException e){
                    e.printStackTrace();
                }
            }
            System.out.print("Жилая площадь [" + residentialRealty.getResidentialArea() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                residentialRealty.setResidentialArea(in);
            System.out.print("Площадь кухни [" + residentialRealty.getKitchenArea() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                residentialRealty.setKitchenArea(in);
            System.out.print("Тип санузла [" + residentialRealty.getTypeWC() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                residentialRealty.setTypeWC(in);
            System.out.print("Тип квартиры [" + residentialRealty.getSubtype() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                residentialRealty.setSubtype(in);

            realtyDAO.updateResidentialRealty(residentialRealty);
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
            System.out.print("Общая площадь [" + privateSectorRealty.getArea() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                privateSectorRealty.setArea(in);
            while (true){
                System.out.print("Количество комнат [" + privateSectorRealty.getNumberOfRooms() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" "))
                        privateSectorRealty.setNumberOfRooms(parseDate(in));
                    break;
                } catch (IncorrectDateFormatException e){
                    e.printStackTrace();
                }
            }
            System.out.print("Жилая площадь [" + privateSectorRealty.getResidentialArea() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                privateSectorRealty.setResidentialArea(in);
            System.out.print("Площадь прилегающей территории [" + privateSectorRealty.getNeighborhoodArea() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                privateSectorRealty.setNeighborhoodArea(in);
            System.out.print("Количество этажей [" + privateSectorRealty.getNumberOfStoreys() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                privateSectorRealty.setNumberOfStoreys(in);
            System.out.print("Количество санузлов [" + privateSectorRealty.getNumberOfWC() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                privateSectorRealty.setNumberOfWC(in);

            realtyDAO.updatePrivateSectorRealty(privateSectorRealty);
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
            System.out.print("Площадь [" + commercialRealty.getArea() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                commercialRealty.setArea(in);
            while (true){
                System.out.print("Количество помещений [" + commercialRealty.getNumberOfRooms() + "]: ");
                in = System.console().readLine();
                try {
                    if (in != null && !in.isEmpty() && !in.equals(" "))
                        commercialRealty.setNumberOfRooms(parseDate(in));
                    break;
                } catch (IncorrectDateFormatException e){
                    e.printStackTrace();
                }
            }
            System.out.print("Этаж [" + commercialRealty.getStorey() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                commercialRealty.setStorey(in);
            System.out.print("Тип помещения [" + commercialRealty.getSubtype() + "]: ");
            in = System.console().readLine();
            if (in != null && !in.isEmpty() && !in.equals(" "))
                commercialRealty.setSubtype(in);

            realtyDAO.updateCommercialRealty(commercialRealty);
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

    public Date parseDate(String dateString) throws IncorrectDateFormatException{
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = format.parse(dateString);
            return date;
        } catch (ParseException e){
            throw new IncorrectDateFormatException();
        }
    }
}

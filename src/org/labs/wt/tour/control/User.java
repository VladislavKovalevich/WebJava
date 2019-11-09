package org.labs.wt.tour.control;

import org.labs.wt.tour.model.Country;
import org.labs.wt.tour.model.Hotel;
import org.labs.wt.tour.model.Region;
import org.labs.wt.tour.model.Tour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class User implements IUserFunc {
    String name;
    boolean isAgent;


    public User(String name, boolean isAgent){
        this.isAgent = isAgent;
        this.name = name;
    }

    public List<Tour> GetToursList(){

        List<Tour> tourList = new LinkedList<>();

        try
        {
            LineNumberReader in = new LineNumberReader(new BufferedReader(new FileReader("D:\\Учёба\\Лабораторные\\Семестр 5\\BT\\WebJava\\datafiles\\Tours.txt")));
            System.out.println("file is opened");

            for(String str = in.readLine(); (str != null) && (str.trim().length() != 0); str = in.readLine()) {
                String[] strings = str.split("\\|");
                for (int i = 0; i < strings.length; i++) {

                    Hotel hotel = new Hotel(strings[4], new Region(new Country(strings[0]), strings[1]));
                    Date date = new SimpleDateFormat("dd.MM.yy").parse(strings[2]);

                    Integer countDays = Integer.parseInt(strings[3]);
                    Integer countPerson = Integer.parseInt(strings[5]);
                    Integer isBookt = Integer.parseInt(strings[6]);

                    Tour tour = new Tour(hotel, date, countDays, countPerson, isBookt);

                    tourList.add(tour);
                }
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }

        return tourList;
    }

    @Override
    public void deleteTour(Tour tour) {

    }

    @Override
    public Tour searchTour() {
        return null;
    }

    @Override
    public void bookTour(Tour tour) {

    }

    @Override
    public Tour modifyTour(Tour tour) {
        return null;
    }

    @Override
    public Tour createTour() {
        return null;
    }
}

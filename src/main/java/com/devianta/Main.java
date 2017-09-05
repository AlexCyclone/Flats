package com.devianta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            FlatsDAO fdao = new FlatsDAO();
            fdao.initDB();
            List<Flat> flats = new ArrayList<>();

            setDefaultData(fdao);
            System.out.println("All data:");
            printFlats(fdao.getFlats());

            System.out.println("Where id from 2 to 4:");
            printFlats(fdao.getFlatById(2, 4));

            System.out.println("Where address contain \"Odessa\":");
            printFlats(fdao.getFlatByAddress("Odessa"));

            System.out.println("Where area from 50 to 100:");
            printFlats(fdao.getFlatByArea(50., 100.));

            System.out.println("Where rooms count 2:");
            printFlats(fdao.getFlatByRooms(1));

            System.out.println("Where price from 200000 to 400000");
            printFlats(fdao.getFlatByPrice(200000., 400000.));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setDefaultData(FlatsDAO fdao) throws SQLException {
        fdao.addFlat(new Flat("Kiev, Khreschatik, 1", 124.4, 3, 999999.99));
        fdao.addFlat(new Flat("Kiev, Borichiv Tik, 38", 79.1, 2, 399999.99));
        fdao.addFlat(new Flat("Kiev, Borschagivska, 175", 43.7, 1, 199999.99));
        fdao.addFlat(new Flat("Kiev, Aviatoriv, 41", 92.6, 2, 439999.99));
        fdao.addFlat(new Flat("Odessa, Leontovicha, 4", 64.9, 1, 249999.99));
    }

    private static void printFlats(List<Flat> flats) {
        System.out.println(Flat.getFormatString());
        for (Flat flat : flats) {
            System.out.println(flat);
        }
        System.out.println();
    }

}

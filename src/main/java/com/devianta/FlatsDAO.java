package com.devianta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlatsDAO {
    private final String DB_CONNECTION = "jdbc:mysql://localhost:3306/flats";
    private final String DB_USER = "flatuser";
    private final String DB_PASSWORD = "123456";
    private Connection conn;

    public void initDB() throws SQLException {
        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        try (Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Flats");
            st.execute("CREATE TABLE Flats (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "address VARCHAR(128) DEFAULT NULL," +
                    "area DOUBLE DEFAULT NULL," +
                    "rooms INT DEFAULT NULL," +
                    "price DOUBLE DEFAULT NULL" +
                    ")");
        }
    }

    public void addFlat(Flat flat) throws SQLException {
        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO flats (address, area, rooms, price) VALUES(?, ?, ?, ?)")) {
            ps.setString(1, flat.getAddress());
            ps.setDouble(2, flat.getArea());
            ps.setInt(3, flat.getRooms());
            ps.setDouble(4, flat.getPrice());
            ps.executeUpdate();
        }
    }

    public void deleteFlatById(Integer id) throws SQLException {
        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM flats WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Flat> getFlats() throws SQLException {
        List<Flat> resultList;
        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        try (PreparedStatement st = conn.prepareStatement("SELECT * FROM flats")) {
            try (ResultSet rs = st.executeQuery()) {
                resultList = resultSetToFlats(rs);
            }
        }
        return resultList;
    }

    public List<Flat> getFlatById(Integer fromId, Integer toId) throws SQLException {
        List<Flat> resultList = new ArrayList<>();
        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM flats WHERE id>=? AND id<=?")) {
            ps.setInt(1, fromId);
            ps.setInt(2, toId);
            try (ResultSet rs = ps.executeQuery()) {
                resultList = resultSetToFlats(rs);
            }
        }
        return resultList;
    }

    public List<Flat> getFlatById(Integer id) throws SQLException {
        return getFlatById(id, id);
    }

    public List<Flat> getFlatByAddress(String str) throws SQLException {
        List<Flat> resultList;
        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM flats WHERE address LIKE ?")) {
            str = "%" + str + "%";
            ps.setString(1, str);
            try (ResultSet rs = ps.executeQuery()) {
                resultList = resultSetToFlats(rs);
            }
        }
        return resultList;
    }

    public List<Flat> getFlatByArea(Double fromArea, Double toArea) throws SQLException {
        List<Flat> resultList;
        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM flats WHERE area>=? AND area<=?")) {
            ps.setDouble(1, fromArea);
            ps.setDouble(2, toArea);
            try (ResultSet rs = ps.executeQuery()) {
                resultList = resultSetToFlats(rs);
            }
        }
        return resultList;
    }

    public List<Flat> getFlatByArea(Double area) throws SQLException {
        return getFlatByArea(area, area);
    }

    public List<Flat> getFlatByRooms(Integer fromRoom, Integer toRoom) throws SQLException {
        List<Flat> resultList = new ArrayList<>();
        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM flats WHERE rooms>=? AND rooms<=?")) {
            ps.setInt(1, fromRoom);
            ps.setInt(2, toRoom);
            try (ResultSet rs = ps.executeQuery()) {
                resultList = resultSetToFlats(rs);
            }
        }
        return resultList;
    }

    public List<Flat> getFlatByRooms(Integer rooms) throws SQLException {
        return getFlatByRooms(rooms, rooms);
    }

    public List<Flat> getFlatByPrice(Double fromPrice, Double toPrice) throws SQLException {
        List<Flat> resultList = new ArrayList<>();
        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM flats WHERE price>=? AND price<=?")) {
            ps.setDouble(1, fromPrice);
            ps.setDouble(2, toPrice);
            try (ResultSet rs = ps.executeQuery()) {
                resultList = resultSetToFlats(rs);
            }
        }
        return resultList;
    }

    public List<Flat> getFlatByPrice(Double price) throws SQLException {
        return getFlatByArea(price, price);
    }

    private List<Flat> resultSetToFlats(ResultSet rs) throws SQLException {
        List<Flat> resultList = new ArrayList<>();
        while (rs.next()) {
            Flat flat = new Flat();
            flat.setId(rs.getInt("id"));
            flat.setAddress(rs.getString("address"));
            flat.setArea(rs.getDouble("area"));
            flat.setRooms(rs.getInt("rooms"));
            flat.setPrice(rs.getDouble("price"));
            resultList.add(flat);
        }
        return resultList;
    }

}

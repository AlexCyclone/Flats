package com.devianta;

public class Flat {
    private Integer id;
    private String address;
    private Double area;
    private Integer rooms;
    private Double price;

    public Flat() {
    }

    public Flat(String address, Double area, Integer rooms, Double price) {
        this.address = address;
        this.area = area;
        this.rooms = rooms;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Double getArea() {
        return area;
    }

    public Integer getRooms() {
        return rooms;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static String getFormatString() {
        return String.format(" %-4s%-30s%-10s%-6s%-15s", "id", "address", "area", "rooms", "price");
    }

    @Override
    public String toString() {
        return String.format(" %-4d%-30s%-10.2f%-6d%-15.2f", id, address, area, rooms, price);
    }
}

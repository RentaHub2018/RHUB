package com.example.dell.fenny.Models;

public class HouseRegisteration {
    String area;
    String amount;
    String rooms;
    boolean tv;
    boolean fridge;
    boolean kitchen;
    boolean wifi;
    boolean machine;
    boolean ac;
    boolean furniture;
    boolean parking;
    boolean handicap;
    String phone;
    String description;

    public HouseRegisteration() {
    }

    public HouseRegisteration(String area,
                              String amount,
                              String rooms,
                              boolean tv,
                              boolean fridge,
                              boolean kitchen,
                              boolean wifi,
                              boolean machine,
                              boolean ac,
                              boolean furniture,
                              boolean parking,
                              boolean handicap,
                              String phone,
                              String description) {
        this.area = area;
        this.amount = amount;
        this.rooms = rooms;
        this.tv = tv;
        this.fridge = fridge;
        this.kitchen = kitchen;
        this.wifi = wifi;
        this.machine = machine;
        this.ac = ac;
        this.furniture = furniture;
        this.parking = parking;
        this.handicap = handicap;
        this.phone = phone;
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isFridge() {
        return fridge;
    }

    public void setFridge(boolean fridge) {
        this.fridge = fridge;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isMachine() {
        return machine;
    }

    public void setMachine(boolean machine) {
        this.machine = machine;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public boolean isFurniture() {
        return furniture;
    }

    public void setFurniture(boolean furniture) {
        this.furniture = furniture;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isHandicap() {
        return handicap;
    }

    public void setHandicap(boolean handicap) {
        this.handicap = handicap;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

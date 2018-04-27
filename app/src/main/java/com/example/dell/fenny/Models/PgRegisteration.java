package com.example.dell.fenny.Models;

public class PgRegisteration {
    boolean female;
    boolean male;
    boolean single;
    boolean two;
    boolean triple;
    boolean four;
    String amount;

    public PgRegisteration() {
    }

    public PgRegisteration(boolean female, boolean male, boolean single, boolean two, boolean triple, boolean four, String amount) {
        this.female = female;
        this.male = male;
        this.single = single;
        this.two = two;
        this.triple = triple;
        this.four = four;
        this.amount = amount;
    }

    public boolean isFemale() {
        return female;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public boolean isTwo() {
        return two;
    }

    public void setTwo(boolean two) {
        this.two = two;
    }

    public boolean isTriple() {
        return triple;
    }

    public void setTriple(boolean triple) {
        this.triple = triple;
    }

    public boolean isFour() {
        return four;
    }

    public void setFour(boolean four) {
        this.four = four;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

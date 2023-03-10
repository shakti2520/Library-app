package com.iiitnr.libraryapp;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String Title, Publisher;
    private int Total = 0, available = 0, id;
    private List<Integer> unit = new ArrayList<Integer>();


    public Book() {
    }

    public Book(String Title, String Publisher, int Total, int id) {
        this.Title = Title;
        this.Publisher = Publisher;
        this.Total = Total;
        this.id = id;
//        this.available=Total;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String Publisher) {
        this.Publisher = Publisher;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getUnit() {
        return unit;
    }

    public void setUnit(List<Integer> unit) {
        this.unit = unit;
    }
}


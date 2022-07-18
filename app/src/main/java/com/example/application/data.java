package com.example.application;

public class data {

    private String name;
    private String notes;
    private String hour;
    private String date;
    private String department;

    public data(String name, String notes, String hour, String date, String department) {
        this.name = name;
        this.notes = notes;
        this.hour = hour;
        this.date = date;
        this.department = department;
    }

    public data() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
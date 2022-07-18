package com.example.application;

public class Appointment {

    private String Department;
    private String Name;
    private String Date;
    private String Hour;
    private String Insurance;

    public Appointment() {
    }

    public Appointment(String department, String name, String date, String hour, String insurance) {
        Department = department;
        Name = name;
        Date = date;
        Hour = hour;
        Insurance = insurance;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getInsurance() {
        return Insurance;
    }

    public void setInsurance(String insurance) {
        Insurance = insurance;
    }
}

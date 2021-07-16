package com.techelevator.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    private int id;
    private int space_id;
    private int number_of_attendees;
    private String start_date;
    private String end_date;
    private String reserved_for;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getSpace_id() {
        return space_id;
    }
    public void setSpace_id(int space_id) {
        this.space_id = space_id;
    }

    public int getNumber_of_attendees() {
        return number_of_attendees;
    }
    public void setNumber_of_attendees(int number_of_attendees) {
        this.number_of_attendees = number_of_attendees;
    }

    public String getStart_date() {
        return start_date;
    }
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
    public String getEnd_date(){
        return end_date;
    }
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
    public String getReserved_for() {
        return reserved_for;
    }
    public void setReserved_for(String reserved_for){
        this.reserved_for = reserved_for;
    }

    public String toString() {
        return this.reserved_for;
    }
}

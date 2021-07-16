package com.techelevator.model;

import java.math.BigDecimal;

public class Space {

    private int id;
    private int venue_id;
    private String name;
    private String open_from;
    private String open_to;
    private Long max_occupancy;
    private Boolean is_accessible;
    private BigDecimal daily_rate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getVenue_id() {
        return venue_id;
    }
    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }

    public String getOpen_from() {
        return open_from;
    }
    public void setOpen_from(String open_from) {
        this.open_from = open_from;
    }
    public String getOpen_to(){
        return open_to;
    }
    public void setOpen_to(String open_to) {
        this.open_to = open_to;
    }
    public Long getMax_occupancy() {
        return max_occupancy;
    }
    public void setMax_occupancy(Long max_occupancy){
        this.max_occupancy = max_occupancy;
    }
    public Boolean getIs_accessible() {
        return is_accessible;
    }
    public void setIs_accessible(Boolean is_accessible){
        this.is_accessible = is_accessible;
    }
    public BigDecimal getDaily_rate() {
        return daily_rate;
    }
    public void setDaily_rate(BigDecimal daily_rate){
        this.daily_rate = daily_rate;
    }

    public String toString() {
        return this.name;
    }

}

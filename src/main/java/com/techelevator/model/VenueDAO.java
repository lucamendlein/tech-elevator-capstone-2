package com.techelevator.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public interface VenueDAO {

    /**
     * Get all venues from the database.
     *
     * @return all venues as Venue objects in a List
     */
    List<Venue> getAllVenues();

    void create (Venue venue);
}

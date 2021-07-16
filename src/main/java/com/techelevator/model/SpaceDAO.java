package com.techelevator.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface SpaceDAO {

    /**
     * Get all spaces from the database.
     * @return all spaces as Space objects in a List
     */
    List<Space> getAllSpaces();


    /**
     * Get all spaces for specific venue id
     * @return venue spaces as Space objects in a List
     */
    List<Space> getSpacesByVenue(int venue_id);

    List<Space> getAvailableSpacesByDateIdAndOccupancy(LocalDate start_date, LocalDate end_Date, int peopleAttending, int venue_id);
}

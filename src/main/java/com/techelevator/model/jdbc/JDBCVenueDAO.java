package com.techelevator.model.jdbc;

import com.techelevator.model.Venue;
import com.techelevator.model.VenueDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCVenueDAO implements VenueDAO {

    private JdbcTemplate jdbcTemplate;

    public JDBCVenueDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Venue> getAllVenues() {

        String sql = "SELECT venue.id, venue.name AS venue_name, description, city.name AS city_name, state_abbreviation, STRING_AGG(category.name, ', ') AS categories FROM venue " +
                "JOIN city ON city.id = venue.city_id " +
                "LEFT JOIN category_venue ON venue.id = category_venue.venue_id " +
                "LEFT JOIN category ON category_venue.category_id = category.id " +
                "GROUP BY venue.id, city.name, state_abbreviation ORDER BY venue.name";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        List<Venue> venues = new ArrayList<Venue>();
        while(rows.next()) {
            Venue venue = mapRowToVenue( rows );
            venues.add( venue );
        }
        return venues;
    }

    @Override
    public void create(Venue venue) {
            String sql = "INSERT INTO venue (id, name, city_id, description) " +
        "VALUES (?, ?, ?, ?) RETURNING id";
         SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, venue.getId(), venue.getName(), venue.getCity_id(), venue.getDescription());
          rows.next();
           venue.setId( rows.getInt("id") );
        }

  //  }


    private Venue mapRowToVenue (SqlRowSet row) {
        Venue venue = new Venue();

        venue.setId(row.getInt("id"));
        venue.setName(row.getString("venue_name"));
        venue.setDescription(row.getString("description"));
        venue.setCity_name(row.getString("city_name"));
        venue.setState_abbreviation(row.getString("state_abbreviation"));

        if(row.getString("categories") == null) {
            venue.setCategories("NA");
        } else {
            venue.setCategories(row.getString("categories"));
        }

        return venue;
    }

}

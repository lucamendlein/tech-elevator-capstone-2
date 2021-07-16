package com.techelevator.model.jdbc;

import com.techelevator.model.Reservation;
import com.techelevator.model.ReservationDAO;
import com.techelevator.model.Space;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCReservationDAO implements ReservationDAO {

    private JdbcTemplate jdbcTemplate;

    public JDBCReservationDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public void create(Reservation reservation) {

            String sql = "INSERT INTO reservation (space_id, number_of_attendees, start_date, end_date, reserved_for) " +
        "VALUES (?, ?, ?, ?, ?) RETURNING reservation_id";
            SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, reservation.getId(), reservation.getSpace_id(), reservation.getStart_date(),
            reservation.getEnd_date(), reservation.getNumber_of_attendees(), reservation.getReserved_for());
            rows.next();
            reservation.setId( rows.getInt("id") );


    }

    private Space mapRowToSpaces (SqlRowSet row) {
        Space space = new Space();

        space.setId(row.getInt("id"));
        space.setVenue_id(row.getInt("venue_id"));
        space.setName(row.getString("name"));
        space.setIs_accessible(row.getBoolean("is_accessible"));
        space.setOpen_to(row.getString("open_to"));
        space.setDaily_rate(row.getBigDecimal("daily_rate"));
        space.setMax_occupancy(row.getLong("max_occupancy"));

        if(row.getString("open_from") == null) {
            space.setOpen_from("NA");
        } else {
            space.setOpen_from(row.getString("open_from"));
        }

        if(row.getString("open_to") == null) {
            space.setOpen_to("NA");
        } else {
            space.setOpen_to(row.getString("open_to"));
        }

        return space;
    }
}

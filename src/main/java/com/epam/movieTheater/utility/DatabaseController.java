package com.epam.movieTheater.utility;

import com.epam.movieTheater.entity.Auditorium;
import com.epam.movieTheater.entity.Event;
import com.epam.movieTheater.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@PropertySource("${DB_PROPS:classpath:db.properties}")
public class DatabaseController {

    private static final String SQL_ERROR_STATE_SCHEMA_EXISTS = "X0Y68";
    private static final String SQL_ERROR_STATE_TABLE_EXISTS = "X0Y32";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public Environment environment;

    @PostConstruct
    private void init() {
        checkIfSchemaExists();
    }

    private void checkIfSchemaExists() {
        try {
            jdbcTemplate.update("CREATE SCHEMA " + environment.getRequiredProperty("jdbc.schema"));
        } catch (DataAccessException e) {
            Throwable causeException = e.getCause();
            if (causeException instanceof SQLException) {
                SQLException sqlException = (SQLException) causeException;
                if (sqlException.getSQLState().equals(SQL_ERROR_STATE_SCHEMA_EXISTS)) {
                    System.out.println("Scheme already exists");
                } else {
                    throw e;
                }
            } else {
                throw e;
            }
        }
    }

    public void checkIfTableExists(String tableSqlQuery, String tableName) {
        try {
            jdbcTemplate.update("CREATE TABLE " + tableSqlQuery);
        } catch (DataAccessException e) {
            Throwable causeException = e.getCause();
            if (causeException instanceof SQLException) {
                SQLException sqlException = (SQLException) causeException;
                if (sqlException.getSQLState().equals(SQL_ERROR_STATE_TABLE_EXISTS)) {
                    System.out.println("Table " + tableName + " already exists");
                } else {
                    throw e;
                }
            } else {
                throw e;
            }
        }
    }

    public List<User> getUsersTable() {
        List<User> list = jdbcTemplate.query("SELECT * FROM T_USERS", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Integer id = resultSet.getInt("id");
                String userName = resultSet.getString("USERNAME");
                String userEmail = resultSet.getString("USEREMAIL");
                String userBirthday = resultSet.getString("USERBIRTHDAY");
                String userOrderHistory = resultSet.getString("USERORDERHISTORY");
                return new User(id, userName, userEmail, userBirthday, userOrderHistory);
            }
        });
        return list;
    }

    public List<Auditorium> getAuditoriumsTable() {
        List<Auditorium> list = jdbcTemplate.query("SELECT * FROM T_AUDITORIUMS", new RowMapper<Auditorium>() {
            @Override
            public Auditorium mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Integer id = resultSet.getInt("id");
                String auditoriumName = resultSet.getString("auditoriumName");
                String numberOfSeats = resultSet.getString("numberOfSeats");
                String vipSeats = resultSet.getString("vipSeats");
                String priceForBasicSeat = resultSet.getString("priceForBasicSeat");
                String factorForVipSeat = resultSet.getString("factorForVipSeat");
                String factorForHighRatedEvent = resultSet.getString("factorForHighRatedEvent");
                return new Auditorium(id, auditoriumName, numberOfSeats, vipSeats, priceForBasicSeat, factorForVipSeat, factorForHighRatedEvent);
            }
        });
        return list;
    }

    public List<Event> getEventsTable() {
        List<Event> list = jdbcTemplate.query("SELECT * FROM T_EVENTS", new RowMapper<Event>() {
            @Override
            public Event mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Integer id = resultSet.getInt("id");
                String eventName = resultSet.getString("eventName");
                String eventPrice = resultSet.getString("eventPrice");
                String rating = resultSet.getString("rating");
                String eventDate = resultSet.getString("eventDate");
                return new Event(id, eventName, eventPrice, rating, eventDate);
            }
        });
        return list;
    }

    public void updateTable(String query) {
        jdbcTemplate.update(query);
    }

    public void updateTable(String query, String... values) {
        jdbcTemplate.update(query, values);
    }


    public void checkIfTicketsTableExists() {
    }
}

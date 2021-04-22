package com.epam.movieTheater.service;

import com.epam.movieTheater.entity.Auditorium;
import com.epam.movieTheater.service.impl.IAuditoriumEventService;
import com.epam.movieTheater.service.impl.IAuditoriumEventUserService;
import com.epam.movieTheater.utility.DatabaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class AuditoriumService implements IAuditoriumEventUserService, IAuditoriumEventService {

    private final String AUDITORIUMS_TABLE_SQL_QUERY = "t_auditoriums (" + "id INT NOT NULL PRIMARY KEY," +
            "auditoriumName VARCHAR(255)," +
            "numberOfSeats VARCHAR(255)," +
            "vipSeats VARCHAR(255)," +
            "priceForBasicSeat VARCHAR(255)," +
            "factorForVipSeat VARCHAR(255)," +
            "factorForHighRatedEvent VARCHAR(255)" + ")";

    private Integer auditoriumId;
    @Autowired
    private DatabaseController databaseController;

    @PostConstruct
    public void checkIfAuditoriumDatabaseExists() {
        databaseController.checkIfTableExists(AUDITORIUMS_TABLE_SQL_QUERY, "AUDITORIUMS");
    }

    @Autowired
    public AuditoriumService() {
        auditoriumId = 0;
    }

    @Override
    public Auditorium getByName(String auditoriumName) {
        List<Auditorium> list = getAll();
        for (Auditorium auditorium : list) {
            if (auditorium.getAuditoriumName().contains(auditoriumName)) {
                return auditorium;
            }
        }
        System.out.println("there is no such auditorium whit name contains:" + auditoriumName);
        return null;
    }

    @Override
    public List<Auditorium> getAll() {
        return databaseController.getAuditoriumsTable();
    }
}
package com.epam.movieTheater.service;

import com.epam.movieTheater.entity.Auditorium;
import com.epam.movieTheater.service.impl.IAuditoriumEventService;
import com.epam.movieTheater.service.impl.IAuditoriumEventUserService;
import com.epam.movieTheater.utility.CsvToBeanBuilderUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;

@Component
public class AuditoriumService implements IAuditoriumEventUserService, IAuditoriumEventService {

    private final String FILE_PATH = "src/main/resources/auditoriums/auditoriums.csv";
    private final CsvToBeanBuilderUtility csvToBeanBuilderUtility;
    private Integer auditoriumId;
    private List<Auditorium> auditoriumsList;

    @Autowired
    public AuditoriumService(CsvToBeanBuilderUtility csvToBeanBuilderUtility) {
        this.csvToBeanBuilderUtility = csvToBeanBuilderUtility;
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
        try {
            auditoriumsList = csvToBeanBuilderUtility.getListOfBeansFromCsv(FILE_PATH, Auditorium.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return auditoriumsList;
    }
}
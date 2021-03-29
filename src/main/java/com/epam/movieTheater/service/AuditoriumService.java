package com.epam.movieTheater.service;

import com.epam.movieTheater.entity.Auditorium;
import com.epam.movieTheater.service.impl.IAuditoriumEventService;
import com.epam.movieTheater.service.impl.IAuditoriumEventUserService;
import com.epam.movieTheater.utility.CsvToBeanBuilderUtility;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

@Component
public class AuditoriumService implements IAuditoriumEventUserService, IAuditoriumEventService {

    private final String FILE_PATH = "src/main/resources/auditoriums/auditoriums.csv";
    @Resource
    Scanner scanner;
    Integer auditoriumId;
    @Resource
    private CsvToBeanBuilderUtility csvToBeanBuilderUtility;
    private List<Auditorium> auditoriumsList;

    @Resource
    private Auditorium auditorium;


    public AuditoriumService() {
        auditoriumId = 0;
    }


    private Integer auditoriumIdIncrement() {
        auditoriumId++;
        return auditoriumId;
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
//        auditoriumsList.forEach(System.out::println);
        return auditoriumsList;
    }
}
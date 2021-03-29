package com.epam.movieTheater.configuration;

import com.epam.movieTheater.entity.*;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
public class ConfigService {

    @Bean
    @Scope("prototype")
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public HashMap<Integer, User> userDatabase() {
        return new HashMap<>();
    }

    @Bean
    public HashMap<Integer, Event> eventDatabase() {
        return new HashMap<>();
    }

    @Bean("userOrderHistoryMap")
    @Scope("prototype")
    public HashMap<Integer, UserOrderHistory> userOrderHistoryMap() {
        return new HashMap<>();
    }


    @Bean("userDateFormat")
    @Scope("prototype")
    public SimpleDateFormat userDateFormat() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }

    @Bean
    @Qualifier("eventDate")
    public SimpleDateFormat eventDateFormat() {
        return new SimpleDateFormat("dd.MM.yyyy/hh:mm");
    }


    @Bean
    public HashMap<Integer, Auditorium> auditoriumBase() {
        return new HashMap<>();
    }

    @Bean
    public List<EventSchedule> eventScheduleList() {
        return new ArrayList<>();
    }

    @Bean
    @Scope("prototype")
    public Properties properties() {
        return new Properties();
    }

    @Bean
    @Scope("prototype")
    public List<String> vipSeats() {
        return new ArrayList<>();
    }

    @Bean
    public AnnotationConfigApplicationContext context() {
        return new AnnotationConfigApplicationContext();
    }

    @Bean
    @Scope("prototype")
    public StringBuilder stringBuilder() {
        return new StringBuilder();
    }

    @Bean
    @Scope("prototype")
    public StringWriter stringWriter() {
        return new StringWriter();
    }

    @Bean
    @Scope("prototype")
    public ColumnPositionMappingStrategy<User> columnPositionMappingStrategy() {
        return new ColumnPositionMappingStrategy<User>();
    }

}

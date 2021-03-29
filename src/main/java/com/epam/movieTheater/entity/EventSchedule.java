package com.epam.movieTheater.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Component
@NoArgsConstructor
@ToString
public class EventSchedule {
    private Integer eventId;
    private Integer auditoriumId;
    private Date eventDate;
    private Double totalEventPrice;

    @Autowired
    @Qualifier("eventDate")
    private SimpleDateFormat simpleDateFormat;

    public EventSchedule(Integer eventId, Integer auditoriumId, Date eventDate, Double totalEventPrice) {
        this.eventId = eventId;
        this.auditoriumId = auditoriumId;
        this.eventDate = eventDate;
        this.totalEventPrice = totalEventPrice;
    }

    public EventSchedule setEventSchedule(Properties properties, Double totalEventPrice) {
        eventId = Integer.parseInt(properties.getProperty("eventId"));
        auditoriumId = Integer.parseInt(properties.getProperty("auditoriumId"));
        try {
            eventDate = simpleDateFormat.parse(properties.getProperty("eventDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.totalEventPrice = totalEventPrice;
        return new EventSchedule(eventId, auditoriumId, eventDate, totalEventPrice);
    }
}

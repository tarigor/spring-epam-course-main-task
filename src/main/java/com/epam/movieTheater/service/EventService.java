package com.epam.movieTheater.service;

import com.epam.movieTheater.entity.Event;
import com.epam.movieTheater.service.impl.IAuditoriumEventService;
import com.epam.movieTheater.service.impl.IEventService;
import com.epam.movieTheater.service.impl.IEventUserService;
import com.epam.movieTheater.utility.BeanToCsvBuilderUtility;
import com.epam.movieTheater.utility.CsvToBeanBuilderUtility;
import com.epam.movieTheater.utility.DatabaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class EventService implements IEventUserService, IAuditoriumEventService, IEventService {
    private final String EVENTS_TABLE_SQL_QUERY = "t_events (" + "id INT NOT NULL PRIMARY KEY," +
            "eventName VARCHAR(255)," +
            "eventPrice VARCHAR(255)," +
            "rating VARCHAR(255)," +
            "eventDate VARCHAR(255)" + ")";
    private final String SQL_QUERY_INSERT_TO_EVENTS_TABLE = "INSERT INTO t_events (id, eventName, eventPrice, rating, eventDate) VALUES (?,?,?,?,?)";

    @Autowired
    private Scanner scanner;
    private final Integer eventId;
    @Autowired
    private Event event;
    @Autowired
    private DatabaseController databaseController;

    public EventService() {
        eventId = 0;
    }

    @PostConstruct
    private void checkIfEventsTableExists() {
        databaseController.checkIfTableExists(EVENTS_TABLE_SQL_QUERY, "EVENTS");
    }

    @Override
    public Event getByName(String eventName) {
        List<Event> list = getAll();
        for (Event event : list) {
            if (event.getEventName().contains(eventName)) {
                return event;
            }
        }
        System.out.println("there is no such event whit name contains:" + eventName);
        return null;
    }

    @Override
    public void getForDateRange(Date from, Date to) {

    }

    @Override
    public void getNextEvents(Date to) {

    }

    public String getEventPrice(Event event){
        return event.getEventPrice();
    }

    @Override
    public void save() {
        System.out.println("Input a name of event:");
        String inputName = scanner.nextLine();
        System.out.println("Input a price of event:");
        String inputPrice = scanner.nextLine();
        System.out.println("Input a rating of event(HIGH,MEDIUM,LOW):");
        String inputRating = scanner.nextLine();
        System.out.println("Input a event date (format DD.MM.YYYY/HH:MM):");
        String inputEventDay = scanner.nextLine();
        event = event.setEvent(toIncrementEventId(), inputName, inputPrice, inputRating, inputEventDay);

        databaseController.updateTable(SQL_QUERY_INSERT_TO_EVENTS_TABLE, String.valueOf(toIncrementEventId()), inputName, inputPrice, inputRating, inputEventDay);

        List<Event> list = getAll();
        System.out.println("Saved to DB event with id " + list.get(list.size() - 1).getEventId());
    }

    @Override
    public void remove() {

    }

    @Override
    public Event getById(Integer id) {
        List<Event> list = getAll();
        for (Event event : list) {
            if (event.getEventId().equals(id)) {
                return event;
            }
        }
        System.out.println("there is no such event with id:" + id);
        return null;
    }

    @Override
    public List<Event> getAll() {
        return databaseController.getEventsTable();
    }

    private Integer toIncrementEventId() {
        List<Event> list = getAll();
        if (list.size() == 0) {
            return 0;
        }
        return list.get(list.size() - 1).getEventId() + 1;
    }
}
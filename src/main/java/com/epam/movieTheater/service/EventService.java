package com.epam.movieTheater.service;

import com.epam.movieTheater.entity.Event;
import com.epam.movieTheater.service.impl.IAuditoriumEventService;
import com.epam.movieTheater.service.impl.IEventService;
import com.epam.movieTheater.service.impl.IEventUserService;
import com.epam.movieTheater.utility.BeanToCsvBuilderUtility;
import com.epam.movieTheater.utility.CsvToBeanBuilderUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class EventService implements IEventUserService, IAuditoriumEventService, IEventService {

    private final String FILE_PATH = "src/main/resources/events/events.csv";
    @Autowired
    private Scanner scanner;
    @Autowired
    private CsvToBeanBuilderUtility csvToBeanBuilderUtility;
    @Autowired
    private BeanToCsvBuilderUtility beanToCsvBuilderUtility;
    private final Integer eventId;
    private List<Event> eventsList;
    @Autowired
    private Event event;

    public EventService() {
        eventId = 0;
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

        System.out.println(event.toString());

        beanToCsvBuilderUtility.writeListToCsv(FILE_PATH, event, Event.class,true);
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
        try {
            eventsList = csvToBeanBuilderUtility.getListOfBeansFromCsv(FILE_PATH, Event.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        eventsList.forEach(System.out::println);
        return eventsList;
    }

    private Integer toIncrementEventId() {
        List<Event> list = getAll();
        if (list.size() == 1) {
            return 0;
        }
        return list.get(list.size() - 1).getEventId() + 1;
    }
}
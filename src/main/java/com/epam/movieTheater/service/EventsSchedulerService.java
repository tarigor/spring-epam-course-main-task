package com.epam.movieTheater.service;

import com.epam.movieTheater.entity.EventSchedule;
import com.epam.movieTheater.entity.PropertiesFilesInputStream;
import com.epam.movieTheater.service.impl.IEventsScheduler;
import com.epam.movieTheater.utility.PropertyFilesScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Component
public class EventsSchedulerService implements IEventsScheduler {

    @Resource
    EventSchedule eventSchedule;
    private List<String> propertiesFiles;
    @Resource
    private PropertyFilesScanner propertyFilesScanner;
    @Resource
    private Properties properties;
    @Resource
    private PropertiesFilesInputStream propertiesFilesInputStream;
    @Autowired
    private List<EventSchedule> eventScheduleList;

    public EventsSchedulerService() {
    }

    @Override
    public void appointEvent() {

    }

    @PostConstruct
    public void toCreateUsersArray() {
        propertiesFiles = propertyFilesScanner.getAllPropertiesFiles(".\\src\\main\\resources\\eventsSchedule");
        for (int i = 0; i < propertiesFiles.size(); i++) {
            try {
                properties.load(propertiesFilesInputStream.getPath(".\\src\\main\\resources\\eventsSchedule\\%s", propertiesFiles, i));
                eventScheduleList.add(eventSchedule.setEventSchedule(properties, getTotalPriceOfEvent()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getAll() {
        for (EventSchedule eventSchedule : eventScheduleList) {
            System.out.println(eventSchedule.toString());
        }
    }

    public Double getTotalPriceOfEvent() {
        return 1.1;
    }
}

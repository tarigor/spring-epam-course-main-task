package com.epam.movieTheater.entity;

import com.opencsv.bean.CsvBindByPosition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Qualifier("eventt")
@Scope("prototype")
public class Event {

    @CsvBindByPosition(position = 0)
    private Integer eventId;
    @CsvBindByPosition(position = 1)
    private String eventName;
    @CsvBindByPosition(position = 2)
    private String eventPrice;
    @CsvBindByPosition(position = 3)
    private String rating;
    @CsvBindByPosition(position = 4)
    private String eventDate;

    public Event() {
    }

    public Event(Integer eventId, String eventName, String eventPrice, String rating, String eventDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventPrice = eventPrice;
        this.rating = rating;
        this.eventDate = eventDate;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public Event setEvent(Integer eventId, String eventName, String eventPrice, String rating, String eventDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventPrice = eventPrice;
        this.rating = rating;
        this.eventDate = eventDate;
        return new Event(eventId, eventName, eventPrice, rating, eventDate);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventPrice=" + eventPrice +
                ", rating='" + rating + '\'' +
                ", eventDate='" + eventDate + '\'' +
                '}';
    }
}
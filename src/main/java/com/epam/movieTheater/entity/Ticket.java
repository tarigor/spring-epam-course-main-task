package com.epam.movieTheater.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Scope("prototype")
public class Ticket {
    private User user;
    private Event event;
    private Integer seat;
    private LocalDate dateTime;

    public Ticket(User user, Event event, Integer seat, LocalDate dateTime) {
        this.event = event;
        this.dateTime = dateTime;
        this.seat = seat;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

/*
    bookTicket(tickets)
    Ticket should contain information about event, air dateTime, seat, and user.
    The user could be registered or not. If user is registered,
    then booking information is stored for that user (in the tickets collection).
    Purchased tickets for particular event should be stored.
*/
}

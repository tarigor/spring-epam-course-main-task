package com.epam.movieTheater.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Scope("prototype")
public class UserOrderHistory {
    private Integer eventId;
    private Integer ticketsAmount;
    private LocalDate orderDate;

    public UserOrderHistory() {
    }

    public UserOrderHistory(Integer eventId, Integer ticketsAmount, LocalDate orderDate) {
        this.eventId = eventId;
        this.ticketsAmount = ticketsAmount;
        this.orderDate = orderDate;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getTicketsAmount() {
        return ticketsAmount;
    }

    public void setTicketsAmount(Integer ticketsAmount) {
        this.ticketsAmount = ticketsAmount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public UserOrderHistory setUserOrderHistory(Integer eventId, Integer ticketsAmount, LocalDate orderDate) {
        this.eventId = eventId;
        this.ticketsAmount = ticketsAmount;
        this.orderDate = orderDate;
        return new UserOrderHistory(eventId, ticketsAmount, orderDate);
    }


    @Override
    public String toString() {
        return "UserOrderHistory{" +
                "eventId=" + eventId +
                ", ticketsAmount=" + ticketsAmount +
                ", orderDate=" + orderDate +
                '}';
    }
}

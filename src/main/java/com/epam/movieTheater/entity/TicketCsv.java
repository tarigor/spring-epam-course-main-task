package com.epam.movieTheater.entity;

import com.opencsv.bean.CsvBindByPosition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TicketCsv {

    @CsvBindByPosition(position = 0)
    private Integer userId;
    @CsvBindByPosition(position = 1)
    private Integer eventId;
    @CsvBindByPosition(position = 2)
    private String orderDate;
    @CsvBindByPosition(position = 3)
    private Integer seat;

    public TicketCsv() {
    }

    public TicketCsv(Integer userId, Integer eventId, String orderDate, Integer seat) {
        this.userId = userId;
        this.eventId = eventId;
        this.orderDate = orderDate;
        this.seat = seat;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public TicketCsv setTicketCsv(Integer userId, Integer eventId, String orderDate, Integer seat) {
        this.userId = userId;
        this.eventId = eventId;
        this.orderDate = orderDate;
        this.seat = seat;
        return new TicketCsv(userId, eventId, orderDate, seat);
    }

    @Override
    public String toString() {
        return userId + "," + eventId + "," + orderDate + "," + seat;
    }
}

package com.epam.movieTheater.discount;

import com.epam.movieTheater.entity.Event;
import com.epam.movieTheater.entity.User;
import com.epam.movieTheater.entity.UserOrderHistory;
import com.epam.movieTheater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
@Scope("prototype")
public class DiscountStrategy {

//    private User user;
    private Event event;
    private Date dateTime;
    private Integer numberOfTickets;
    private Integer j;
    @Autowired
    private UserService userService;

    public DiscountStrategy() {
        j = 1;
    }

    public void getDiscount(User user, Event event, Date dateTime, Integer numberOfTickets) {
    }

    public Double checkBirthdayDiscount(Integer userId, LocalDate dateTime) {
        if (LocalDate.parse(userService.getById(userId).getBirthday(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).isAfter(dateTime.minusDays(1)) &&
                LocalDate.parse(userService.getById(userId).getBirthday(), DateTimeFormatter.ofPattern("dd.MM.yyyy")).isBefore(dateTime.plusDays(6))) {
            return 5.0;
        }
        return 0.0;
    }

    public Double checkEvery10TicketsDiscount(Integer userId) {
        Integer totalOrders = 0;

        for (UserOrderHistory userOrderHistory : userService.getById(userId).getUserOrderHistory(userService.getById(userId))) {
            totalOrders = userOrderHistory.getTicketsAmount() + totalOrders;
        }

        System.out.println("Total of orders:" + (totalOrders + j));
        if ((totalOrders + j) % 10 == 0) {
            System.out.println("This is a TENTH order");
            j = j + 1;
            return 50.0;
        } else {
            j = j + 1;
            return 0.0;
        }
    }
}
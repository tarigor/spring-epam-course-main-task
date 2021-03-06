package com.epam.movieTheater.service;

import com.epam.movieTheater.discount.DiscountStrategy;
import com.epam.movieTheater.entity.User;
import com.epam.movieTheater.utility.ServiceUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DiscountService {

    @Autowired
    DiscountStrategy discountStrategy;

    public DiscountService() {
    }

    public Double getDiscountValue(Integer userId, LocalDate dateTime) {
        Double birthDayDiscount = discountStrategy.checkBirthdayDiscount(userId, dateTime);
        Double every10TicketsDiscount = discountStrategy.checkEvery10TicketsDiscount(userId);
        ServiceUtility.outputMessageToConsole("Birthday discount: ", birthDayDiscount);
        ServiceUtility.outputMessageToConsole("Every 10 tickets discount: ", every10TicketsDiscount);

        return Math.max(birthDayDiscount, every10TicketsDiscount);
    }

}
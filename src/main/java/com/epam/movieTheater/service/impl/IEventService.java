package com.epam.movieTheater.service.impl;

import java.util.Date;

public interface IEventService {

    void getForDateRange(Date from, Date to);

    void getNextEvents(Date to);

}
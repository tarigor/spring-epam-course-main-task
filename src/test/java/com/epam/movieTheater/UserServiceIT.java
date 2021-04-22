package com.epam.movieTheater;

import com.epam.movieTheater.entity.TicketCsv;
import com.epam.movieTheater.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceIT {

    @Autowired
    UserService userService;

    @Test
    public void updateUserOrderHistoryTest(){
//        Integer userId, Integer eventId, String orderDate, Integer seat
        userService.updateUserHistory(new TicketCsv(1,1,"test",1));
    }
}

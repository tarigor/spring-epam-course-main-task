package com.epam.movieTheater;

import com.epam.movieTheater.aspects.CounterAspect;
import com.epam.movieTheater.aspects.DiscountAspect;
import com.epam.movieTheater.entity.TicketCsv;
import com.epam.movieTheater.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class AppTest {

    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;
    @Autowired
    AuditoriumService auditoriumService;
    @Autowired
    EventsSchedulerService eventsSchedulerService;
    @Autowired
    BookingService bookingService;
    @Autowired
    CounterAspect counterAspect;
    @Autowired
    DiscountAspect discountAspect;

    @Test
    public void test() {
//        eventService.getAll();
//        System.out.println("---------------------------");
//        userService.getAll();
//        System.out.println("---------------------------");
//        auditoriumService.getAll();
//        System.out.println("---------------------------");
//        eventsSchedulerService.getAll();

//        System.out.println("event name: " + eventService.getByName("i"));
//        System.out.println("event name: " + eventService.getByName("a"));
//        System.out.println("event name: " + eventService.getByName("k"));

//        System.out.println("auditorium name:" + auditoriumService.getAuditoriumBase().get(1).getAuditoriumName());
//        System.out.println("User Name: " + userService.getById(1).getName());
//        bookingService.getTicketsPrice(
//                eventService.getById(1),
//                auditoriumService.getAll().get(1),
//                LocalDate.parse("10.01.1980", DateTimeFormatter.ofPattern("dd.MM.yyyy")),
//                1,
//                new Integer[]{1, 5, 6, 10});
//
//        bookingService.getTicketsPrice(
//                eventService.getById(3),
//                auditoriumService.getAll().get(3),
//                LocalDate.parse("10.01.1980", DateTimeFormatter.ofPattern("dd.MM.yyyy")),
//                3,
//                new Integer[]{1, 2, 8, 13});
//        System.out.println(userService.getById(1).getName());
//        bookingService.bookTicket(new TicketCsv().setTicketCsv(3, 2, "06.04.2021", 2));
//        bookingService.bookTicket(new TicketCsv().setTicketCsv(1, 3, "08.04.2021", 2));
//        userService.save();
//        userService.getAll().forEach(System.out::println);
//        System.out.println(userService.getById(3).toString());
//        System.out.println(userService.getUserByEmail("egor").toString());
//        System.out.println("result: " + userService.getById(1).toString());

//        counterAspect.outputCounter(counterAspect, counterAspect.getCounterGetByNameOfEvent(), "Statistic of getByName of Event method calls:");
//        counterAspect.outputCounter(counterAspect, counterAspect.getCounterGetEventPrice(), "Statistic of getEventPrice of Event method calls:");
//        counterAspect.outputCounter(counterAspect, counterAspect.getCounterBookTicket(), "Statistic of booked tickets:");
//
//        discountAspect.outputCounter(discountAspect, discountAspect.getBirthdayDiscountMap(), "Statistic of provided birthday's discount:");
        auditoriumService.getAll().forEach(System.out::println);
    }

}

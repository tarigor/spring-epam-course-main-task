package com.epam.movieTheater;

import com.epam.movieTheater.entity.Auditorium;
import com.epam.movieTheater.entity.Event;
import com.epam.movieTheater.entity.Rating;
import com.epam.movieTheater.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class BookingServiceIT {
//    getTicketsPrice(Event event, Auditorium auditorium, LocalDate dateTime, User user, Integer[] seats)
//    Event(Integer eventId, String eventName, String eventPrice, String rating, String eventDate)
//    Auditorium(Integer auditoriumId, String auditoriumName, String numberOfSeats, String vipSeats, String priceForBasicSeat, String factorForVipSeat, String factorForHighRatedEvent)
//    User(Integer id, String name, String email, String birthday, String orderHistory)
    @Test
    public void testGetTicketPrices() {
        Event event = new Event(1,"movie1","12.2", "HIGH","12.01.2021/13:00");
        Auditorium auditorium = new Auditorium(1,"Vostok","45","1,2,3","23.3","1.2","1.2");
        LocalDate localDate = LocalDate.parse("12.12.1986", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        User user = new User(1,"User1","user@mail.com","12.03.2000","");
        Integer[] seats = {1,2,4};
    }
}

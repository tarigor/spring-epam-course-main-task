package com.epam.movieTheater.shell;

import com.epam.movieTheater.entity.Event;
import com.epam.movieTheater.entity.User;
import com.epam.movieTheater.service.AuditoriumService;
import com.epam.movieTheater.service.BookingService;
import com.epam.movieTheater.service.EventService;
import com.epam.movieTheater.service.UserService;
import com.epam.movieTheater.utility.ConsoleService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ShellComponent
public class ShellCommand {

    private final UserService userService;
    private final EventService eventService;
    private final AuditoriumService auditoriumService;
    private final BookingService bookingService;
    private final ConsoleService consoleService;

    private String connectionStatus = "guest>";

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public ShellCommand(UserService userService, EventService eventService, AuditoriumService auditoriumService, BookingService bookingService, ConsoleService consoleService) {
        this.userService = userService;
        this.eventService = eventService;
        this.auditoriumService = auditoriumService;
        this.bookingService = bookingService;
        this.consoleService = consoleService;
    }

    @ShellMethod("Create a new User")
    public void createNewUser() {
        userService.save();
    }

    @ShellMethod("Remove an User")
    public void removeUser() {
        userService.remove();
    }

    @ShellMethod("Get User by ID")
    public void getUserById(Integer id) {
        System.out.println(userService.getById(id).toString());
    }

    @ShellMethod("Get User by Email")
    public void getUserByEmail(String emailName) {
        System.out.println(userService.getUserByEmail(emailName).toString());
    }

    @ShellMethod("Get all Users")
    public void getAllUser() {
        userService.getAll().forEach(System.out::println);
    }

    @ShellMethod("Create a new Event")
    public void createNewEvent() {
        eventService.save();
    }

    Availability createNewEventAvailability() {
        return connectionStatus.contentEquals("admin") ? Availability.available() : Availability.unavailable("admin rights is required");
    }

    @ShellMethod("Remove an event")
    public void remove() {
        eventService.remove();
    }

    @ShellMethod("Get Event by ID")
    public void getEventById(Integer id) {
        System.out.println(eventService.getById(id).toString());
    }

    @ShellMethod("Get Event by name")
    public void getEventByName(String name) {
        System.out.println(eventService.getByName(name).toString());
    }

    @ShellMethod("Get all event")
    public void getAllEvent() {
        eventService.getAll().forEach(System.out::println);
    }

    @ShellMethod("Get all Auditoriums")
    public void getAllAuditoriums() {
        auditoriumService.getAll().forEach(System.out::println);
    }

    @ShellMethod("Get Auditorium by name")
    public void getAuditoriumByName(String name) {
        System.out.println(auditoriumService.getByName(name).toString());
    }

    @ShellMethod("Get Ticket price")
    public void getTicketPrice(Integer eventID, String auditoriumName, String ddMMyy, Integer userID, Integer... ticketNumbers) {
        bookingService.getTicketsPrice(
                eventService.getById(eventID),
                auditoriumService.getByName(auditoriumName),
                LocalDate.parse(ddMMyy, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                userID,
                ticketNumbers
        );
    }

    @ShellMethod("Get purchased tickets")
    public void getPurchasedTickets(Event event, Date dateTime){

    }

    Availability getPurchasedTicketsAvailability() {
        return connectionStatus.contentEquals("admin") ? Availability.available() : Availability.unavailable("admin rights is required");
    }



    @ShellMethod("User sign-in")
    public void userSignIn(String userEmail, String password) {
        Object connectionResult = userService.connect(userEmail, password);
        if (!connectionResult.equals(false)) {
            consoleService.write("connected %s.", ((User) connectionResult).getName());
            connectionStatus = ((User) connectionResult).getName();
        } else {
            connectionStatus = "guest";
        }
    }

    @ShellMethod("sign-off")
    public void signOff() {
        connectionStatus = "guest";
    }
}

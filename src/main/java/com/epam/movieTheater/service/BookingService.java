package com.epam.movieTheater.service;

import com.epam.movieTheater.entity.Auditorium;
import com.epam.movieTheater.entity.Event;
import com.epam.movieTheater.entity.PropertiesFilesInputStream;
import com.epam.movieTheater.entity.TicketCsv;
import com.epam.movieTheater.utility.BeanToCsvBuilderUtility;
import com.epam.movieTheater.utility.DatabaseController;
import com.epam.movieTheater.utility.ServiceUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

@Component
public class BookingService {

    private final String TICKETS_TABLE_SQL_QUERY = "t_tickets (" +
            "userId INT," +
            "eventId INT," +
            "orderDate VARCHAR(255)," +
            "seat INT" + ")";
    private final String INSERT_TICKETS_TABLE_SQL_QUERY = "INSERT INTO T_TICKETS (USERID, EVENTID, ORDERDATE, SEAT) VALUES (?,?,?,?)";

    @Autowired
    DiscountService discountService;
    @Autowired
    PropertiesFilesInputStream propertiesFilesInputStream;
    @Autowired
    Properties properties;
    @Autowired
    UserService userService;
    @Autowired
    TicketCsv ticketCsv;
    @Autowired
    EventService eventService;
    @Autowired
    private DatabaseController databaseController;

    private Integer fileIndex;

    public BookingService() {
        fileIndex = 0;
    }

    @PostConstruct
    private void checkIfTicketsTableExists() {
        databaseController.checkIfTableExists(TICKETS_TABLE_SQL_QUERY, "TICKETS");
    }

    public void getTicketsPrice(Event event, Auditorium auditorium, LocalDate dateTime, Integer userId, Integer[] seats) {
        Double totalPriceOfAllTickets = 0.0;
        Double discount;
        Double basePriceForEvent;
        Double factorPriceDependsOnRating;
        Boolean isVip;
        Double vipFactor;
        Double totalTicketPriceWithoutDiscount;
        Double totalTicketPriceWithDiscount;
        try {
            int i = 1;
            for (Integer seat : seats) {
                System.out.println("___________________________________");
                properties.load(propertiesFilesInputStream.getPath("src/main/resources/service/ratingFactor.properties"));

                discount = discountService.getDiscountValue(userId, dateTime);
                ServiceUtility.outputMessageToConsole("Total Discount For Order: ", discount);

                basePriceForEvent = Double.valueOf(eventService.getEventPrice(event));
                ServiceUtility.outputMessageToConsole("Base price for event per single ticket: ", basePriceForEvent);
                factorPriceDependsOnRating = Double.valueOf(properties.getProperty(event.getRating()));
                ServiceUtility.outputMessageToConsole("event factor price: ", factorPriceDependsOnRating);

                isVip = checkIfSeatIsVip(seat, auditorium);
                ServiceUtility.outputMessageToConsole("if the seat is VIP: ", isVip);
                if (isVip) {
                    vipFactor = 2.0;
                } else {
                    vipFactor = 1.0;
                }
                totalTicketPriceWithoutDiscount = basePriceForEvent * factorPriceDependsOnRating * vipFactor;
                totalTicketPriceWithDiscount = totalTicketPriceWithoutDiscount - totalTicketPriceWithoutDiscount * (discount / 100);
                ServiceUtility.outputMessageToConsole("price for single ticket WITHOUT discount: ", totalTicketPriceWithoutDiscount);
                ServiceUtility.outputMessageToConsole("price for single ticket WITH discount: ", totalTicketPriceWithDiscount);
                System.out.println("___________________________________");
                totalPriceOfAllTickets = totalPriceOfAllTickets + totalTicketPriceWithDiscount;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.format("Total price of all tickets: %.2f" + "\n", totalPriceOfAllTickets);
    }

//    public String getEventPrices(Event event){
//        System.out.println("I'm here!!!!!");
//        return event.getEventPrice();
//    }

    private Boolean checkIfSeatIsVip(Integer seat, Auditorium auditorium) {
        ServiceUtility.outputMessageToConsole("seat number:", seat);
        for (String vipSeat : auditorium.getVipSeats().split(",")) {
            if (seat == Integer.parseInt(vipSeat)) {
                return true;
            }
        }
        return false;
    }

    public void bookTicket(TicketCsv ticketCsv) {
        databaseController.updateTable(INSERT_TICKETS_TABLE_SQL_QUERY,
                String.valueOf(ticketCsv.getUserId()),
                String.valueOf(ticketCsv.getEventId()),
                ticketCsv.getOrderDate(),
                String.valueOf(ticketCsv.getSeat()));
        userService.updateUserHistory(ticketCsv);
    }

    public Integer fileIdIncrement() {
        return fileIndex++;
    }

    public void getPurchasedTicketsForEvent(Event event, Date dateTime) {

    }

}
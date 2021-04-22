package com.epam.movieTheater.aspects;

import com.epam.movieTheater.service.UserService;
import com.epam.movieTheater.utility.BeanToCsvBuilderUtility;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Aspect
@Component
public class LuckyWinnerAspect {
    //    LuckyWinnerAspect
//    every time the bookTicket method is executed perform the checkLucky
//    method for the user that based on some randomness will return
//    true or false. If user is lucky, the ticketPrice changes to zero
//    and ticket is booked, thus user pays nothing. Store the information
//    about this lucky event into the user object (like some system messages or so)—OPTIONAL.
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private BeanToCsvBuilderUtility beanToCsvBuilderUtility;

//    @Pointcut("execution(* com.epam.movieTheater.service.BookingService.bookTicket(..)) && args(ticketCsv)")
//    public void gettingLuckyBookTicket(TicketCsv ticketCsv) {
//    }

//    @Around("execution(* com.epam.movieTheater.service.BookingService.bookTicket(..))")
//    public void getLuckyBookTicket(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        if(new Random().nextBoolean()) {
//            System.out.println("Congrats Bro! You lucky today! Get your free ticket!");
//        }else {
//            proceedingJoinPoint.proceed();
//        }
//    }
}
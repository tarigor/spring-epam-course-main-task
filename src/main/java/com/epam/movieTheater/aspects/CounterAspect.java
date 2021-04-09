package com.epam.movieTheater.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CounterAspect extends CommonAspect{

    private Map<String, Integer> counterGetByNameOfEvent = new HashMap<>();
    private Map<String, Integer> counterGetEventPrice = new HashMap<>();
    private Map<String, Integer> counterBookTicket = new HashMap<>();

    @Pointcut("execution(* *..getByName(..))")
    public void counterOfGetByNameMethodCalls() {
    }

    @Pointcut("execution(* *..getEventPrice(..))")
    public void counterOfGetEventPrice() {
    }

    @Pointcut("execution(* *..bookTicket(..))")
    public void counterOfBookTickets() {
    }

    @AfterReturning("counterOfGetByNameMethodCalls()")
    public void countOfGetByNameOfEvent() {
        String name = "Method: getByName";
        if (!counterGetByNameOfEvent.containsKey(name)) {
            counterGetByNameOfEvent.put(name, 0);
        }
        counterGetByNameOfEvent.put(name, counterGetByNameOfEvent.get(name) + 1);
    }

    @AfterReturning("counterOfGetEventPrice()")
    public void countOfGetPriceOfEvent() {
        String name = "Method: getEventPrice:";
        if (!counterGetEventPrice.containsKey(name)) {
            counterGetEventPrice.put(name, 0);
        }
        counterGetEventPrice.put(name, counterGetEventPrice.get(name) + 1);
    }

    @AfterReturning("counterOfBookTickets()")
    public void countOfBookTickets() {
        String name = "Method: bookTickets:";
        if (!counterBookTicket.containsKey(name)) {
            counterBookTicket.put(name, 0);
        }
        counterBookTicket.put(name, counterBookTicket.get(name) + 1);
    }

    public Map<String, Integer> getCounterGetByNameOfEvent() {
        return Collections.unmodifiableMap(counterGetByNameOfEvent);
    }

    public Map<String, Integer> getCounterGetEventPrice() {
        return Collections.unmodifiableMap(counterGetEventPrice);
    }

    public Map<String, Integer> getCounterBookTicket() {
        return Collections.unmodifiableMap(counterBookTicket);
    }


}

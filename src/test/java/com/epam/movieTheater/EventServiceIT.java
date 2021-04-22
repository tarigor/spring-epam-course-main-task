package com.epam.movieTheater;

import com.epam.movieTheater.service.EventService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class EventServiceIT {

    @Autowired
    private EventService eventService;

    @Test
    public void getAllTest() {
        eventService.getAll().forEach(System.out::println);
    }

    @Test
    public void getByIdTest() {
        int eventId = 3;
        Assert.assertTrue(eventService.getById(eventId).getEventId().equals(eventId));
    }

    @Test
    public void getByNameTest() {
        String eventName = "King-Kong";
        Assert.assertTrue(eventService.getByName(eventName).getEventName().contains(eventName));
    }
}

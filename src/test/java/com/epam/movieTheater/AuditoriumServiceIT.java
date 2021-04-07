package com.epam.movieTheater;

import com.epam.movieTheater.service.AuditoriumService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import javax.annotation.Resource;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class AuditoriumServiceIT {

    @Resource
    private AuditoriumService auditoriumService;

    @Test
    public void testGetAuditoriumByName() {
        String testName = "Vostok";
        Assert.assertTrue(auditoriumService.getByName(testName).getAuditoriumName().contains(testName));
    }

    @Test
    public void testGetAllAuditoriums(){
    }
}

package com.epam.movieTheater.utility;

import com.epam.movieTheater.entity.User;
import com.epam.movieTheater.shell.ShellCommand;
import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class ConnectedPromptProvider implements PromptProvider {

    private final ShellCommand shellCommand;

    private User user;

    public ConnectedPromptProvider(ShellCommand shellCommand) {
        this.shellCommand = shellCommand;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public AttributedString getPrompt() {
        String msg = shellCommand.getConnectionStatus() + ">";
        return new AttributedString(msg);
    }
}

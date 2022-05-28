package com.example.geektrust.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {
    private CommandInvoker() {
    }

    static CommandInvoker commandInvoker = new CommandInvoker();

    public static CommandInvoker getInstanceOfCommandInvoker() {
        return commandInvoker;
    }

    private static final Map<String, ISubscriptionCommand> commandMap = new HashMap<>();

    // Register the command into the HashMap
    public void register(String commandName, ISubscriptionCommand command) {
        commandMap.put(commandName, command);
    }

    private ISubscriptionCommand get(String commandName) {
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, List<String> tokens) {
        ISubscriptionCommand command = get(commandName);
        command.execute(tokens);
    }

}

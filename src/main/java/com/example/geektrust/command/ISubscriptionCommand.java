package com.example.geektrust.command;

import java.util.List;

public interface ISubscriptionCommand {
    void execute(List<String> commandParts);
}

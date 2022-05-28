package com.example.geektrust.command;

import com.example.geektrust.service.SubscriptionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CreateSubscriptionCommand implements ISubscriptionCommand {
    private final SubscriptionService subscriptionService;

    private static final int DATE_INDEX = 1;

    public CreateSubscriptionCommand(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(List<String> commandParts) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(commandParts.get(DATE_INDEX), formatter);
            subscriptionService.createSubscription(date);
        } catch (Exception e) {
            if (e instanceof DateTimeParseException)
                System.out.println("INVALID_DATE");
            else {
                System.out.println(e.getMessage());
            }
        }
    }
}

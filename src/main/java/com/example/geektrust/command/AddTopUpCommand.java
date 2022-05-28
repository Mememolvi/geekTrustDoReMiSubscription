package com.example.geektrust.command;

import com.example.geektrust.service.SubscriptionService;

import java.util.List;

public class AddTopUpCommand implements ISubscriptionCommand {
    private final SubscriptionService subscriptionService;
    private static final int TOPUP_PLAN_INDEX = 1;
    private static final int NO_OF_MONTH_INDEX = 2;

    public AddTopUpCommand(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(List<String> commandParts) {
        try {
            subscriptionService.addTopUpPlan(commandParts.get(TOPUP_PLAN_INDEX), Integer.parseInt(commandParts.get(NO_OF_MONTH_INDEX)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

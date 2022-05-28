package com.example.geektrust.command;

import com.example.geektrust.service.SubscriptionService;

import java.util.List;

public class AddActivePlanCommand implements ISubscriptionCommand {

    private final SubscriptionService subscriptionService;
    private static final int PLAN_TYPE_INDEX = 1;
    private static final int SUBSCRIPTION_TYPE_INDEX = 2;

    public AddActivePlanCommand(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(List<String> commandParts) {
        try {
            subscriptionService.addActivePlan(commandParts.get(PLAN_TYPE_INDEX), commandParts.get(SUBSCRIPTION_TYPE_INDEX));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

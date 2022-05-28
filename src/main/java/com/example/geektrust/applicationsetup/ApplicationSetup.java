package com.example.geektrust.applicationsetup;

import com.example.geektrust.command.*;
import com.example.geektrust.entity.Subscription;
import com.example.geektrust.service.SubscriptionService;

public class ApplicationSetup {
    static ApplicationSetup applicationSetup = new ApplicationSetup();

    private ApplicationSetup() {

    }

    public static ApplicationSetup getInstanceOfApplicationSetup() {
        return applicationSetup;
    }

    private Subscription subscription = new Subscription();
    private final SubscriptionService subscriptionService = new SubscriptionService(subscription);
    private final CreateSubscriptionCommand createSubscriptionCommand = new CreateSubscriptionCommand(subscriptionService);
    private final AddActivePlanCommand addActivePlanCommand = new AddActivePlanCommand(subscriptionService);
    private final AddTopUpCommand addTopUpCommand = new AddTopUpCommand(subscriptionService);
    private final PrintRenewalDatesCommand printRenewalDatesCommand = new PrintRenewalDatesCommand(subscriptionService);
    private final CommandInvoker commandInvoker = CommandInvoker.getInstanceOfCommandInvoker();

    public CommandInvoker getCommandInvoker() {
        commandInvoker.register("START_SUBSCRIPTION", createSubscriptionCommand);
        commandInvoker.register("ADD_SUBSCRIPTION", addActivePlanCommand);
        commandInvoker.register("ADD_TOPUP", addTopUpCommand);
        commandInvoker.register("PRINT_RENEWAL_DETAILS", printRenewalDatesCommand);
        return commandInvoker;
    }
}

package com.example.geektrust.command;

import com.example.geektrust.dto.RenewalDetailsDTO;
import com.example.geektrust.service.SubscriptionService;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PrintRenewalDatesCommand implements ISubscriptionCommand {
    private final SubscriptionService subscriptionService;

    public PrintRenewalDatesCommand(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(List<String> commandParts) {
        try {
            List<RenewalDetailsDTO> renewalDateStrings = subscriptionService.printRenewalDetails();
            Integer renewalAmount = subscriptionService.getPlanValue();
            for (RenewalDetailsDTO renewalDetailsDTO : renewalDateStrings) {
                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String dateString = renewalDetailsDTO.getDate().format(formatters);
                System.out.println("RENEWAL_REMINDER " + renewalDetailsDTO.getPlanType() + " " + dateString);
            }
            System.out.println("RENEWAL_AMOUNT " + renewalAmount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

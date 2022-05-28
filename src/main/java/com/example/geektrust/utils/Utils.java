package com.example.geektrust.utils;

import com.example.geektrust.dto.RenewalDetailsDTO;
import com.example.geektrust.entity.ActivePlan;
import com.example.geektrust.enums.SubscriptionType;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static final int FREE_PLAN_PRICE_PER_MONTH = 0;
    public static final int PERSONAL_MUSIC_PLAN_PRICE = 100;
    public static final int PREMIUM_MUSIC_PLAN_PRICE = 250;

    public static final int PERSONAL_VIDEO_PLAN_PRICE = 200;
    public static final int PREMIUM_VIDEO_PLAN_PRICE = 500;

    public static final int PERSONAL_PODCAST_PLAN_PRICE = 100;
    public static final int PREMIUM_PODCAST_PLAN_PRICE = 300;

    public static final int FREE_AND_PERSONAL_PLAN_VALIDITY_IN_MONTHS = 1;
    public static final int PREMIUM_PLAN_VALIDITY_IN_MONTHS = 3;

    public static final int NO_DAYS_BEFORE_RENEWAL = 10;

    public static List<RenewalDetailsDTO> generateRenewalReminderDate(LocalDate subscriptionDate, List<ActivePlan> activePlans) {
        return activePlans.stream().map(obj -> getRenewalDetailsDTO(subscriptionDate, obj)).collect(Collectors.toList());
    }

    public static RenewalDetailsDTO getRenewalDetailsDTO(LocalDate subscriptionDate, ActivePlan obj) {
        return new RenewalDetailsDTO(generateRenewalReminderDate(subscriptionDate, obj), obj.getPlanType());
    }

    public static LocalDate generateRenewalReminderDate(LocalDate subscriptionDate, ActivePlan obj) {
        return subscriptionDate.plusMonths(getActivePlanValidityInMonths(obj)).minusDays(NO_DAYS_BEFORE_RENEWAL);
    }

    public static int getActivePlanValidityInMonths(ActivePlan activePlan) {
        SubscriptionType subscriptionType = activePlan.getSubscriptionType();
        if (subscriptionType.equals(SubscriptionType.FREE) || subscriptionType.equals(SubscriptionType.PERSONAL)) {
            return FREE_AND_PERSONAL_PLAN_VALIDITY_IN_MONTHS;
        }
        return PREMIUM_PLAN_VALIDITY_IN_MONTHS;
    }
}

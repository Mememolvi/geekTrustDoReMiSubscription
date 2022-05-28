package com.example.geektrust.service;

import com.example.geektrust.dto.RenewalDetailsDTO;
import com.example.geektrust.entity.ActivePlan;
import com.example.geektrust.entity.Subscription;
import com.example.geektrust.enums.PlanType;
import com.example.geektrust.enums.SubscriptionType;
import com.example.geektrust.enums.TopUpType;
import com.example.geektrust.exception.StreamingServiceException;

import java.time.LocalDate;
import java.util.List;

import static com.example.geektrust.utils.Utils.generateRenewalReminderDate;

public class SubscriptionService {
    private Subscription subscription;

    public SubscriptionService(Subscription subscription) {
        this.subscription = subscription;
    }

    public void createSubscription(LocalDate date) {
        this.subscription.setDateOfSubscription(date);
    }

    public void addActivePlan(String planType, String subscriptionType) throws StreamingServiceException {
        if (this.subscription.getDateOfSubscription() == null) {
            throw new StreamingServiceException("ADD_SUBSCRIPTION_FAILED INVALID_DATE");
        }
        PlanType planTypeEnum = PlanType.valueOf(planType);
        SubscriptionType subscriptionTypeEnum = SubscriptionType.valueOf(subscriptionType);
        ActivePlan activePlan = new ActivePlan(planTypeEnum, subscriptionTypeEnum);
        boolean isDuplicatePlan = checkDuplicatePlan(this.subscription.getActivePlans(), planTypeEnum);
        if (isDuplicatePlan) {
            throw new StreamingServiceException("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
        }
        subscription.addActivePlan(activePlan);
    }

    private boolean checkDuplicatePlan(List<ActivePlan> activePlans, PlanType planTypeEnum) {
        if (activePlans == null) return false;
        return activePlans.stream().allMatch(obj -> obj.getPlanType().equals(planTypeEnum));
    }

    public void addTopUpPlan(String topUpType, int noOfMonths) throws StreamingServiceException {
        if (this.subscription.getDateOfSubscription() == null) {
            throw new StreamingServiceException("ADD_TOPUP_FAILED INVALID_DATE");
        }
        TopUpType topUpTypeEnum = TopUpType.valueOf(topUpType);
        if (this.subscription.getTopUpType() != null && this.subscription.getTopUpType().equals(topUpTypeEnum)) {
            throw new StreamingServiceException("ADD_TOPUP_FAILED DUPLICATE_TOPUP");
        }
        this.subscription.setTopUpType(topUpTypeEnum);
        this.subscription.setTopUpValidity(noOfMonths);
    }

    public List<RenewalDetailsDTO> printRenewalDetails() throws StreamingServiceException {
        if (this.subscription.getDateOfSubscription() == null) {
            throw new StreamingServiceException("SUBSCRIPTIONS_NOT_FOUND");
        }
        List<ActivePlan> activePlanList = subscription.getActivePlans();
        return generateRenewalReminderDate(this.subscription.getDateOfSubscription(), activePlanList);
    }

    public Integer getPlanValue() {
        List<ActivePlan> activePlanList = subscription.getActivePlans();
        Integer totalCost = 0;
        for (ActivePlan activePlan : activePlanList) {
            totalCost += activePlan.getValueForPlan();
        }
        totalCost += subscription.getTopUpType() != null ? subscription.getTopUpCost() * subscription.getTopUpValidity() : 0;
        return totalCost;
    }
}

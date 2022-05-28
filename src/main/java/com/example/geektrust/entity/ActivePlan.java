package com.example.geektrust.entity;

import com.example.geektrust.enums.PlanType;
import com.example.geektrust.enums.SubscriptionType;
import lombok.Getter;

import java.util.Objects;

import static com.example.geektrust.utils.Utils.*;

public class ActivePlan {
    @Getter
    private PlanType planType;
    @Getter
    private SubscriptionType subscriptionType;

    public ActivePlan(PlanType planType, SubscriptionType subscriptionType) {
        this.planType = planType;
        this.subscriptionType = subscriptionType;
    }

    public Integer getValueForPlan() {
        int totalAmount = 0;
        if (this.planType.equals(PlanType.MUSIC)) {
            if (this.subscriptionType.equals(SubscriptionType.FREE)) {
                totalAmount = FREE_PLAN_PRICE_PER_MONTH;
            } else if (this.subscriptionType.equals(SubscriptionType.PERSONAL)) {
                totalAmount = PERSONAL_MUSIC_PLAN_PRICE;
            } else if (this.subscriptionType.equals(SubscriptionType.PREMIUM)) {
                totalAmount = PREMIUM_MUSIC_PLAN_PRICE;
            }
        } else if (this.planType.equals(PlanType.VIDEO)) {
            if (this.subscriptionType.equals(SubscriptionType.FREE)) {
                totalAmount = FREE_PLAN_PRICE_PER_MONTH;
            } else if (this.subscriptionType.equals(SubscriptionType.PERSONAL)) {
                totalAmount = PERSONAL_VIDEO_PLAN_PRICE;
            } else if (this.subscriptionType.equals(SubscriptionType.PREMIUM)) {
                totalAmount = PREMIUM_VIDEO_PLAN_PRICE;
            }
        } else if (this.planType.equals(PlanType.PODCAST)) {
            if (this.subscriptionType.equals(SubscriptionType.FREE)) {
                totalAmount = FREE_PLAN_PRICE_PER_MONTH;
            } else if (this.subscriptionType.equals(SubscriptionType.PERSONAL)) {
                totalAmount = PERSONAL_PODCAST_PLAN_PRICE;
            } else if (this.subscriptionType.equals(SubscriptionType.PREMIUM)) {
                totalAmount = PREMIUM_PODCAST_PLAN_PRICE;
            }
        }
        return totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivePlan that = (ActivePlan) o;
        return planType == that.planType && subscriptionType == that.subscriptionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(planType, subscriptionType);
    }
}

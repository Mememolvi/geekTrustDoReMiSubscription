package com.example.geektrust.entity;

import com.example.geektrust.enums.TopUpType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subscription {
    @Getter
    @Setter
    private LocalDate dateOfSubscription;
    @Getter
    private List<ActivePlan> activePlans;
    @Setter
    @Getter
    private TopUpType topUpType;
    @Setter
    @Getter
    private Integer topUpValidity;

    public Subscription() {
    }

    public Subscription(LocalDate dateOfSubscription, List<ActivePlan> activePlans, TopUpType topUpType, Integer topUpValidity) {
        this.dateOfSubscription = dateOfSubscription;
        this.activePlans = activePlans;
        this.topUpType = topUpType;
        this.topUpValidity = topUpValidity;
    }

    public int getTopUpCost() {
        return this.topUpType.getTopUpCost();
    }

    public void addActivePlan(ActivePlan activePlan) {
        if (this.activePlans == null) {
            this.activePlans = new ArrayList<>();
            this.activePlans.add(activePlan);
        } else {
            this.activePlans.add(activePlan);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(dateOfSubscription, that.dateOfSubscription) && Objects.equals(activePlans, that.activePlans) && topUpType == that.topUpType && Objects.equals(topUpValidity, that.topUpValidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfSubscription, activePlans, topUpType, topUpValidity);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "dateOfSubscription=" + dateOfSubscription +
                ", subDefinition=" + activePlans +
                ", topUpType=" + topUpType +
                ", topUpValidity=" + topUpValidity +
                '}';
    }
}

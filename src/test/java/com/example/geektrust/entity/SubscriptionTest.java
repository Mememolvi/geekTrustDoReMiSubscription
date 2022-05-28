package com.example.geektrust.entity;

import com.example.geektrust.enums.PlanType;
import com.example.geektrust.enums.SubscriptionType;
import com.example.geektrust.enums.TopUpType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SubscriptionTest {

    @ParameterizedTest
    @MethodSource("subscriptionSource")
    public void shouldGetTopUpCostTest(Subscription subscription, int expectedOutput) {
        Integer actualOutput = subscription.getTopUpCost();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldAddActivePlanWhenNonePresentTest() {
        Subscription subscription = new Subscription(LocalDate.now(), null, TopUpType.FOUR_DEVICE, 3);
        ActivePlan activePlan = new ActivePlan(PlanType.MUSIC, SubscriptionType.FREE);
        List<ActivePlan> activePlansExpected = new ArrayList<>();
        activePlansExpected.add(activePlan);
        subscription.addActivePlan(activePlan);
        List<ActivePlan> actualPlans = subscription.getActivePlans();
        Assertions.assertEquals(activePlansExpected, actualPlans);

    }

    private static Stream<Arguments> subscriptionSource() {
        return Stream.of(
                Arguments.of(new Subscription(LocalDate.now(), generateActivePlanList(), TopUpType.FOUR_DEVICE, 3), 50),
                Arguments.of(new Subscription(LocalDate.now(), generateActivePlanList(), TopUpType.TEN_DEVICE, 2), 100)
        );
    }

    private static List<ActivePlan> generateActivePlanList() {
        List<ActivePlan> activePlanList = new ArrayList<>();
        activePlanList.add(new ActivePlan(PlanType.MUSIC, SubscriptionType.FREE));
        activePlanList.add(new ActivePlan(PlanType.MUSIC, SubscriptionType.PERSONAL));
        activePlanList.add(new ActivePlan(PlanType.MUSIC, SubscriptionType.PREMIUM));
        activePlanList.add(new ActivePlan(PlanType.VIDEO, SubscriptionType.FREE));
        activePlanList.add(new ActivePlan(PlanType.VIDEO, SubscriptionType.PERSONAL));
        activePlanList.add(new ActivePlan(PlanType.VIDEO, SubscriptionType.PREMIUM));
        activePlanList.add(new ActivePlan(PlanType.PODCAST, SubscriptionType.FREE));
        activePlanList.add(new ActivePlan(PlanType.PODCAST, SubscriptionType.PERSONAL));
        activePlanList.add(new ActivePlan(PlanType.PODCAST, SubscriptionType.PREMIUM));
        return activePlanList;
    }
}

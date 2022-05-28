package com.example.geektrust.Utility;

import com.example.geektrust.dto.RenewalDetailsDTO;
import com.example.geektrust.entity.ActivePlan;
import com.example.geektrust.enums.PlanType;
import com.example.geektrust.enums.SubscriptionType;
import com.example.geektrust.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UtilsTest {
    @Test
    public void shouldGenerateRenewalReminderDateForActivePlansTest() {
        List<RenewalDetailsDTO> renewalDetailsDTOSExpected = generateRenewalDetailsDTOList();
        LocalDate date = LocalDate.of(2022, 01, 20);
        List<RenewalDetailsDTO> renewalDetailsDTOSActual = Utils.generateRenewalReminderDate(date, generateActivePlanList());
        Assertions.assertEquals(renewalDetailsDTOSExpected, renewalDetailsDTOSActual);
    }

    @ParameterizedTest
    @MethodSource("activePlansSource")
    public void ShouldGetActivePlanValidityInMonthsTest(ActivePlan activePlan, int validityExpected) {
        int actualValidity = Utils.getActivePlanValidityInMonths(activePlan);
        Assertions.assertEquals(validityExpected, actualValidity);
    }

    private static Stream<Arguments> activePlansSource() {
        return Stream.of(
                Arguments.of(new ActivePlan(PlanType.MUSIC, SubscriptionType.FREE), 1),
                Arguments.of(new ActivePlan(PlanType.MUSIC, SubscriptionType.PERSONAL), 1),
                Arguments.of(new ActivePlan(PlanType.MUSIC, SubscriptionType.PREMIUM), 3),
                Arguments.of(new ActivePlan(PlanType.VIDEO, SubscriptionType.FREE), 1),
                Arguments.of(new ActivePlan(PlanType.VIDEO, SubscriptionType.PERSONAL), 1),
                Arguments.of(new ActivePlan(PlanType.VIDEO, SubscriptionType.PREMIUM), 3),
                Arguments.of(new ActivePlan(PlanType.PODCAST, SubscriptionType.FREE), 1),
                Arguments.of(new ActivePlan(PlanType.PODCAST, SubscriptionType.PERSONAL), 1),
                Arguments.of(new ActivePlan(PlanType.PODCAST, SubscriptionType.PREMIUM), 3)
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

    private static List<RenewalDetailsDTO> generateRenewalDetailsDTOList() {
        List<RenewalDetailsDTO> renewalDetailsDTOS = new ArrayList<>();
        renewalDetailsDTOS.add(new RenewalDetailsDTO(LocalDate.of(2022, 02, 10), PlanType.MUSIC));
        renewalDetailsDTOS.add(new RenewalDetailsDTO(LocalDate.of(2022, 02, 10), PlanType.MUSIC));
        renewalDetailsDTOS.add(new RenewalDetailsDTO(LocalDate.of(2022, 04, 10), PlanType.MUSIC));
        renewalDetailsDTOS.add(new RenewalDetailsDTO(LocalDate.of(2022, 02, 10), PlanType.VIDEO));
        renewalDetailsDTOS.add(new RenewalDetailsDTO(LocalDate.of(2022, 02, 10), PlanType.VIDEO));
        renewalDetailsDTOS.add(new RenewalDetailsDTO(LocalDate.of(2022, 04, 10), PlanType.VIDEO));
        renewalDetailsDTOS.add(new RenewalDetailsDTO(LocalDate.of(2022, 02, 10), PlanType.PODCAST));
        renewalDetailsDTOS.add(new RenewalDetailsDTO(LocalDate.of(2022, 02, 10), PlanType.PODCAST));
        renewalDetailsDTOS.add(new RenewalDetailsDTO(LocalDate.of(2022, 04, 10), PlanType.PODCAST));

        return renewalDetailsDTOS;
    }
}

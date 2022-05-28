package com.example.geektrust.service;


import com.example.geektrust.dto.RenewalDetailsDTO;
import com.example.geektrust.entity.ActivePlan;
import com.example.geektrust.entity.Subscription;
import com.example.geektrust.enums.PlanType;
import com.example.geektrust.enums.SubscriptionType;
import com.example.geektrust.enums.TopUpType;
import com.example.geektrust.exception.StreamingServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("SubscriptionServiceTest")
@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTest {
    @Mock
    private Subscription subscriptionMock;

    @InjectMocks
    private SubscriptionService subscriptionService;

    @Test
    public void shouldThrowErrorWhenSubscriptionNotCreatedOnAddingNewActivePlanTest() {
        when(subscriptionMock.getDateOfSubscription()).thenReturn(null);
        Assertions.assertThrows(StreamingServiceException.class, () -> subscriptionService.addActivePlan(PlanType.PODCAST.toString(), SubscriptionType.PREMIUM.toString()));
    }

    @Test
    public void shouldThrowErrorOnAddingDuplicatePlan() throws StreamingServiceException {
        List<ActivePlan> activePlans = new ArrayList<>();
        activePlans.add(new ActivePlan(PlanType.MUSIC, SubscriptionType.FREE));
        when(subscriptionMock.getDateOfSubscription()).thenReturn(LocalDate.now());
        when(subscriptionMock.getActivePlans()).thenReturn(activePlans);
        Assertions.assertThrows(StreamingServiceException.class, () -> subscriptionService.addActivePlan(PlanType.MUSIC.toString(), SubscriptionType.FREE.toString()));
    }

    @Test
    public void shouldThrowErrorOnAddingTopUpPlanWhenNoSubscriptionFoundTest() {
        when(subscriptionMock.getDateOfSubscription()).thenReturn(null);
        Assertions.assertThrows(StreamingServiceException.class, () -> subscriptionService.addTopUpPlan(String.valueOf(TopUpType.TEN_DEVICE), 1));
    }

    @Test
    public void shouldThrowErrorOnAddingDuplicateTopUpPlanTest() {
        when(subscriptionMock.getDateOfSubscription()).thenReturn(LocalDate.now());
        when(subscriptionMock.getTopUpType()).thenReturn(TopUpType.TEN_DEVICE);
        Assertions.assertThrows(StreamingServiceException.class, () -> subscriptionService.addTopUpPlan(String.valueOf(TopUpType.TEN_DEVICE), 1));
    }

    @Test
    public void shouldAddTopUpPlan() throws StreamingServiceException {
        when(subscriptionMock.getDateOfSubscription()).thenReturn(LocalDate.now());
        when(subscriptionMock.getTopUpType()).thenReturn(null);
        subscriptionService.addTopUpPlan(String.valueOf(TopUpType.TEN_DEVICE), 1);
        verify(subscriptionMock, times(1)).setTopUpType(TopUpType.TEN_DEVICE);
        verify(subscriptionMock, times(1)).setTopUpValidity(1);

    }

    @Test
    public void shouldThrowErrorOnPrintRenewalDetailsTest() {
        when(subscriptionMock.getDateOfSubscription()).thenReturn(null);
        Assertions.assertThrows(StreamingServiceException.class, () -> subscriptionService.printRenewalDetails());

    }

    @Test
    public void printRenewalDetailsTest() throws StreamingServiceException {
        List<RenewalDetailsDTO> expectedRenewalDetailsDTO = new ArrayList<>();
        expectedRenewalDetailsDTO.add(new RenewalDetailsDTO(LocalDate.of(2022, 02, 05), PlanType.MUSIC));
        expectedRenewalDetailsDTO.add(new RenewalDetailsDTO(LocalDate.of(2022, 02, 05), PlanType.VIDEO));
        expectedRenewalDetailsDTO.add(new RenewalDetailsDTO(LocalDate.of(2022, 04, 05), PlanType.PODCAST));
        when(subscriptionMock.getDateOfSubscription()).thenReturn(LocalDate.of(2022, 01, 15));
        when(subscriptionMock.getActivePlans()).thenReturn(generateActivePlanList());
        List<RenewalDetailsDTO> renewalDetailsDTOSActual = subscriptionService.printRenewalDetails();
        Assertions.assertEquals(expectedRenewalDetailsDTO, renewalDetailsDTOSActual);

    }

    @Test
    public void createSubscriptionTest() {
        LocalDate date = LocalDate.now();
        subscriptionService.createSubscription(date);
        verify(subscriptionMock, times(1)).setDateOfSubscription(date);
    }

    @Test
    public void getPlanValueTest() {
        Integer expected = 500;
        when(subscriptionMock.getActivePlans()).thenReturn(generateActivePlanList());
        when(subscriptionMock.getTopUpType()).thenReturn(TopUpType.TEN_DEVICE);
        when(subscriptionMock.getTopUpCost()).thenReturn(100);
        Integer actual = subscriptionService.getPlanValue();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddActivePlanTest() throws StreamingServiceException {
        List<ActivePlan> activePlans = new ArrayList<>();
        activePlans.add(new ActivePlan(PlanType.VIDEO, SubscriptionType.FREE));
        when(subscriptionMock.getDateOfSubscription()).thenReturn(LocalDate.now());
        when(subscriptionMock.getActivePlans()).thenReturn(activePlans);
        ActivePlan activePlan = new ActivePlan(PlanType.MUSIC, SubscriptionType.FREE);
        subscriptionService.addActivePlan(PlanType.MUSIC.toString(), SubscriptionType.FREE.toString());
        verify(subscriptionMock, times(1)).addActivePlan(activePlan);
    }

    private static List<ActivePlan> generateActivePlanList() {
        List<ActivePlan> activePlanList = new ArrayList<>();
        activePlanList.add(new ActivePlan(PlanType.MUSIC, SubscriptionType.FREE));
        activePlanList.add(new ActivePlan(PlanType.VIDEO, SubscriptionType.PERSONAL));
        activePlanList.add(new ActivePlan(PlanType.PODCAST, SubscriptionType.PREMIUM));
        return activePlanList;
    }


}

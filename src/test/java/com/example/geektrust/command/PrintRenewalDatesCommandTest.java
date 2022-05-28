package com.example.geektrust.command;

import com.example.geektrust.exception.StreamingServiceException;
import com.example.geektrust.service.SubscriptionService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("AddTopUpCommandTestTest")
@ExtendWith(MockitoExtension.class)
public class PrintRenewalDatesCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Mock
    private SubscriptionService subscriptionServiceMock;

    @InjectMocks
    private PrintRenewalDatesCommand printRenewalDatesCommand;

    @Test
    public void shouldPrintRenewalDetailsTest() throws StreamingServiceException {
        when(subscriptionServiceMock.getPlanValue()).thenReturn(200);
        List<String> list = new ArrayList<>();
        list.add("PRINT_RENEWAL_DETAILS");
        printRenewalDatesCommand.execute(list);
        verify(subscriptionServiceMock, times(1)).printRenewalDetails();
        Assertions.assertEquals("RENEWAL_AMOUNT 200", outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldFailToAddTopUpWhenDuplicateTest() throws StreamingServiceException {
        List<String> list = new ArrayList<>();
        String expected = "SUBSCRIPTIONS_NOT_FOUND";
        doThrow(new StreamingServiceException(expected)).when(subscriptionServiceMock).printRenewalDetails();
        list.add("PRINT_RENEWAL_DETAILS");
        printRenewalDatesCommand.execute(list);
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}

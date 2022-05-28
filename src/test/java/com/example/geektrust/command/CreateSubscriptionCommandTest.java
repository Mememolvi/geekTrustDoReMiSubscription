package com.example.geektrust.command;

import com.example.geektrust.service.SubscriptionService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("CreateSubscriptionCommandTest")
@ExtendWith(MockitoExtension.class)
public class CreateSubscriptionCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Mock
    private SubscriptionService subscriptionServiceMock;

    @InjectMocks
    private CreateSubscriptionCommand createSubscriptionCommand;

    @Test
    public void shouldCreateSubscriptionTest() {
        LocalDate date = LocalDate.of(2022, 10, 20);
        List<String> list = new ArrayList<>();
        list.add("START_SUBSCRIPTION");
        list.add("20-10-2022");
        createSubscriptionCommand.execute(list);
        verify(subscriptionServiceMock, times(1)).createSubscription(date);
    }

    @Test
    public void shouldPrintErrorWhenDateIsInvalidTest() {
        String expected = "INVALID_DATE";
        List<String> list = new ArrayList<>();
        list.add("START_SUBSCRIPTION");
        list.add("20-30-2022");
        createSubscriptionCommand.execute(list);
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}

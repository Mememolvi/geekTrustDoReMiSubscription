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
public class AddTopUpCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Mock
    private SubscriptionService subscriptionServiceMock;

    @InjectMocks
    private AddTopUpCommand addTopUpCommand;

    @Test
    public void shouldAddToTopUpTest() throws StreamingServiceException {
        List<String> list = new ArrayList<>();
        list.add("ADD_TOPUP");
        list.add("TEN_DEVICE");
        list.add("1");
        addTopUpCommand.execute(list);
        verify(subscriptionServiceMock, times(1)).addTopUpPlan("TEN_DEVICE", 1);
    }

    @Test
    public void shouldFailToAddTopUpWhenDuplicateTest() throws StreamingServiceException {
        List<String> list = new ArrayList<>();
        String expected = "ADD_TOPUP_FAILED DUPLICATE_TOPUP";
        doThrow(new StreamingServiceException(expected)).when(subscriptionServiceMock).addTopUpPlan("TEN_DEVICE", 1);
        list.add("ADD_TOPUP");
        list.add("TEN_DEVICE");
        list.add("1");
        addTopUpCommand.execute(list);
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}

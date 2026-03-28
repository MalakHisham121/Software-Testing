package test.services;

import main.Interfaces.IScrubDigits;
import main.Interfaces.IScrubEmails;
import main.Services.MainScrubber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MainScrubberTest {

    @InjectMocks
    private MainScrubber mainScrubber;

    @Mock
    private IScrubDigits iScrubDigits;

    @Mock
    private IScrubEmails iScrubEmails;



}

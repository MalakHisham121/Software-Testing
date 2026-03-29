package test.services;

import main.Interfaces.IScrub;
import main.Interfaces.IScrubDigits;
import main.Interfaces.IScrubEmails;
import static org.mockito.Mockito.*;
import main.Models.ScrubMode;
import main.Services.DigitScrubber;
import main.Services.EmailScrubber;
import main.Services.MainScrubber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MainScrubberTest {

    @InjectMocks
    private MainScrubber mainScrubber;

    @Mock
    private IScrubDigits ScrubDigits ;

    @Mock
    private IScrubEmails ScrubEmails ;



    @Test
    void EmailScrubModeOnlyDelegatesEmailScrubberTest(){
        String in = "Email me at test@test.com";
        String ex = "Email me at [EMAIL_HIDDEN]";
        when(ScrubEmails.scrub(in)).thenReturn(ex);
        assertEquals(ex,mainScrubber.scrub(in,ScrubMode.ONLY_EMAILS));

        verify(ScrubEmails,times(1)).scrub(in);
        verify(ScrubDigits,never()).scrub(in);
    }
    @Test
    void DigitScrubModeOnlyDelegatesDigitScrubberTest(){

        String in = "Contact me at 0144837238";
        String ex = "Contact me at XXXXXXXXXX";
        when(ScrubDigits.scrub(in)).thenReturn(ex);
        assertEquals(ex,mainScrubber.scrub(in,ScrubMode.ONLY_DIGITS));

        verify(ScrubDigits,times(1)).scrub(in);
        verify(ScrubEmails,never()).scrub(in);
    }


    @Test
    void FullScrubbingTest(){
        String in = "Call me at 0144837238 or though email test@test.com";
        String afterdigitScrubbing = "Call me at XXXXXXXXXX or though email test@test.com";
        String finalout = "Call me at XXXXXXXXXX or though email [EMAIL_HIDDEN]";

        when(ScrubDigits.scrub(in)).thenReturn(afterdigitScrubbing);
        when(ScrubEmails.scrub(afterdigitScrubbing)).thenReturn(finalout);
        assertEquals(finalout,mainScrubber.scrub(in,ScrubMode.FULL_SCRUBBING));

        verify(ScrubDigits,times(1)).scrub(in);
        verify(ScrubEmails,times(1)).scrub(afterdigitScrubbing);
    }


    // negative scenarios
    @Test
    void throwNullPointerExceptionIfInputisNull(){
        assertThrows(NullPointerException.class,()->mainScrubber.scrub(null,ScrubMode.FULL_SCRUBBING));

        verifyZeroInteractions(ScrubDigits,ScrubEmails);

    }
    @Test
    void throwIllegalArgumentExceptionIfInputisBlank(){
        assertThrows(IllegalArgumentException.class,()->mainScrubber.scrub("      ",ScrubMode.FULL_SCRUBBING));

        verifyZeroInteractions(ScrubDigits,ScrubEmails);

    }

}

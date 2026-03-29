package test.services;

import main.Services.DigitScrubber;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DigitSrubberTest {
    // This class would test regex logic

    private final DigitScrubber digitScrubber= new DigitScrubber();

    // Happy scenarios

    @Test
    void replaceNormalDigitsWithXTest(){
        String in = "Hello, you could contact me on 013498437  or 017393825";
        String expected = "Hello, you could contact me on XXXXXXXXX  or XXXXXXXXX";
        assertEquals(expected,digitScrubber.scrub(in));
    }
    @Test
    void dontReplaceDigitsFollowedByDollarSignTest(){
        String in = "The price is 8569$ but after the discount the total is 6979$ , thanks for choosing us.";
        String expected = "The price is 8569$ but after the discount the total is 6979$ , thanks for choosing us.";
        assertEquals(expected,digitScrubber.scrub(in));
    }
    @Test
    void CombinedTestWhenInputContainsPhoneNumberAndPriceBothReplaceOnlyPhoneNumberwithXTest(){
        String in = "Ok, Order Confirmed the total is 549$ and for any modifications you could contact use on our hotline 19458";
        String expected = "Ok, Order Confirmed the total is 549$ and for any modifications you could contact use on our hotline XXXXX";
        assertEquals(expected,digitScrubber.scrub(in));
    }

    // bad scenarios


    @Test
    void throwNullPointerExceptionIfInputIsNullTest(){
        assertThrows(NullPointerException.class,()->digitScrubber.scrub(null));
    }

    @Test
    void throwIllegalArgumentExceptionIfInputIsBlankTest(){
        assertThrows(IllegalArgumentException.class,()->digitScrubber.scrub(""));
        assertThrows(IllegalArgumentException.class,()->digitScrubber.scrub(" "));
        assertThrows(IllegalArgumentException.class,()->digitScrubber.scrub("        "));

    }






}

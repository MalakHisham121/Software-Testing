package test.services;

import main.Services.EmailScrubber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EmailScrubberTest {

    // This class would test regex logic
// test cases ,
    /*
    * special char in the mail
    * all chars
    * all numbers
    * numbers then chars the numbers again
    * empty prefix
    * empty after gmail
    * empty . empty after the gmail
    *
     */

    private final EmailScrubber emailScrubber= new EmailScrubber();

    // positive scenarios
    @Test
    void replaceValidEmailContainsOnlyCharsWithHiddenTagTest() {
        // test1
        String in = "I am available at malak@gmail.com";
        String expected = "I am available at [EMAIL_HIDDEN]";
        assertEquals(expected, emailScrubber.scrub(in));


        in = "I am available at malak@g.com";
        expected = "I am available at [EMAIL_HIDDEN]";
        assertEquals(expected,emailScrubber.scrub(in))  ;

    }
    @Test
    void replaceEmailContainsCharsAndNumbersWithHiddenTagTest() {
        // test2
       String in = "I am available at malak123@gmail.com";
        String expected = "I am available at [EMAIL_HIDDEN]";
        assertEquals(expected, emailScrubber.scrub(in));
    }
    @Test
    void replaceEmailContainsOnlyNumbersWithHiddenTagTest() {
        // test3
         String in = "I am available at 123@gmail.com";
         String expected = "I am available at [EMAIL_HIDDEN]";
        assertEquals(expected,emailScrubber.scrub(in))  ;


    }

    @Test
    void notReplacinginvalidEmailsTest() {
        // test1
        String in = "I am available at @gmail.com";
        String expected = "I am available at @gmail.com";
        assertEquals(expected, emailScrubber.scrub(in));

        // test2
         in = "I am available at mal3#@gmail.com";
         expected = "I am available at mal3#@gmail.com";
        assertEquals(expected, emailScrubber.scrub(in));

        // test3
         in = "I am available at malak@gmail";
         expected = "I am available at malak@gmail";
        assertEquals(expected,emailScrubber.scrub(in))  ;

        // test4
         in = "I am available at malak@gmail.c";
         expected = "I am available at malak@gmail.c";
        assertEquals(expected,emailScrubber.scrub(in))  ;

         // test5
         in = "I am available at 123@gmailllllllllll.com";
         expected = "I am available at 123@gmailllllllllll.com";
        assertEquals(expected,emailScrubber.scrub(in))  ;
    }


    // negative scenarios
    @Test
    void throwNullPointerExceptionIfInputIsNullTest(){
        assertThrows(NullPointerException.class,()->emailScrubber.scrub(null));
    }

    @Test
    void throwIllegalArgumentExceptionIfInputIsBlankTest(){

        assertThrows(IllegalArgumentException.class,()->emailScrubber.scrub(""));
        assertThrows(IllegalArgumentException.class,()->emailScrubber.scrub(" "));
        assertThrows(IllegalArgumentException.class,()->emailScrubber.scrub("        "));

    }
}

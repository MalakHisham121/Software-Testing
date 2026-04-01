package test.services;

import main.Services.EmailScrubber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailScrubberTest {

    private final EmailScrubber emailScrubber = new EmailScrubber();

         // Happy Scenarios
        @Test
        void shouldHideStandardEmailWithCharsOnly() {
            String in = "I am available at malak@gmail.com";
            String expected = "I am available at [EMAIL_HIDDEN]";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        @Test
        void shouldHideEmailWithSingleCharDomain() {
            String in = "I am available at malak@g.com";
            String expected = "I am available at [EMAIL_HIDDEN]";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        @Test
        void shouldHideEmailWithLongModernTld() {
            String in = "I am available at malak@solutions.engineering";
            String expected = "I am available at [EMAIL_HIDDEN]";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        @Test
        void shouldHideEmailWithHyphenInLocalPart() {
            String in = "I am available at malak-hesham@gmail.com";
            String expected = "I am available at [EMAIL_HIDDEN]";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        @Test
        void shouldHideEmailWithSpecialCharHash() {
            String in = "I am available at malak#@gmail.com";
            String expected = "I am available at [EMAIL_HIDDEN]";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        @Test
        void shouldHideEmailWithMultipleSubdomains() {
            String in = "I am available at malak@fci.cu.edu.eg";
            String expected = "I am available at [EMAIL_HIDDEN]";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        @Test
        void shouldHideEmailWithMixedCharsAndNumbers() {
            String in = "I am available at malak123@gmail.com";
            String expected = "I am available at [EMAIL_HIDDEN]";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        @Test
        void shouldHideEmailWithNumbersOnlyLocalPart() {
            String in = "I am available at 123@gmail.com";
            String expected = "I am available at [EMAIL_HIDDEN]";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        // egde case scenarios
        @Test
        void shouldNotHideEmailWithEmptyLocalPart() {
            String in = "I am available at @gmail.com";
            String expected = "I am available at @gmail.com";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        @Test
        void shouldNotHideEmailMissingTld() {
            String in = "I am available at malak@gmail";
            String expected = "I am available at malak@gmail";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        @Test
        void shouldNotHideEmailWithTldTooShort() {
            String in = "I am available at malak@gmail.c";
            String expected = "I am available at malak@gmail.c";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        @Test
        void shouldHideEmailWithExcessivelyLongDomain() {
            String in = "I am available at malak@cairo-university.com";
            String expected = "I am available at [EMAIL_HIDDEN]";
            assertEquals(expected, emailScrubber.scrub(in));
        }

        @Test
        void shouldHideUppercase() {
            String in = "I am available at malak@gmail.COM";
            String expected = "I am available at [EMAIL_HIDDEN]";
            assertEquals(expected, emailScrubber.scrub(in));
        }



        @Test
        void throwNullPointerExceptionIfInputIsNull() {
            assertThrows(NullPointerException.class, () -> emailScrubber.scrub(null));
        }

        @Test
        void throwIllegalArgumentExceptionIfInputIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> emailScrubber.scrub(""));
            assertThrows(IllegalArgumentException.class, () -> emailScrubber.scrub(" "));
            assertThrows(IllegalArgumentException.class, () -> emailScrubber.scrub("        "));
        }

}
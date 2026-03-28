package main.Services;

import main.Interfaces.IScrubEmails;

public class EmailScrubber implements IScrubEmails {
    @Override
    public String scrub(String input) {
        if (input == null ) {
            throw new NullPointerException("Input cannot be null");
        }else if (input.isBlank()){
            throw new IllegalArgumentException("Input cannot be blank");
        }
        return input.replaceAll("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}", "[EMAIL_HIDDEN]");
    }
}

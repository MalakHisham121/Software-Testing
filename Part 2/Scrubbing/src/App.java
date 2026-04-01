import main.Interfaces.IScrubDigits;
import main.Interfaces.IScrubEmails;
import main.Models.ScrubMode;
import main.Services.DigitScrubber;
import main.Services.EmailScrubber;
import main.Services.MainScrubber;

public class App {
    public static void main(String[] args) throws Exception {
        IScrubDigits dScrubber = new DigitScrubber();
//        IScrubEmails scrubEmails = new EmailScrubber();
//        MainScrubber mainScrubber = new MainScrubber(dScrubber, scrubEmails);
//        System.out.println(mainScrubber.scrub("Contact me at 02344$",ScrubMode.ONLY_DIGITS));
        System.out.println(dScrubber.scrub("Contact me at 02344$"));
    
    }
}

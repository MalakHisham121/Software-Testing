import main.Interfaces.IScrubDigits;
import main.Services.DigitScrubber;

public class App {
    public static void main(String[] args) throws Exception {
        IScrubDigits dScrubber = new DigitScrubber();
        System.out.println(dScrubber.scrub("Contact me at 02344$"));
    
    }
}

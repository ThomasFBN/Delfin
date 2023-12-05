
import domain.Swimmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserinterfaceTest {
    Swimmer swimmer1;
    Swimmer swimmer2;
    Swimmer swimmer3;
    Swimmer swimmer4;
    Swimmer swimmer5;

    @BeforeEach
    public void setUp() {

        swimmer1 = new Swimmer("Ejner", "Hellerup", " 12345678", "ejner@gmail.com", LocalDate.parse(("2009-11-22")), true, true);
        swimmer2 = new Swimmer("Oliver", "Nørrebro", " 12345678", "oliver@gmail.com", LocalDate.parse(("2000-11-22")), true, false);
        swimmer3 = new Swimmer("Thomas", "Hellerup", " 12345678", "ejner@gmail.com", LocalDate.parse(("1996-11-22")), true, true);
        swimmer4 = new Swimmer("Thomas", "Hellerup", " 12345678", "ejner@gmail.com", LocalDate.parse(("1955-11-22")), true, true);
        swimmer5 = new Swimmer("Thomas", "Hellerup", " 12345678", "ejner@gmail.com", LocalDate.parse(("1960-11-22")), false, true);

    }

    @Test
    public void kontigentTest() {

            // For aktiv junior (under 18 år)
            double expectedFee1 = 1000.0;
            assertEquals(expectedFee1, swimmer1.calculateExpectedMembershipFees());

            // For aktiv senior (60 år og derover)
            double expectedFee2 = 1600.0 * 0.75;
            assertEquals(expectedFee2, swimmer4.calculateExpectedMembershipFees());

            // For aktiv senior (18 år og derover)
            double expectedFee3 = 1600.0;
            assertEquals(expectedFee3, swimmer3.calculateExpectedMembershipFees());

            // For passivt medlemskab
            double expectedFee4 = 500.0;
            assertEquals(expectedFee4, swimmer5.calculateExpectedMembershipFees());
    }
}
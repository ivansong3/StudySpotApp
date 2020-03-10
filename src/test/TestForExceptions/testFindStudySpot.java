package test.TestForExceptions;

import exceptions.OOBSearchingForStudySpot;
import main.model.Focus;
import main.model.StudySpot;
import main.model.StudySpots;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class testFindStudySpot {
    StudySpot ss1 = new Focus("", "1", true, false);
    StudySpot ss2 = new Focus("", "2", true, false);
    StudySpot ss3 = new Focus("", "3", true, false);
    StudySpots sss1 = new StudySpots();

    @BeforeEach
    public void setup() {
        sss1.addSpot(ss1);
        sss1.addSpot(ss2);
        sss1.addSpot(ss3);
    }

    @Test
    public void CorrectStringInput() throws OOBSearchingForStudySpot {
        try {
            sss1.findStudySpot("2");
        } catch(OOBSearchingForStudySpot oob) {
            fail("2 is within index");
        } catch(NumberFormatException nfe) {
            fail("2 is a number");
        }
    }

    @Test
    public void inputOutOfBound() throws OOBSearchingForStudySpot {
        try {
            sss1.findStudySpot("4");
            fail("should throw exception");

        } catch(OOBSearchingForStudySpot oob) {
            // Don't do anything
        } catch(NumberFormatException nfe) {
            fail("2 is a number");
        }
    }

    @Test
    public void inputNotNumber() throws OOBSearchingForStudySpot {
        try {
            sss1.findStudySpot("what");
            fail("should throw exception");
        } catch(OOBSearchingForStudySpot oob) {
            fail("does not apply");
        } catch(NumberFormatException nfe) {
            //do nothing
        }
    }
}



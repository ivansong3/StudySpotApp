package test.TestForExceptions;

import main.exceptions.GoingBack;
import main.model.Focus;
import main.model.StudySpot;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.fail;

public class testOOBEditingStudySpot {
    Scanner typed = new Scanner(System.in);
    StudySpot s1 = new Focus("tim", "34th ave", true, false);


    @Test
    public void testEditingStudySpotBack() throws OOBBusiness, OOBOpeningClosingTime {
        try {
            s1.editStudySpot("b", typed);
        } catch(GoingBack gb) {
            System.out.println("caught going back");
        }
    }

    @Test
    public void testEditingStudySpotNotBack() throws GoingBack, OOBBusiness, OOBOpeningClosingTime {
        try {
            s1.editStudySpot("3", typed);
        } catch(GoingBack gb) {
            fail("program should not catch goingback exception");
        }
    }

}

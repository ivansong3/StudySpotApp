package tests;

import exceptions.OobSearchingForStudySpot;
import model.Focus;
import model.Romantic;
import model.StudySpot;
import model.StudySpots;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testStudySpots {
    StudySpot s1;
    StudySpot s2;
    StudySpots ss1;

    @BeforeEach
    public void setup(){
        s1 = new Focus("Tim Horton's","1323 w26th ave", true, false);
        s2 = new Romantic("Mcd", "288 w2th ave", true, true);
        ss1 = new StudySpots();
    }


    @Test
    public void testAddDifferentSpots() {
        ss1.addSpot(s1);
        assertTrue(ss1.getStudySpots().get("All").contains(s1));
        assertTrue(ss1.getStudySpots().get("Focus").contains(s1));
        assertEquals(1, ss1.getStudySpots().get("All").size());
        assertEquals(1, ss1.getStudySpots().get("Focus").size());

        ss1.addSpot(s2);
        assertTrue(ss1.getStudySpots().get("All").contains(s1));
        assertTrue(ss1.getStudySpots().get("Focus").contains(s1));

        assertTrue(ss1.getStudySpots().get("All").contains(s2));
        assertTrue(ss1.getStudySpots().get("Romantic").contains(s2));

        assertEquals(2, ss1.getStudySpots().get("All").size());
        assertEquals(1, ss1.getStudySpots().get("Focus").size());
        assertEquals(1, ss1.getStudySpots().get("Romantic").size());
    }

    @Test
    public void testAddSameSpots() {
        ss1.addSpot(s1);

        ss1.addSpot(s1);
        assertTrue(ss1.getStudySpots().get("All").contains(s1));
        assertTrue(ss1.getStudySpots().get("Focus").contains(s1));
        assertEquals(1, ss1.getStudySpots().get("All").size());
        assertEquals(1, ss1.getStudySpots().get("Focus").size());
    }

    @Test
    public void testFindStudySpot() throws OobSearchingForStudySpot {
        ss1.addSpot(s1);
        ss1.addSpot(s2);
        assertEquals(s1, ss1.findStudySpot("0","All" ));
        assertEquals(s2, ss1.findStudySpot("1","All" ));
    }

}



package test.TestForExceptions;

import interfaces.Loadable;
import main.model.StudySpots;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testingLoadable {

    StudySpots sss = new StudySpots();
    StudySpots ssn = new StudySpots();

    public void testLoad(Loadable l, String s) throws IOException {
        l.load(s);
    }

    @Test
    public void testLOAD() throws IOException {
        // json file has Study Spots: {"name":"timmy","address":"1323 w26th ave"}
        //                            {"name":"ruck","address":"387 w7th ave"}

        sss.load("testLoadable.json");
        assertEquals(sss.findStudySpot(0).getName(), "timmy");
        assertEquals(sss.findStudySpot(0).getAddress(), "1323 w26th ave");
        assertEquals(sss.findStudySpot(1).getName(), "ruck");
        assertEquals(sss.findStudySpot(1).getAddress(), "387 w7th ave");
    }

    @Test

    public void testLOADempty() throws IOException {
        ssn.load("testLOADABLE1.json");
        assertEquals(ssn.size(), 0);
    }

}

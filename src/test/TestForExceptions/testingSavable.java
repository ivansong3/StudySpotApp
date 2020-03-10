package test.TestForExceptions;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import interfaces.Savable;
import main.model.StudySpot;
import main.model.StudySpots;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testingSavable {

    StudySpots sss = new StudySpots();
    StudySpots ssn = new StudySpots();

    public void testSave(Savable l, String s) throws IOException {
        l.save(s);
    }
    @Test
    public void testLOAD() throws IOException {
        // create json file with studyspots s1 and s2
        StudySpot s1 = new StudySpot("Tim Horton's","1323 w26th ave");
        StudySpot s2 = new StudySpot ("Mcd", "288 w2th ave");
        sss.addSpot(s1);
        sss.addSpot(s2);
        testSave(sss, "testSavable.json");

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("testSavable.json"));
        StudySpot[] sss = gson.fromJson(reader, StudySpot[].class);

        assertEquals(sss[0].getName(), "Tim Horton's");
        assertEquals(sss[0].getAddress(), "1323 w26th ave");
        assertEquals(sss[1].getName(), "Mcd");
        assertEquals(sss[1].getAddress(), "288 w2th ave");
    }

    @Test
    public void testLOADEmpty() throws IOException {
        ssn.save("testSAVABLE1.json");

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("testSAVABLE1.json"));
        StudySpot[] ssn = gson.fromJson(reader, StudySpot[].class);

        assertEquals(ssn.length, 0);
    }


}
package model;

import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudySpots {

    private Map<String, ArrayList<StudySpot>> studySpots;

    public StudySpots() {
        studySpots = new HashMap<>();
        studySpots.put("All", new ArrayList<StudySpot>());
        studySpots.put("Focus", new ArrayList<StudySpot>());
        studySpots.put("Relaxing", new ArrayList<StudySpot>());
        studySpots.put("Romantic", new ArrayList<StudySpot>());
        studySpots.put("TwentyFourHour", new ArrayList<StudySpot>());
    }

    //EFFECTS: print out all the study spots in the list with name and address
    public void displayStudySpots(String k) {
        int i = 0;
        for (StudySpot s: studySpots.get(k)) {
            System.out.println(i + ") " + s.getName() + ": " + s.getAddress()
                    + "  " + s.getReadableDistance() + "  " + s.getTime());
            i = ++i;
        }
    }

    public String[] turnToListOfString(String k) {
        ArrayList<String> sss = new ArrayList<>();
        for (StudySpot s: studySpots.get(k)) {
            sss.add(s.getName() + ": " + s.getAddress()
                    + "  " + s.getReadableDistance() + "  " + s.getTime());
        }
        String[] studySpots = new String[sss.size()];
        return studySpots = sss.toArray(studySpots);
    }


    public boolean contains(StudySpot s) {
        return studySpots.get("All").contains(s);
    }



   // MODIFIES: this
    // EFFECTS: if studyspot does not already exists, then add it
    //          to the "all" and corresponding list and produce true,
   //           other
   //           wise produce false
    public void addSpot(StudySpot s) {
        if (!(studySpots.get("All").contains(s))) {
            studySpots.get("All").add(s);
            studySpots.get(s.getClass().getSimpleName()).add(s);
        } else {
            System.out.println("!!! ALREADY ADDED");
        }
    }

    public void addSpotToAll(StudySpot s) {
        studySpots.get("All").add(s);
    }




    public StudySpot findStudySpot(String s, String k) {
        return studySpots.get(k).get(Integer.parseInt(s));
    }


    public ArrayList<StudySpot> getStudySpotsList(String k) {
        return studySpots.get(k);
    }



    public Map<String, ArrayList<StudySpot>> getStudySpots() {
        return studySpots;
    }

    public void setDistance(String origin) throws ApiException, InterruptedException, IOException {
        for (StudySpot s: studySpots.get("All")) {
            s.setDistanceFromOrigin(origin);
        }
    }




}





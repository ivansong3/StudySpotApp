package model;

import exceptions.GoingBack;
import exceptions.OobEditingStudySpot;

import java.util.Scanner;

import static ui.Main.tfLoop;

public class Romantic extends StudySpot {
    private String music;
    private boolean restaurantsNearBy;

    public Romantic(String name, String address, boolean wifi, boolean outlet) {
        super(name, address, wifi, outlet);
    }

    @Override
    public void getDescription() {
        System.out.println("you have added [Romantic StudySpot: " + getName() + "]");
    }

    @Override
    public void displayStudySpot() {
        super.displayStudySpot();
        System.out.println("[7] opens from " + openingTime + " to " + closingTime);
        System.out.println("[8] Type of music: " + music);
        System.out.println("[9] Nice restaurants nearby? " + restaurantsNearBy);
        System.out.println("[10] Created by " + user.getUsername());
    }

    @Override
    public void editStudySpot(String s, Scanner typed) throws GoingBack, OobEditingStudySpot {
        super.editStudySpot(s, typed);
        if (s.equals("7")) {
            setOpenClosingTime(typed);
        } else if (s.equals("8")) {
            System.out.println("What type of music does the study spot play?");
            music = typed.nextLine();
        } else if (s.equals("9")) {
            restaurantsNearBy = tfLoop("Are there good restaurants nearby?");
        }
    }
}

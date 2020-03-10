package model;

import exceptions.GoingBack;
import exceptions.OobEditingStudySpot;

import java.util.Scanner;

import static ui.Main.tfLoop;

public class Relaxing extends StudySpot {
    private Boolean outdoor;
    private Boolean niceView;

    public Relaxing(String name, String address, boolean wifi, boolean outlet) {
        super(name, address, wifi, outlet);
    }


    @Override
    public void getDescription() {
        System.out.println("you have added [Relaxing StudySpot: " + getName() + "]");
    }

    @Override
    public void displayStudySpot() {
        super.displayStudySpot();
        System.out.println("[7] opens from " + openingTime + " to " + closingTime);
        System.out.println("[8] Outdoor? " + outdoor);
        System.out.println("[9] NiceView? " + niceView);
        System.out.println("[10] Created by " + user.getUsername());
    }

    @Override
    public void editStudySpot(String s, Scanner typed) throws GoingBack, OobEditingStudySpot {
        super.editStudySpot(s, typed);
        if (s.equals("7")) {
            setOpenClosingTime(typed);
        } else if (s.equals("8")) {
            outdoor = tfLoop("Is the study spot outdoor? type t/f");
        } else if (s.equals("9")) {
            niceView = tfLoop("Does the study spot have a nice view? type t/f");
        }
    }

}

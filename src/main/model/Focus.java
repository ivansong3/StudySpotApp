package model;

import exceptions.GoingBack;
import exceptions.OobEditingStudySpot;

import java.util.Scanner;

public class Focus extends StudySpot {



    public Focus(String name, String address, boolean wifi, boolean outlet) {
        super(name, address, wifi, outlet);
    }


    @Override
    public void getDescription() {
        System.out.println("you have added [Focus StudySpot: " + getName() + "]");
    }

    @Override
    public void displayStudySpot() {
        super.displayStudySpot();
        System.out.println("[7] opens from " + openingTime + " to " + closingTime);
        System.out.println("[8] Created by " + user.getUsername());
    }

    @Override
    public void editStudySpot(String s, Scanner typed) throws GoingBack, OobEditingStudySpot {
        super.editStudySpot(s, typed);
        if (s.equals("7")) {
            setOpenClosingTime(typed);
        }
    }


}


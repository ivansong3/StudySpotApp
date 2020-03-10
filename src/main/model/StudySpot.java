package model;

import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import exceptions.GoingBack;
import exceptions.OobEditingStudySpot;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static ui.Main.tfLoop;

public abstract class StudySpot implements Comparable<StudySpot> {
    protected String name;
    protected String address;
    protected String noiseLevel;
    protected boolean wifi;
    protected boolean outlet;
    protected int openingTime;
    protected int closingTime;
    protected int business;
    protected transient User user;
    private transient long distance;
    private transient String readableDistance;
    private transient String time;


    public String getReadableDistance() {
        return readableDistance;
    }

    public String getTime() {
        return time;
    }

    public StudySpot(String name, String address, boolean wifi, boolean outlet) {
        this.name = name;
        this.address = address;
        this.wifi = wifi;
        this.outlet = outlet;
        this.noiseLevel = "";
        this.openingTime = 0;
        this.closingTime = 0;
        this.business = 0;
    }
    //EFFECTS: get the address of the StudySpot

    public String getAddress() {
        return this.address;
    }

    //EFFECTS: get the name of the StudySpot
    public String getName() {
        return this.name;
    }

    //EFFECTS: print "you have added (StudySpot type): (name of StudySpot)"
    public abstract void getDescription();

    public void displayStudySpot() {
        System.out.println("[1] " + name);
        System.out.println("[2] " + address);
        System.out.println("[3] Has wifi? " + wifi);
        System.out.println("[4] Has outlet? " + outlet);
        System.out.println("[5] Appropriate voice level: " + noiseLevel);
        System.out.println("[6] How busy: " + business + "/10");
    }

    public String studySpotToString() {
        return ("<html>" + name + "<br>" + address + "<br>" + "Has wifi? " + wifi + "<br>" + "Has outlet? " + outlet
                + "<br>" + " Appropriate voice level: " + noiseLevel + "<br>" + "How busy: " + business + "/10" + "<br>"
                +  "opens from " + openingTime + " to " + closingTime + "<br>"
                +  "Created by " + user.getUsername() + "</html>");
    }

    public void editStudySpot(String s, Scanner typed) throws GoingBack, OobEditingStudySpot {
        if (s.equals("1")) {
            System.out.println("Type in new name");
            name = typed.nextLine();
        } else if (s.equals("2")) {
            System.out.println("Type in new address");
            address = typed.nextLine();
        } else if (s.equals("3")) {
            wifi = tfLoop("Has wifi? type t/f");
        } else if (s.equals("4")) {
            outlet = tfLoop("Has wifi? type t/f");
        } else if (s.equals("5")) {
            System.out.println("How loud can you be at the study spot?");
            noiseLevel = typed.nextLine();
        } else if (s.equals("6")) {
            setBusiness(typed);
        } else if (s.equals("b")) {
            throw new GoingBack();
        }
    }

    public void setUser(User u) {
        if (! (u.equals(user))) {
            user = u;
            u.addStudySpot(this);
        }
    }

    public void setOpenClosingTime(Scanner typed) throws OobEditingStudySpot {
        System.out.println("Opening time:");
        setTime(typed);
        System.out.println("Closing time:");
        setTime(typed);
    }

    private void setTime(Scanner typed) throws OobEditingStudySpot {
        String s1 = typed.nextLine();
        if (Integer.parseInt(s1) > 12) {
            throw new OobEditingStudySpot();
        } else {
            openingTime = Integer.parseInt(s1);
        }
    }

    public void setBusiness(Scanner typed) throws OobEditingStudySpot {
        System.out.println("How busy is the place, rate it out of 10");
        String s1 = typed.nextLine();
        if (Integer.parseInt(s1) > 10 && Integer.parseInt(s1) >= 0) {
            throw new OobEditingStudySpot();
        } else {
            business = Integer.parseInt(s1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudySpot studySpot = (StudySpot) o;
        return address.equals(studySpot.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    public void setDistanceFromOrigin(String origin) throws ApiException, InterruptedException, IOException {
        GeoApiContext distCalc = new GeoApiContext.Builder()
                .apiKey("AIzaSyBlCPYKns92_8EDxsa0VpXHiJksyGIVlX4")
                .build();

        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(distCalc);
        DistanceMatrix result = req.origins(origin)
                .destinations(address)
                .mode(TravelMode.TRANSIT)
                .avoid(DirectionsApi.RouteRestriction.TOLLS)
                .language("en-US")
                .await();
        distance = result.rows[0].elements[0].distance.inMeters;
        readableDistance = result.rows[0].elements[0].distance.humanReadable;
        time = result.rows[0].elements[0].duration.humanReadable;
    }

    public long getDistance() {
        return distance;
    }

    @Override
    public int compareTo(StudySpot s) {
        if (distance > s.getDistance()) {
            return 1;
        } else if (distance < s.getDistance()) {
            return -1;
        } else {
            return 0;
        }
    }

}

package ui;

import com.google.maps.errors.ApiException;
import exceptions.GoingBack;
import exceptions.OobEditingStudySpot;
import model.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException, ApiException, InterruptedException, IOException {
        Scanner typed = new Scanner(System.in);

        UserData ud = new UserData();
        ud.load();

        User currentUser = new User(null, null);
        System.out.println("[1] log in  [2] sign up ");
        String login = typed.nextLine();
        if (login.equals("1")) {
            boolean running = true;
            while (running) {
                System.out.println("Username:");
                String username = typed.nextLine();
                System.out.println("Password");
                String password = typed.nextLine();
                currentUser = ud.getExistingUser(username,password);
                if (currentUser != null) {
                    running = false;
                }
            }

        } else if (login.equals("2")) {
            System.out.println("Username:");
            String username = typed.nextLine();
            System.out.println("Password");
            String password = typed.nextLine();
            currentUser = ud.newUser(username,password);
        }

        System.out.println("Input Current Address");
        String location = typed.nextLine();

        StudySpots sss = new StudySpots();
        ud.redistributeUserData(sss);
        sss.setDistance(location);
        TestSubject ts = new TestSubject();

        boolean running = true;

        while (running) {
            System.out.println("enter: "
                    + "[1] add study spots "
                    + "[2] see study spots "
                    + "[3] exit and save "
                    + "[4] view my study spots");

            String operation = typed.nextLine();

            // search study spot
            if (operation.equals("1")) {
                System.out.println("Type name of study spot");
                String name = typed.nextLine();

                System.out.println("Type address of study spot");
                String address = typed.nextLine();

                //t/f input
                boolean wifi = tfLoop("Does the StudySpot have wifi? (type t/f)");
                boolean outlet = tfLoop("Does the StudySpot have outlet? (type t/f)");

                // CHOOSING TYPE OF STUDY SPOT
                System.out.println("What type of StudySpot is this?");

                System.out.println("[1] Focus: StudySpots that are quiet, not distracting, complete focus");
                System.out.println("[2] Relaxing: StudySpots that are for chill study time: get some work done "
                        + "while still keeping your sanity");
                System.out.println("[3] Romantic: StudySpots for study dates with an aesthetic, romantic atmosphere");
                System.out.println("[4] TwentyFourHours: StudySpots for all nighters");

                String operation1 = typed.nextLine();
                if (operation1.equals("1")) {
                    StudySpot ss = new Focus(name, address, wifi, outlet);
                    sss.addSpot(ss);
                    ss.getDescription();
                    ss.setDistanceFromOrigin(location);
                    currentUser.addStudySpot(ss);
                } else if (operation1.equals("2")) {
                    StudySpot ss = new Relaxing(name, address, wifi, outlet);
                    sss.addSpot(ss);
                    ss.getDescription();
                    ss.setDistanceFromOrigin(location);
                    currentUser.addStudySpot(ss);
                } else if (operation1.equals("3")) {
                    StudySpot ss = new Romantic(name, address, wifi, outlet);
                    sss.addSpot(ss);
                    ss.getDescription();
                    ss.setDistanceFromOrigin(location);
                    currentUser.addStudySpot(ss);
                } else if (operation1.equals("4")) {
                    StudySpot ss = new TwentyFourHour(name, address, wifi, outlet);
                    sss.addSpot(ss);
                    ss.getDescription();
                    ss.setDistanceFromOrigin(location);
                    currentUser.addStudySpot(ss);
                }

                // view study spots
            } else if (operation.equals("2")) {
                System.out.println("[1] all  [2]Focus  [3] Relaxing  [4] Romantic  [5] TwentyFourHour");
                String s = typed.nextLine();
                if (s.equals("1")) {
                    Collections.sort(sss.getStudySpotsList("All"));
                    viewStudySpot(sss,"All", typed);
                } else if (s.equals("2")) {
                    Collections.sort(sss.getStudySpotsList("Focus"));
                    viewStudySpot(sss,"Focus", typed);
                } else if (s.equals("3")) {
                    Collections.sort(sss.getStudySpotsList("Relaxing"));
                    viewStudySpot(sss,"Relaxing", typed);
                } else if (s.equals("4")) {
                    Collections.sort(sss.getStudySpotsList("Romantic"));
                    viewStudySpot(sss,"Romantic", typed);
                } else if (s.equals("5")) {
                    Collections.sort(sss.getStudySpotsList("TwentyFourHour"));
                    viewStudySpot(sss,"TwentyFourHour", typed);
                }

                // exit and save
            } else if (operation.equals("3")) {
                running = false;
                ud.save();
            } else if (operation.equals("4")) {
                viewEditStudySpot(currentUser.getStudySpots(), "All", typed);
            } else if (operation.equals("5")) {
                ts.triggerObserver();
            }
        }
    }

    public static void viewStudySpot(StudySpots sss, String k, Scanner typed) {
        boolean running = true;
        while (running) {
            if (sss.getStudySpots().get(k).size() > 0) {
                System.out.println("type number to view the study spot, anything else to return home");
                sss.displayStudySpots(k);
                try {
                    String s = typed.nextLine();
                    StudySpot ss = sss.findStudySpot(s, k);
                    ss.displayStudySpot();

                } catch (IndexOutOfBoundsException idob) {
                    System.out.println("!!! incorrect input !!!");
                } catch (NumberFormatException nfe) {
                    System.out.println("going home");
                    running = false;
                }
            } else {
                System.out.println("No study spots added yet in this category!!");
                running = false;
            }


        }
    }

    public static void viewEditStudySpot(StudySpots sss, String k, Scanner typed) {
        boolean running = true;
        while (running) {
            if (sss.getStudySpots().get(k).size() > 0) {
                System.out.println("type number to view the study spot, anything else to return home");
                sss.displayStudySpots(k);
                try {
                    String s = typed.nextLine();
                    boolean editingStudySpot = true;
                    while (editingStudySpot) {
                        try {
                            sss.findStudySpot(s, k).displayStudySpot();
                            System.out.println("type number to edit, otherwise type b to go back");
                            sss.findStudySpot(s, k).editStudySpot(typed.nextLine(), typed);
                        } catch (GoingBack gb) {
                            editingStudySpot = false;
                        } catch (OobEditingStudySpot oob) {
                            System.out.println("!!! incorrect input !!!");
                        }
                    }
                } catch (IndexOutOfBoundsException oob) {
                    System.out.println("!!! incorrect input !!!");
                } catch (NumberFormatException nfe) {
                    System.out.println("going home");
                    running = false;
                }
            } else {
                System.out.println("No study spots added yet in this category!!");
                running = false;
            }
        }
    }



    //EFFECTS: takes in a t/f question, if user types "t", returns boolean true, if "f" returns boolean false
    //         if wrong input then rerun the loop and prints "wrong input"
    public static boolean tfLoop(String q) {
        Scanner typed = new Scanner(System.in);
        boolean typedTrue = true;
        boolean b = false;
        while (typedTrue) {
            System.out.println(q);
            String operation = typed.next();
            if (operation.equals("t")) {
                b = true;
                typedTrue = false;
            } else if (operation.equals("f")) {
                b = false;
                typedTrue = false;
            } else {
                System.out.println("not the right input");
                typedTrue = true;
            }
        }
        return b;
    }
}
package model;

public class TwentyFourHour extends StudySpot {

    public TwentyFourHour(String name, String address, boolean wifi, boolean outlet) {
        super(name, address, wifi, outlet);
    }

    @Override

    public void getDescription() {
        System.out.println("you have added [TwentyFourHours StudySpot: " + getName() + "]");
    }

}

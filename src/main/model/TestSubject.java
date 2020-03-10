package model;

public class TestSubject extends Observable{
    private String test;

    public TestSubject() {
        addObserver(new LikeObserver());
    }

    public void triggerObserver() {
        notifyObserver("TRIGGGGGGGGER");
    }
}

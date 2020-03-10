package model;

public class LikeObserver implements Observer{
    @Override
    public void update(Object arg) {
        System.out.println(arg);
    }
}

package model;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private StudySpots studySpots;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        studySpots = new StudySpots();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return username.equals(user.username)
                && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    public StudySpots getStudySpots() {
        return studySpots;
    }

    public void addStudySpot(StudySpot s) {
        if (!(studySpots.contains(s))) {
            studySpots.addSpotToAll(s);
            s.setUser(this);
        }
    }

    public String getUsername() {
        return username;
    }


}

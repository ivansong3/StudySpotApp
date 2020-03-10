package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import main.util.RuntimeTypeAdapterFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserData  {
    ArrayList<User> userData;
    private Gson gson;

    public UserData() {
        gson = setupGson();
        userData = new ArrayList<>();
    }

    public User getExistingUser(String username, String password) {
        for (User u: userData) {
            if (u.equals(new User(username, password))) {
                System.out.println("welcome back " + u.getUsername());
                return u;
            }
        }
        System.out.println("wrong username or password");
        return null;
    }

    public User newUser(String username, String password) {
        User newUser = new User(username, password);
        userData.add(newUser);
        System.out.println("new user " + newUser.getUsername() + " created");
        return newUser;
    }

    public void redistributeUserData(StudySpots sss) {
        for (User u: userData) {
            setUserBackInEveryStudySpot(u);
            copyUserDataToStudySpots(sss, u);
        }
    }

    private void copyUserDataToStudySpots(StudySpots sss, User u) {
        for (StudySpot s: u.getStudySpots().getStudySpotsList("All")) {
            sss.addSpot(s);
        }
    }

    private void setUserBackInEveryStudySpot(User u) {
        for (StudySpot s: u.getStudySpots().getStudySpotsList("All")) {
            s.setUser(u);
        }
    }

    public static Gson setupGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        RuntimeTypeAdapterFactory<StudySpot> myAdapterFactory
                = RuntimeTypeAdapterFactory.of(StudySpot.class, "type");

        myAdapterFactory.registerSubtype(Focus.class, "Focus");
        myAdapterFactory.registerSubtype(Relaxing.class, "Relaxing");
        myAdapterFactory.registerSubtype(Romantic.class, "Romantic");
        myAdapterFactory.registerSubtype(TwentyFourHour.class, "TwentyFourHour");

        gsonBuilder.registerTypeAdapterFactory(myAdapterFactory);

        gsonBuilder.enableComplexMapKeySerialization();


        return gsonBuilder.create();
    }

    public void save() throws IOException {
        String json = gson.toJson(userData);
        FileWriter writer = new FileWriter("userdata.json");
        writer.write(json);
        writer.close();
    }

    public void load() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("userdata.json"));
        User[] users = gson.fromJson(reader, User[].class);
        if (users != null) {
            for (User u: users) {
                userData.add(u);
            }
        }
    }

}

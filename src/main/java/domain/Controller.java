package domain;

import datasource.FileHandler;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private Database database;

    public Controller() {
        database = new Database();

    }

    public void saveSwimmer() {
        database.saveSwimmer();
    }

    public void loadSwimmer() throws FileNotFoundException {
        database.loadSwimmer();
    }

    public ArrayList<Swimmer> search(String søgeord) {
        return database.search(søgeord);

    }

    public void addSwimmer(String name, String address, String phonenumber, String mail, LocalDate birthday, boolean isActive, boolean isJunior, boolean isCompetitor) {
        database.createSwimmer(name, address, phonenumber, mail, birthday, isActive, isJunior, isCompetitor);
    }
}

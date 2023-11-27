package domain;

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

    public ArrayList activeSwimmers() {
        return database.activeMembers();
    }

    public ArrayList passiveSwimmers() {
        return database.passiveMembers();
    }

    public ArrayList juniorMembers() {
        return database.juniorMembers();
    }

    public ArrayList seniorMembers() {
        return database.seniorMembers();
    }

    public ArrayList competitiveMembers() {
        return database.competitiveMembers();
    }

    public ArrayList regularMembers() {
        return database.regularMembers();
    }


    public void addSwimmer(String name, String address, String phonenumber, String mail, LocalDate birthday, boolean isActive, boolean isJunior, boolean isCompetitor) {
        database.createSwimmer(name, address, phonenumber, mail, birthday, isActive, isJunior, isCompetitor);
    }

    public void addSwimTime(String member, Double time, LocalDate date, Event event, Disciplin disciplin, String placement) {
        database.createSwimTime(member, time, date, event, disciplin, placement);
    }

    public double calculateExpectedMembershipFeesForAll() {
        double totalExpectedFees = 0.0;

        for (Swimmer swimmer : database.getSwimmerList()) {
            totalExpectedFees += swimmer.calculateExpectedMembershipFees();
        }

        return totalExpectedFees;
    }
}

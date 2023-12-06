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

    public void saveSwimTime() {
        database.saveSwimTime();
    }

    public void loadSwimmer() throws FileNotFoundException {
        database.loadSwimmer();
    }

    public void loadSwimTime() throws FileNotFoundException {
        database.loadSwimTime();
    }

    public ArrayList<Swimmer> search(String søgeord) {
        return database.search(søgeord);

    }

    public ArrayList allSwimmers() {
        return database.getSwimmerList();
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

    public ArrayList missingPaymentMembers() {
        return database.missingPaymentMembers();
    }


    public void addSwimmer(String name, String address, String phonenumber, String mail, LocalDate birthday, boolean isActive, boolean isCompetitor) {
        database.createSwimmer(name, address, phonenumber, mail, birthday, isActive, isCompetitor);
    }

    public void addSwimTime(String member, Double time, LocalDate date, boolean competition, Disciplin disciplin, String placement) {
        database.createSwimTime(member, time, date, competition, disciplin, placement);
    }

    public double calculateExpectedMembershipFeesForAll() {
        double totalExpectedFees = 0.0;

        for (Swimmer swimmer : database.getSwimmerList()) {
            totalExpectedFees += swimmer.calculateExpectedMembershipFees();
        }

        return totalExpectedFees;
    }

    public void sortSwimmers() {
        database.sortSwimmers();
    }
    public void editMember(String memberName, String newName, String newAddress, String newPhonenumber, String newMail, LocalDate newBirthday, boolean newIsActive, boolean newIsJunior,boolean newIsCompetitor){
        database.editMember(memberName,newName, newAddress, newPhonenumber, newMail, newBirthday, newIsActive, newIsJunior, newIsCompetitor);
    }
    public void deleteSwimmer(String swimmerName){
        database.deleteSwimmer(swimmerName);
    }
    public ArrayList getTop5SwimmersPerDiscipline(){
        return database.getTop5SwimmersPerDiscipline();
    }
}

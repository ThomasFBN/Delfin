package domain;

import comperator.NameComperator;
import datasource.FileHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Database {
    private ArrayList<Swimmer> swimmerList;
    public ArrayList<SwimTime> swimTimeList;
    public ArrayList<Disciplin> disciplinList;
    private FileHandler filehandler = new FileHandler("SwimmerMembers.csv");
    private FileHandler filehandler2 = new FileHandler("SwimmerTimes.csv");

    public Database() {
        swimmerList = new ArrayList<>();
        swimTimeList = new ArrayList<>();
        disciplinList = new ArrayList<>();
    }

    public void createSwimmer(String name, String address, String phonenumber, String mail, LocalDate birthday, boolean isActive, boolean isCompetitor) {
        swimmerList.add(new Swimmer(name, address, phonenumber, mail, birthday, isActive, isCompetitor));

    }

    public void createSwimTime(String member, Double time, LocalDate date, boolean competition, Disciplin disciplin, String placement) {
        swimTimeList.add(new SwimTime(member, time, date, competition, disciplin, placement));
    }

    public void editMember(String memberName, String newName, String newAddress, String newPhonenumber, String newMail, LocalDate newBirthday, boolean newIsActive, boolean newIsJunior, boolean newIsCompetitor) {
        ArrayList<Swimmer> searchResult = search(memberName);

        if (!searchResult.isEmpty()) {
            Swimmer swimmer = searchResult.get(0);

            swimmer.setName(newName);
            swimmer.setAddress(newAddress);
            swimmer.setPhoneNumber(newPhonenumber);
            swimmer.setMail(newMail);
            swimmer.setBirthday(newBirthday);
            swimmer.setActive(newIsActive);
            swimmer.setJunior(newIsJunior);
            swimmer.setCompetitor(newIsCompetitor);

            System.out.println("Medlem opdateret: " + swimmer);
        } else {
            System.out.println("Medlem med navnet '" + memberName + "' blev ikke fundet.");
        }

    }


    public void saveSwimmer() {
        filehandler.saveSwimmer(swimmerList);
    }

    public void loadSwimmer() {
        swimmerList = filehandler.loadSwimmer();
    }

    public ArrayList<Swimmer> getSwimmerList() {
        return swimmerList;
    }

    public ArrayList<SwimTime> getSwimTimelist() {
        return swimTimeList;
    }

    public ArrayList<Swimmer> search(String søgeOrd) {
        ArrayList<Swimmer> søgeResultat = new ArrayList<>();
        for (Swimmer swimmer : swimmerList) {
            String name = swimmer.getName().toLowerCase();
            if (name.contains(søgeOrd.toLowerCase())) {
                søgeResultat.add(swimmer);
            }

        }
        return søgeResultat;


    }

    public ArrayList<Swimmer> activeMembers() {
        ArrayList<Swimmer> activeMembers = new ArrayList<>();
        for (Swimmer swimmer : swimmerList) {
            if (swimmer.getIsActive()) {
                activeMembers.add(swimmer);
            }
        }
        return activeMembers;
    }

    public ArrayList<Swimmer> passiveMembers() {
        ArrayList<Swimmer> passiveMembers = new ArrayList<>();
        for (Swimmer swimmer : swimmerList) {
            if (!swimmer.getIsActive()) {
                passiveMembers.add(swimmer);
            }
        }
        return passiveMembers();
    }

    public ArrayList<Swimmer> juniorMembers() {
        ArrayList<Swimmer> juniorMembers = new ArrayList<>();
        for (Swimmer swimmer : swimmerList) {
            if (swimmer.getIsJunior()) {
                juniorMembers.add(swimmer);
            }
        }
        return juniorMembers;
    }

    public ArrayList<Swimmer> seniorMembers() {
        ArrayList<Swimmer> seniorMembers = new ArrayList<>();
        for (Swimmer swimmer : swimmerList) {
            if (!swimmer.getIsJunior()) {
                seniorMembers.add(swimmer);
            }
        }
        return seniorMembers;
    }

    public ArrayList<Swimmer> competitiveMembers() {
        ArrayList<Swimmer> competitiveMembers = new ArrayList<>();
        for (Swimmer swimmer : swimmerList) {
            if (swimmer.getIsCompetitor()) {
                competitiveMembers.add(swimmer);
            }
        }
        return competitiveMembers;
    }

    public ArrayList<Swimmer> regularMembers() {
        ArrayList<Swimmer> regularMembers = new ArrayList<>();
        for (Swimmer swimmer : swimmerList) {
            if (!swimmer.getIsCompetitor()) {
                regularMembers.add(swimmer);
            }
        }
        return regularMembers;
    }

    public ArrayList<Swimmer> missingPaymentMembers() {
        ArrayList<Swimmer> missingPaymentMembers = new ArrayList<>();
        for (Swimmer swimmer : swimmerList) {
            if (!swimmer.getHasPaid()) {
                missingPaymentMembers.add(swimmer);
            }
        }
        return missingPaymentMembers;
    }

    public void saveSwimTime() {
        filehandler2.saveSwimTime(swimTimeList);
    }

    public void loadSwimTime() {
        swimTimeList = filehandler2.loadSwimTime();
    }

    public void sortSwimmers() {
        Collections.sort(swimmerList, new NameComperator());
    }

    public void deleteSwimmer(String swimmerName) {
        ArrayList<Swimmer> søgeResultat = search(swimmerName);

        if (!søgeResultat.isEmpty()) {
            Swimmer swimmer = søgeResultat.get(0);
            swimmerList.remove(swimmer);
            System.out.println("Svømmeren '" + swimmerName + "' er blevet slettet.");
        } else {
            System.out.println("Svømmeren med navnet '" + swimmerName + "' blev ikke fundet.");
        }
    }

    public ArrayList<ArrayList<SwimTime>> getTop5SwimmersPerDiscipline() {
        ArrayList<ArrayList<SwimTime>> top5Swimmers = new ArrayList<>();

        for (Disciplin disciplin : Disciplin.values()) {
            ArrayList<SwimTime> swimTimesForDiscipline = new ArrayList<>();

            for (SwimTime swimTime : swimTimeList) {
                if (swimTime.getDisciplin() == disciplin) {
                    swimTimesForDiscipline.add(swimTime);
                }
            }

            swimTimesForDiscipline.sort(Comparator.comparingDouble(SwimTime::getTime));

            ArrayList<SwimTime> top5 = new ArrayList<>();
            int count = 0;
            for (SwimTime swimTime : swimTimesForDiscipline) {
                if (count < 5) {
                    top5.add(swimTime);
                    count++;
                } else {
                    break;
                }
            }

            top5Swimmers.add(top5);
        }

        return top5Swimmers;
    }
}




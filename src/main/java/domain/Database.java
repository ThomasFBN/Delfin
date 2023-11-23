package domain;

import datasource.FileHandler;

import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
    private ArrayList<Swimmer> swimmerList;
    private FileHandler filehandler = new FileHandler("SwimmerMembers.csv");

    public Database() {
        swimmerList = new ArrayList<>();
    }

    public void createSwimmer(String name, String address, String phonenumber, String mail, LocalDate birthday, boolean isActive, boolean isJunior, boolean isCompetitor) {
        swimmerList.add(new Swimmer(name, address, phonenumber, mail, birthday, isActive, isJunior, isCompetitor));

    }

    public void saveSwimmer() {
        filehandler.saveSwimmer(swimmerList);
    }

    public void loadSwimmer() {
        swimmerList = filehandler.loadSwimmer();
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
}




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
}




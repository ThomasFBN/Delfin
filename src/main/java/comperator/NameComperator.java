package comperator;

import domain.Swimmer;

import java.util.Comparator;

public class NameComperator implements Comparator<Swimmer> {
    public int compare(Swimmer d1, Swimmer d2) {
        return d1.getName().compareTo(d2.getName());
    }
}

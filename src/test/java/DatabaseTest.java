import comperator.NameComperator;
import domain.Swimmer;

import domain.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    private Database db;

    @BeforeEach
    public void setUp(){
        db = new Database();
        db.createSwimmer("Ejner", "Hellerup", " 12345678", "ejner@gmail.com", LocalDate.parse(("2000-11-22")), true,true);
        db.createSwimmer("Oliver", "Nørrebro", " 12345678", "oliver@gmail.com", LocalDate.parse(("2000-11-22")), true,false);
        db.createSwimmer("Thomas", "Hellerup", " 12345678", "ejner@gmail.com", LocalDate.parse(("2000-11-22")), false,true);
    }
    @Test
    public void addSwimmerTest(){
        int expected = db.getSwimmerList().size() + 1;
        db.createSwimmer("August", "Hellerup", " 12345678", "ejner@gmail.com", LocalDate.parse("2000-11-03"), true,true);
        int actual = db.getSwimmerList().size();
       assertEquals(expected, actual);
    }

    @Test
public void addFlereSwimmerTest(){
        int expected = db.getSwimmerList().size() + 3;
        db.createSwimmer("Dennis", "Hellerup", " 12345678", "ejner@gmail.com", LocalDate.parse(("2000-11-22")), true,true);
        db.createSwimmer("Joakim", "Nørrebro", " 12345678", "oliver@gmail.com", LocalDate.parse(("2000-11-22")), true,false);
        db.createSwimmer("Bo", "Hellerup", " 12345678", "ejner@gmail.com", LocalDate.parse(("2000-11-22")), false,true);
        int actual = db.getSwimmerList().size();
        assertEquals(expected, actual);

    }

    @Test
    public void searchForIngen(){
        ArrayList<Swimmer> expected = new ArrayList<>(1);
        ArrayList<Swimmer> actual = db.search("Dennis");
        assertEquals(expected, actual);
    }
    @Test
    public void searchForSwimmer(){
        ArrayList<Swimmer> results = db.search("Ejner");
        int expected = 1;
        int actual = results.size();

        assertEquals(expected, actual);
    }
    @Test
    public void searchForFlereSwimmer(){
        ArrayList<Swimmer> results = db.search("E");
        int expected = 2;
        int actual = results.size();

        assertEquals(expected, actual);
    }
    @Test
    public void deleteTest(){
        int expected = db.getSwimmerList().size() - 1;
        db.deleteSwimmer("Oliver");
        int actual = db.getSwimmerList().size();
        assertEquals(expected, actual);
    }
    @Test
    public void testSortering() {

        ArrayList<Swimmer> sortedSuperheroes = db.getSwimmerList();

        Comparator<Swimmer> testComperator = new NameComperator();
        Collections.sort(sortedSuperheroes, testComperator);

        assertEquals(sortedSuperheroes, db.getSwimmerList());
    }
}


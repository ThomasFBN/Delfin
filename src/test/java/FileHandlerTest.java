import datasource.FileHandler;
import domain.Database;

import domain.SwimTime;
import domain.Swimmer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class FileHandlerTest {
    private Database database;
    private FileHandler fileHandler;
    private ArrayList<Swimmer> swimmer;
    private ArrayList<SwimTime> swimTime;

    @BeforeEach
    public void setUp() {
        swimmer = new ArrayList();
        swimTime = new ArrayList();
        database = new Database();
    }

    @Test
    public void LoadSwimmer() throws IOException {
        fileHandler = new FileHandler("SwimmerMembersTest.csv");

        swimmer = fileHandler.loadSwimmer();

        assertEquals(5, swimmer.size());

    }
    @Test
    public void LoadSwimmerTime() throws IOException {
        fileHandler = new FileHandler("SwimmerTimesTest.csv");
        swimTime = fileHandler.loadSwimTime();

        assertEquals(6, swimTime.size());

    }
}
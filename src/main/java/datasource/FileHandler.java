package datasource;

import domain.Swimmer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private File file = null;

    public FileHandler(String fileName) {
        file = new File(fileName);
    }

    public void saveSwimmer(ArrayList<Swimmer> swimmerList) {
        PrintStream output = null;
        try {
            output = new PrintStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (Swimmer swimmer : swimmerList) {
            output.println(swimmer.getName() + ";" +
                    swimmer.getAddress() + ";"+
                    swimmer.getPhoneNumber() + ";" +
                    swimmer.getMail() + ";" +
                    swimmer.getBirthday() + ";" +
                    swimmer.getIsActive()+ ";" +
                    swimmer.getIsJunior()+ ";" +
                    swimmer.getIsCompetitor());


        }


        System.out.println("List saved");
    }

    public ArrayList<Swimmer> loadSwimmer() {
        ArrayList<Swimmer> swimmerData = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(file, StandardCharsets.ISO_8859_1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] attributes = line.split(";");

            String name = attributes[0].trim();
            String address = attributes[1].trim();
            String phoneNumber = attributes[2];
            String mail = attributes[3];
            LocalDate birthday = LocalDate.parse(attributes[4]);
            boolean isActive = Boolean.parseBoolean(attributes[5]);
            boolean isJunior = Boolean.parseBoolean(attributes[6]);
            boolean isCompetitor = Boolean.parseBoolean(attributes[7]);



            Swimmer swimmer = new Swimmer(name, address, phoneNumber, mail, birthday, isActive, isJunior, isCompetitor);
            swimmerData.add(swimmer);

        }
        sc.close();
        return swimmerData;
    }

}



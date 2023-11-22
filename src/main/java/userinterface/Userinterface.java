package userinterface;
import domain.Controller;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;
public class Userinterface {
    Controller controller;
    Scanner scanner;

    public void startProgram() throws FileNotFoundException {
        scanner = new Scanner(System.in);
        controller = new Controller();
        controller.loadSwimmer();
        int menuValg = 0;

        while (menuValg != 9) {
            System.out.println("Velkommen til svømmeklubben Delfinen!" +
                    "\nTast 1 for at oprette et medlem til klubben" +
                    "\nTast 2 for at søge efter et medlem i klubben." +
                    "\nTast 3 for at se resultater over konkurrence resultater." +
                    "\nTast 4 for at se resultater over trænings resultater." +
                    "\nTast 5 for at få oversigt over top 5 svømmere i hver svømmedisciplin." +
                    "\nTast 6 for at få oversigt over forventede indbetalte kontigenter på et år." +
                    "\nTast 7 for at få oversigt over manglende betalinger til kontigenter." +
                    "\nTast 8 for at få udmelde et given medlem." +
                    "\nTast 9 for at rette i et given medlems informationer." +
                    "\nTast 10 for at afslutte")

            menuValg = scanner.nextInt();
            scanner.nextLine();
        }

        switch (menuValg) {
            case 1: {
                createSwimmer();
                break;
            }
            case 2: {

            }
            default: {

            }
        }
    }
        public void createSwimmer() {
            System.out.println("Indtast navnet på det medlem du vil oprette: ");
            String name = scanner.nextLine();
            System.out.println("Indtast adressen");
            String address = scanner.nextLine();
            System.out.println("Indtast telefon nummer");
            String phonenumber = scanner.nextLine();
            System.out.println("Indtast mail");
            String mail = scanner.next();
            System.out.println("Indtast fødselsdagdato");
            LocalDate birthday = LocalDate.parse(scanner.nextLine());
            boolean isActive = true;
            char medlem;
            do {
                System.out.println("Er medlemmet aktivt (j/n): ");

                medlem = scanner.next().charAt(0);

                if (medlem == 'j') {
                    isActive = true;
                } else if (medlem == 'n') {
                    isActive = false;
                } else {
                    System.out.println("Ugyldigt input, indtast j/n");
                }
            } while (medlem != 'j' && medlem != 'n');
            boolean isJunior = true;
            do {
                System.out.println("Er medlemmet Junior (j/n): ");

                medlem = scanner.next().charAt(0);

                if (medlem == 'j') {
                    isJunior = true;
                } else if (medlem == 'n') {
                    isJunior = false;
                } else {
                    System.out.println("Ugyldigt input, indtast j/n");
                }
            } while (medlem != 'j' && medlem != 'n');
            boolean isCompetitor = true;
            do {
                System.out.println("Er medlemmet konkurrence svømmer (j/n): ");

                medlem = scanner.next().charAt(0);

                if (medlem == 'j') {
                    isCompetitor = true;
                } else if (medlem == 'n') {
                    isCompetitor = false;
                } else {
                    System.out.println("Ugyldigt input, indtast j/n");
                }
            } while (medlem != 'j' && medlem != 'n');
           



            controller.addSwimmer(name, address, phonenumber, mail, birthday, isActive, isJunior, isCompetitor);
        }
    }


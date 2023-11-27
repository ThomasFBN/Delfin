package userinterface;

import domain.Controller;
import domain.Swimmer;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Userinterface {
    Controller controller;
    Scanner scanner;

    public void startProgram() throws FileNotFoundException {
        scanner = new Scanner(System.in);
        controller = new Controller();
        controller.loadSwimmer();
        int menuValg = 0;

        while (menuValg != 10) {
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
                    "\nTast 10 for at afslutte");

            menuValg = scanner.nextInt();
            scanner.nextLine();

            switch (menuValg) {
                case 1: {
                    createSwimmer();
                    break;
                }
                case 2: {
                    searchForSwimmer();

                    break;
                }
                case 3: {
                    handleSwimmerCategory();
                    break;
                }
                case 6: {
                    calculateExpectedMembershipFeesForAll();
                    break;
                }
                case 10: {
                    saveSwimmer();
                    saveSwimTime();
                    System.out.println("System exit");
                }
                default: {

                }
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
        String mail = scanner.nextLine();
        System.out.println("Indtast fødselsdagdato i formatet DD-MM-ÅÅÅÅ :");
        String birthdayInput = scanner.nextLine();

        String[] parts = birthdayInput.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        LocalDate birthday = LocalDate.of(year, month, day);
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

    public void saveSwimmer() {
        controller.saveSwimmer();
    }
    
    public void saveSwimTime(){
        controller.saveSwimTime();
    }

    public void searchForSwimmer() {

        System.out.println("Indtast navnet på medlemmet, du vil søge efter: ");
        String swimmerName = scanner.nextLine();
        ArrayList<Swimmer> søgeResultat = controller.search(swimmerName);

        if (søgeResultat != null) {
            System.out.println("medlemmet fundet:");
            System.out.println(søgeResultat);
        } else {
            System.out.println("Medlemmet med navnet '" + swimmerName + "' blev ikke fundet.");
        }

    }

    public void printActiveMembers() {
        ArrayList<Swimmer> activeMembers = controller.activeSwimmers();

        if (!activeMembers.isEmpty()) {
            System.out.println("Aktive medlemmer:");
            for (Swimmer swimmer : activeMembers) {
                System.out.println(swimmer.toString());
            }
        } else {
            System.out.println("Ingen aktive medlemmer fundet.");
        }
    }

    public void printPassiveMembers() {
        ArrayList<Swimmer> passiveMembers = controller.passiveSwimmers();

        if (!passiveMembers.isEmpty()) {
            System.out.println("Passive medlemmer:");
            for (Swimmer swimmer : passiveMembers) {
                System.out.println(swimmer.toString());
            }
        } else {
            System.out.println("Ingen aktive medlemmer fundet.");
        }
    }

    public void printjuniorMembers() {
        ArrayList<Swimmer> juniorMembers = controller.juniorMembers();

        if (!juniorMembers.isEmpty()) {
            System.out.println("Junior medlemmer:");
            for (Swimmer swimmer : juniorMembers) {
                System.out.println(swimmer.toString());
            }
        } else {
            System.out.println("Ingen junior medlemmer fundet.");
        }
    }

    public void printSeniorMembers() {
        ArrayList<Swimmer> seniorMembers = controller.seniorMembers();

        if (!seniorMembers.isEmpty()) {
            System.out.println("Senior medlemmer:");
            for (Swimmer swimmer : seniorMembers) {
                System.out.println(swimmer.toString());
            }
        } else {
            System.out.println("Ingen Senior medlemmer fundet.");
        }
    }

    public void printCompetetiveMembers() {
        ArrayList<Swimmer> competitiveMembers = controller.competitiveMembers();

        if (!competitiveMembers.isEmpty()) {
            System.out.println("Konkurrence medlemmer:");
            for (Swimmer swimmer : competitiveMembers) {
                System.out.println(swimmer.toString());
            }
        } else {
            System.out.println("Ingen competetive medlemmer fundet: ");
        }
    }

    public void printRegularMembers() {
        ArrayList<Swimmer> regularMember = controller.regularMembers();

        if (!regularMember.isEmpty()) {
            System.out.println("Regular medlemmer:");
            for (Swimmer swimmer : regularMember) {
                System.out.println(swimmer.toString());
            }
        } else {
            System.out.println("Ingen regular medlemmer fundet.");
        }
    }

    public void handleSwimmerCategory() {
        int categoryChoice = 0;
        do {
            System.out.println("Vælg en kategori: " +
                    "\n1. Aktive medlemmer" +
                    "\n2. Passive medlemmer" +
                    "\n3. Junior medlemmer" +
                    "\n4. Senior medlemmer" +
                    "\n5. Konkurrence medlemmer" +
                    "\n6. Regular medlemmer" +
                    "\nIndtast dit valg (1-6): ");

            categoryChoice = scanner.nextInt();
            scanner.nextLine();
            switch (categoryChoice) {
                case 1:
                    printActiveMembers();
                    break;
                case 2:
                    printPassiveMembers();
                    break;
                case 3:
                    printjuniorMembers();
                    break;
                case 4:
                    printSeniorMembers();
                    break;
                case 5:
                    printCompetetiveMembers();
                    break;
                case 6:
                    printRegularMembers();
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        } while (categoryChoice < 1 || categoryChoice > 6);
    }

    public void calculateExpectedMembershipFeesForAll() {
        double totalExpectedFees = controller.calculateExpectedMembershipFeesForAll();
        System.out.println("Den samlede forventede indbetaling for alle medlemmer er: " + totalExpectedFees);

    }
}




package userinterface;

import domain.Controller;
import domain.Disciplin;
import domain.Swimmer;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Userinterface {
    Controller controller;
    Scanner scanner;

    public void startProgram() throws FileNotFoundException {
        scanner = new Scanner(System.in);
        controller = new Controller();
        controller.loadSwimmer();
        controller.loadSwimTime();
        int menuValg = 0;


        while (menuValg != 10) {
            System.out.println("Vælg din rolle: " +
                    "\n1. Formand" +
                    "\n2. Kasserer" +
                    "\n3. Træner" +
                    "\n4. Afslut");

            int roleChoice = scanner.nextInt();
            scanner.nextLine();

            switch (roleChoice) {
                case 1:
                    handleChairmanOptions();
                    break;
                case 2:
                    handleTreasurerOptions();
                    break;
                case 3:
                    handleCoachOptions();
                    break;
                case 4:
                    saveSwimmer();
                    saveSwimTime();
                    System.out.println("System exit");
                    menuValg = 10;
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        }
    }

    public void handleChairmanOptions() {
        int chairmanChoice = 0;
        while (chairmanChoice != 6) {
            System.out.println("Formand valgmuligheder: " +
                    "\n1. Opret et medlem til klubben" +
                    "\n2. Søg efter et medlem i klubben" +
                    "\n3. Se oversigt over medlemmere" +
                    "\n4. Udmeld et medlem" +
                    "\n5. Rediger medlems informationer" +
                    "\n6. Tilbage til hovedmenuen");

            chairmanChoice = scanner.nextInt();
            scanner.nextLine();

            switch (chairmanChoice) {
                case 1:
                    createSwimmer();
                    break;
                case 2:
                    searchForSwimmer();
                    break;
                case 3:
                    handleSwimmerCategory();
                    break;
                case 4:
                    deleteSwimmer();
                    break;
                case 5:
                    editMember();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        }
    }

    public void handleTreasurerOptions() {
        int treasurerChoice = 0;
        while (treasurerChoice != 3) {
            System.out.println("Kasserer valgmuligheder: " +
                    "\n1. Oversigt over forventede indbetalte kontingenter på et år" +
                    "\n2. Oversigt over manglende betalinger til kontingenter" +
                    "\n3. Tilbage til hovedmenuen");

            treasurerChoice = scanner.nextInt();
            scanner.nextLine();

            switch (treasurerChoice) {
                case 1:
                    calculateExpectedMembershipFeesForAll();
                    break;
                case 2:
                    missingPaymentMembers();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        }
    }

    public void handleCoachOptions() {
        int coachChoice = 0;
        while (coachChoice != 3) {
            System.out.println("Træner valgmuligheder: " +
                    "\n1. Opret nye trænings-/konkurrenceresultater" +
                    "\n2. Se medlemmere på hvert hold" +
                    "\n3 Tilbage til hovedmenuen");

            coachChoice = scanner.nextInt();
            scanner.nextLine();

            switch (coachChoice) {
                case 1:
                    createSwimTime();
                    break;
                case 2:
                    handleSwimmerCategory();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
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
        System.out.println("Indtast fødselsdagdato i formatet DD-MM-ÅÅÅÅ:");
        LocalDate birthday = validBirthdayInput();
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
        boolean isJunior = calculateIsJunior(birthday);

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

    public void createSwimTime() {
        System.out.println("Indtast medlemmet: ");
        String member = scanner.nextLine();
        System.out.println("Indtast medlemmets tid: ");
        double time = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Indtast datoen: ");
        LocalDate date = validBirthdayInput();
        boolean competition = true;
        String tid;
        do {
            System.out.println("Er tiden til konkurrence eller træning?");
            tid = scanner.nextLine().toLowerCase();

            if (tid.equals("konkurrence")) {
                competition = true;
            } else if (tid.equals("træning")) {
                competition = false;
            } else {
                System.out.println("Ugyldigt input, indtast konkurrence eller træning");
            }
        } while (!tid.equals("konkurrence") && !tid.equals("træning"));


        Disciplin disciplin = getValidDiscipline();
        System.out.println("Indtast placering");
        String placement = scanner.nextLine();

        controller.addSwimTime(member, time, date, competition, disciplin, placement);


    }

    public void saveSwimmer() {
        controller.saveSwimmer();
    }

    public void saveSwimTime() {
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

    public void printAllMembers() {
        ArrayList<Swimmer> allSwimmers = controller.allSwimmers();

        if (!allSwimmers.isEmpty()) {
            System.out.println("Alle medlemmer:");
            for (Swimmer swimmer : allSwimmers) {
                System.out.println(swimmer.toString());
            }
        } else {
            System.out.println("Ingen medlemmer fundet.");
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

    public void missingPaymentMembers() {
        ArrayList<Swimmer> missingPaymentMembers = controller.missingPaymentMembers();

        if (!missingPaymentMembers.isEmpty()) {
            System.out.println("Medlemmere som ikke har betalt:");
            for (Swimmer swimmer : missingPaymentMembers) {
                System.out.println(swimmer.toString());
            }
        } else {
            System.out.println("Ingen medlemmer fundet.");
        }
    }

    public void sortSwimmers() {
        controller.sortSwimmers();
    }

    public boolean calculateIsJunior(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthday, currentDate).getYears();
        return age < 18;
    }

    public void handleSwimmerCategory() {
        int categoryChoice = 0;
        do {
            System.out.println("Vælg en kategori: " +
                    "\n1. Alle medlemmer" +
                    "\n2. Alle medlemmer sorteret efter navn" +
                    "\n3. Alle medlemmer som ikke har betalt" +
                    "\n4. Aktive medlemmer" +
                    "\n5. Passive medlemmer" +
                    "\n6. Junior medlemmer" +
                    "\n7. Senior medlemmer" +
                    "\n8. Konkurrence medlemmer" +
                    "\n9. Regular medlemmer" +
                    "\nIndtast dit valg (1-9): ");

            categoryChoice = scanner.nextInt();
            scanner.nextLine();
            switch (categoryChoice) {
                case 1:
                    printAllMembers();
                    break;
                case 2:
                    sortSwimmers();
                    printAllMembers();
                    break;
                case 3:
                    missingPaymentMembers();
                    break;
                case 4:
                    printActiveMembers();
                    break;
                case 5:
                    printPassiveMembers();
                    break;
                case 6:
                    printjuniorMembers();
                    break;
                case 7:
                    printSeniorMembers();
                    break;
                case 8:
                    printCompetetiveMembers();
                    break;
                case 9:
                    printRegularMembers();
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
                    break;
            }
        } while (categoryChoice < 1 || categoryChoice > 9);
    }

    public LocalDate validBirthdayInput() {
        LocalDate localDate = null;
        boolean validInput = false;

        do {
            String input = scanner.nextLine();

            try {
                // Forsøger at parse inputtet til en LocalDate
                localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                validInput = true; // Hvis parsingen lykkes, er inputtet gyldigt
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldigt format. Indtast datoen i formatet DD-MM-ÅÅÅÅ.");
            }
        } while (!validInput);

        return localDate;
    }

    public Disciplin getValidDiscipline() {
        Disciplin selectedDiscipline = null;
        boolean validDiscipline = false;
        do {
            System.out.println("Indtast disciplin:");
            String disciplineInput = scanner.nextLine();
            try {
                selectedDiscipline = Disciplin.valueOf(disciplineInput.toUpperCase());
                validDiscipline = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig disciplin. Vælg venligst en gyldig disciplin fra listen.");
            }
        } while (!validDiscipline);
        return selectedDiscipline;
    }
    public void editMember() {
        System.out.println("Indtast navnet på medlemmet, du vil redigere: ");
        String memberName = scanner.nextLine();
        ArrayList<Swimmer> searchResult = controller.search(memberName);

        if (!searchResult.isEmpty()) {
            Swimmer swimmer = searchResult.get(0);

            System.out.println("Medlem fundet:");
            System.out.println(swimmer);
            System.out.println("Indtast nye oplysninger for medlemmet:");

            System.out.println("Indtast nyt navn: ");
            String newName = scanner.nextLine();
            System.out.println("Indtast ny adresse: ");
            String newAddress = scanner.nextLine();
            System.out.println("Indtast nyt telefonnummer: ");
            String newPhoneNumber = scanner.nextLine();
            System.out.println("Indtast ny e-mail: ");
            String newMail = scanner.nextLine();
            System.out.println("Indtast ny fødselsdag (DD-MM-ÅÅÅÅ): ");
            LocalDate newBirthday = validBirthdayInput();

            boolean newIsActive = true;
            char isActiveInput;
            do {
                System.out.println("Er medlemmet aktivt (j/n): ");
                isActiveInput = scanner.next().charAt(0);

                if (isActiveInput == 'j') {
                    newIsActive = true;
                } else if (isActiveInput == 'n') {
                    newIsActive = false;
                } else {
                    System.out.println("Ugyldigt input, indtast j/n");
                }
            } while (isActiveInput != 'j' && isActiveInput != 'n');

            boolean newIsJunior = calculateIsJunior(newBirthday);

            boolean newIsCompetitor = true;
            char isCompetitorInput;
            do {
                System.out.println("Er medlemmet konkurrencesvømmer (j/n): ");
                isCompetitorInput = scanner.next().charAt(0);

                if (isCompetitorInput == 'j') {
                    newIsCompetitor = true;
                } else if (isCompetitorInput == 'n') {
                    newIsCompetitor = false;
                } else {
                    System.out.println("Ugyldigt input, indtast j/n");
                }
            } while (isCompetitorInput != 'j' && isCompetitorInput != 'n');

            scanner.nextLine();

            controller.editMember(memberName, newName, newAddress, newPhoneNumber, newMail, newBirthday, newIsActive, newIsJunior, newIsCompetitor);
        } else {
            System.out.println("Medlemmet med navnet '" + memberName + "' blev ikke fundet.");
        }
    }
    public void deleteSwimmer() {
        System.out.println("Indtast navnet på medlemmet, du vil slette: ");
        String swimmerNameToDelete = scanner.nextLine();
        controller.deleteSwimmer(swimmerNameToDelete);
    }



    public void calculateExpectedMembershipFeesForAll() {
        double totalExpectedFees = controller.calculateExpectedMembershipFeesForAll();
        System.out.println("Den samlede forventede indbetaling for alle medlemmer er: " + totalExpectedFees + "kr");

    }
}





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
            System.out.println("Velkommen til svømmeklubben Delfinen!" +
                    "\nTast 1 for at oprette et medlem til klubben" +
                    "\nTast 2 for at søge efter et medlem i klubben." +
                    "\nTast 3 for at se oversigt over medlemmere." +
                    "\nTast 4 for at oprette nye træning/konkurrence resultater" +
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
                case 4: {
                    createSwimTime();
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
        String dateInput = scanner.nextLine();
        String[] parts = dateInput.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        LocalDate date = LocalDate.of(year, month, day);
        System.out.println("Indtast event");
        Event event = Event.valueOf(scanner.nextLine());
        System.out.println("Indtast disciplin");
        Disciplin disciplin = Disciplin.valueOf(scanner.nextLine());
        System.out.println("Indtast placering");
        String placement = scanner.nextLine();

        controller.addSwimTime(member, time, date, event, disciplin, placement);


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

            scanner.nextLine(); // Clear the buffer

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





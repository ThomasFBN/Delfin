package domain;

import java.time.LocalDate;

public class Swimmer {
    private String name;
    private String address;
    private String phoneNumber;
    private String mail;
    private LocalDate birthday;
    private boolean isActive;
    private boolean isJunior;
    private boolean isCompetitor;
    private double kontingent;

    public Swimmer(String name, String address, String phonenumber, String mail, LocalDate birthday, boolean isActive, boolean isJunior, boolean isCompetitor) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phonenumber;
        this.mail = mail;
        this.birthday = birthday;
        this.isActive = true;
        this.isJunior = isJunior;
        this.isCompetitor = isCompetitor;
        this.kontingent = kontingent;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }
    
    public LocalDate getBirthday() {
        return birthday;
    }
    
    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsJunior() {
        return isJunior;
    }

    public boolean getIsCompetitor() {
        return isCompetitor;
    }

    public double getKontingent() {
        return kontingent;
    }

    private double beregnKontingent() {
        if ("Aktiv".equals(isActive)) {
            if ("Junior".equals(birthday)) {
                return 1000.0;  // Kontingent for ungdomssvømmere (under 18 år)
            } else if ("Senior".equals(birthday)) {
                if ("Motionist".equals(isCompetitor)) {
                    return 1600.0;  // Kontingent for seniorsvømmere (18 år og over) - motionist
                } else {
                    return 1600.0 * 0.75;  // Rabat på 25% for seniorsvømmere (18 år og over) - konkurrencesvømmer
                }
            }
        } else if ("Passiv".equals(isActive)) {
            return 500.0;  // Kontingent for passivt medlemskab
        }

        return 0.0;  // Returnerer 0.0 for ukendt aktivitet/aldersgruppe
    }


    public String toString() {
        return "Medlem: " + "\n" +
                "Medlemmets navn: " + name + "\n" +
                "Medlemmets addresse: " + address + "\n" +
                "Medlemmets telefon nummer: " + phoneNumber + "\n" +
                "Medlemmets mail: " + mail + "\n" +
                "Medlemmets fødselsdagsdato: " + birthday + "\n" +
                "Er medlemmet aktivt/passiv: " + isActive + "\n" +
                "Er medlemmet junior/senior svømmer: " + isJunior + "\n" +
                "Er medlemmet motionist/konkurrence svømmer: " + isCompetitor;

}


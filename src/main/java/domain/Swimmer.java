package domain;

import java.time.LocalDate;
import java.time.Period;

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
        this.isActive = isActive;
        this.isJunior = calculateIsJunior(birthday);
        this.isCompetitor = isCompetitor;
        this.kontingent = kontingent;
    }

    public String getName() {
        return name;
    }

    public boolean calculateIsJunior(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthday, currentDate).getYears();
        return age < 18;
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

    public double calculateExpectedMembershipFees() {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthday, currentDate).getYears();

        if (isActive) {
            if (age < 18) {
                return 1000.0;  // Kontingent for ungdomssvømmere (under 18 år)
            } else if (age >= 60) {
                return 1600.0 * 0.75;  // Rabat på 25% for seniorsvømmere (60 år og derover)
            } else {
                return 1600.0;  // Kontingent for seniorsvømmere (18 år og derover)
            }
        } else {
            return 500.0;  // Kontingent for passivt medlemskab
        }
    }

}


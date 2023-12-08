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
    private boolean hasPaid;

    public Swimmer(String name, String address, String phonenumber, String mail, LocalDate birthday, boolean isActive, boolean isCompetitor) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phonenumber;
        this.mail = mail;
        this.birthday = birthday;
        this.isActive = isActive;
        this.isJunior = calculateIsJunior(birthday);
        this.isCompetitor = isCompetitor;
        this.kontingent = kontingent;
        this.hasPaid = true;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setCompetitor(boolean competitor) {
        isCompetitor = competitor;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJunior(boolean junior) {
        isJunior = junior;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public void setKontingent(double kontingent) {
        this.kontingent = kontingent;
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

    public boolean getHasPaid() {
        return hasPaid;
    }


    public String toString() {
        return "-------------------------------------------------\n"+
                "Medlem: " + "\n" +
                "Navn: " + name + "\n" +
                "Addresse: " + address + "\n" +
                "Telefon nummer: " + phoneNumber + "\n" +
                "Mail: " + mail + "\n" +
                "Fødselsdagsdato: " + birthday + "\n" +
                "Er medlemmet aktivt: " + isActive + "\n" +
                "Er medlemmet junior svømmer: " + isJunior + "\n" +
                "Er medlemmet konkurrence svømmer: " + isCompetitor;

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


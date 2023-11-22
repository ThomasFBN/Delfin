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

    public Swimmer(String name, String address, String phonenumber, String mail, LocalDate birthday, boolean isActive, boolean isJunior, boolean isMotionist) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phonenumber;
        this.mail = mail;
        this.birthday = birthday;
        this.isActive = true;
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



}


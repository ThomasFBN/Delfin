package domain;


import java.time.LocalDate;

public class SwimTime {
    private double time;
    private String member;
    private LocalDate date;
    private Disciplin disciplin;
    private String placement;
    private String training;
    private boolean competition;


    public SwimTime(String member, double time, LocalDate date, boolean competition, Disciplin disciplin, String placement) {
        this.time = time;
        this.member = member;
        this.date = date;
        this.disciplin = disciplin;
        this.placement = placement;
        this.competition = competition;
    }


    public double getTime() {
        return time;
    }

    public String getMember() {
        return member;
    }

    public Disciplin getDisciplin() {
        return disciplin;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPlacement() {
        return placement;
    }


    public boolean getCompetition() {
        return competition;
    }
}

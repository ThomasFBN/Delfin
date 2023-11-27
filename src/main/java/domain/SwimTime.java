package domain;


import java.time.LocalDate;

public class SwimTime {
    private double time;
    private String member;
    private LocalDate date;
    private Event event;
    private Disciplin disciplin;
    private String placement;

    public SwimTime(String member, double time, LocalDate date, Event event, Disciplin disciplin, String placement){
        this.time = time;
        this.member = member;
        this.date = date;
        this.event = event;
        this.disciplin = disciplin;
        this.placement = placement;
    }


    public double getTime() {
        return time;
    }
    public String getMember(){
        return member;
    }

    public Disciplin getDisciplin() {
        return disciplin;
    }

    public LocalDate getDate() {
        return date;
    }
    public Event getEvent() {
        return event;
    }

    public String getPlacement() {
        return placement;
    }

}

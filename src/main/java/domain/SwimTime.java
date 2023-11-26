package domain;


import jdk.jfr.Event;

import java.time.LocalDate;

public class SwimTime {
    private double time;
    private String member;
    private LocalDate date;
    private Event event;
    private String placement;

    public SwimTime(double time, String member, LocalDate date, Event event, String placement){
        this.time = time;
        this.member = member;
        this.date = date;
        this.event = event;
        this.placement = placement;
    }

    public double getTime() {
        return time;
    }
    public String getMember(){
        return member;
    }

    public Event getEvent() {
        return event;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPlacement() {
        return placement;
    }

}

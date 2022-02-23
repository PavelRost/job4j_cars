package ru.job4j.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date(System.currentTimeMillis());
    private String description;
    private String mark;
    private String body;
    private boolean done = false;
    private boolean photo = false;

    @OneToOne(fetch = FetchType.LAZY)
    private Car car;

    public static Advertisement of(String description, String mark, String body, Car car) {
        Advertisement advertisement = new Advertisement();
        advertisement.description = description;
        advertisement.mark = mark;
        advertisement.body = body;
        advertisement.car = car;
        return advertisement;
    }

    public boolean isPhoto() {
        return photo;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}

package ru.job4j.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "advertisement")
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Advertisement of(String description, String mark, String body, Car car, User user) {
        Advertisement advertisement = new Advertisement();
        advertisement.description = description;
        advertisement.mark = mark;
        advertisement.body = body;
        advertisement.car = car;
        advertisement.user = user;
        return advertisement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isPhoto() {
        return photo;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

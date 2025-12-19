package com.chrima_chris_game.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String giverPhone;

    @Column(nullable = false)
    private String receiverName;

    private boolean revealed = false;

    // constructors
    public Assignment() {}

    public Assignment(String giverPhone, String receiverName) {
        this.giverPhone = giverPhone;
        this.receiverName = receiverName;
    }

    // getters & setters
    public int getId() {
        return id;
    }

    public String getGiverPhone() {
        return giverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGiverPhone(String giverPhone) {
        this.giverPhone = giverPhone;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }
}

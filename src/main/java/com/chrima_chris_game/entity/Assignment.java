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
    private String receiverPhone;

    @Column(nullable = false)
    private String receiverName;

    @Column(columnDefinition = "TEXT")
    private String taskForReceiver;

    private boolean revealed = false;
    
    public Assignment() {
        // Required by JPA
    }


    public Assignment(String giverPhone, String receiverPhone, String receiverName) {
        this.giverPhone = giverPhone;
        this.receiverPhone = receiverPhone;
        this.receiverName = receiverName;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGiverPhone() {
		return giverPhone;
	}

	public void setGiverPhone(String giverPhone) {
		this.giverPhone = giverPhone;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getTaskForReceiver() {
		return taskForReceiver;
	}

	public void setTaskForReceiver(String taskForReceiver) {
		this.taskForReceiver = taskForReceiver;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

    
}

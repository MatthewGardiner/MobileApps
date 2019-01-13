package com.example.taskmanager3.sightmarks;

import java.util.Date;
import java.util.UUID;

public class Task {
	private UUID taskId;
	private String taskTitle;
	private String distance;
	private double sightmark;
	private int extension;
	
	public Task () {
		taskId = UUID.randomUUID();
	}
	
	public UUID getTaskId() {
		return taskId;
	}
	public void setTaskId(UUID taskId) {
		this.taskId = taskId;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
    public String getDistance() {
        return distance;
    }
    public void setDistance(String distance) {
        this.distance = distance;
    }
    public double getSightmark() {
		return sightmark;
	}

	public void setSightmark(double sightmark) {
		this.sightmark = sightmark;
	}

	public int getExtension() {
		return extension;
	}

	public void setExtension(int extension) {
		this.extension = extension;
	}
}

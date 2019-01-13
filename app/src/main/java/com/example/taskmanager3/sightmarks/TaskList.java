package com.example.taskmanager3.sightmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.Context;

public class TaskList {
	private static TaskList taskList;
	private static List<Task> tasks;
	
	public void addTask(Task t) {
		tasks.add(t);
}
	
	public static TaskList get (Context context) {
		if(taskList == null) {
			taskList = new TaskList(context);
		}
		return taskList;
	}

	
	private TaskList(Context context) {
		tasks = new ArrayList<Task>();
		
	}
	
	public Task getTask(UUID id) {
		for(Task task: tasks) {
			if(task.getTaskId().equals(id)) {
				return task;
			}
		}
		return null;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void updateTask(Task task) {
		int task_index = tasks.indexOf(task);
		tasks.set(task_index,task);
	}
	
}

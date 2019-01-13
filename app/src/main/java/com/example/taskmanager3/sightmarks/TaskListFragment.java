package com.example.taskmanager3.sightmarks;

import java.util.List;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taskmanager3.R;
import com.example.taskmanager3.sightmarks.Task;
import com.example.taskmanager3.sightmarks.TaskList;

public class TaskListFragment extends Fragment{
	private RecyclerView  taskRecyclerView;
	private TaskAdapter taskAdapter;
	
	/** Experimental code - from here
	 * 
	 */
    OnTaskSelectedListener taskCallback;
	
	// Container Activity must implement this interface
    public interface OnTaskSelectedListener {
        public void onTaskSelected(Task task);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
        	taskCallback = (OnTaskSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTaskSelectedListener");
        }
    }
    
    @Override
    public void onDetach() {
    	super.onDetach();
    	taskCallback = null;
    }
    /** Experimental code - to here
	 * 
	 */
	

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_item_new_task:
			Task task = new Task();
			TaskList.get(getActivity()).addTask(task);
			updateUI();
			taskCallback.onTaskSelected(task);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}

	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.task, menu);
	}

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		//we want to inflate the layout we have created for recyclerview, here you specify the name of the layout file
		View view = inflater.inflate(R.layout.fragment_task_list, container, false);

		
		//remember, we named our recycler view in the fragment_task_list file as task_recycler_view, 
				//we pass that to find view by id, similar to all the widget we have done so far.
		taskRecyclerView = (RecyclerView) view.findViewById(R.id.task_recycler_view);

		//remember, we discussed that RecyclerView is good at delegation. this is an example, where it delegates laying out the UI
				//to a layout manager. the layout manaager requires a context in which it needs to work, it will be the activity this 
				//fragment is part of..
		taskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		updateUI();
		return view;
	
	
	}
	
	
	public void updateUI() {
		
		//get the task list for this activity as a context
		TaskList taskList = TaskList.get(getActivity());
		List<Task> tasks = taskList.getTasks();
		
				//first time, it is loading
				if(taskAdapter == null) {
					taskAdapter = new TaskAdapter(tasks);
					taskRecyclerView.setAdapter(taskAdapter);
				} else {
					taskAdapter.setTasks(tasks);
					taskAdapter.notifyDataSetChanged();
				}
	}


	private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		public TextView titleTextView;
		public TextView sightmarkTextView;
		public TextView extensionTextView;
		private Task task;
		
		public TaskHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);

			titleTextView = (TextView) itemView.findViewById(R.id.list_item_distance);
			sightmarkTextView = (TextView) itemView.findViewById(R.id.list_item_sightmark);
			extensionTextView = (TextView) itemView.findViewById(R.id.list_item_extension);

		}
		
		public void bindTask (Task bindTask) {
			task = bindTask;
			titleTextView.setText(task.getTaskTitle());
			sightmarkTextView.setText(Double.toString(task.getSightmark()));
			extensionTextView.setText(Integer.toString(task.getExtension()));
		}

		
		@Override
		public void onClick(View v) {
			taskCallback.onTaskSelected(task);
		}

		
	}
	
	private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
		private List<Task> tasks;
		
		public TaskAdapter(List<Task> listOftasks) {
			tasks = listOftasks;
		}
		
		@Override
		public int getItemCount() {
			return tasks.size();
		}

		@Override
		public void onBindViewHolder(TaskHolder holder, int position) {
			Task task = tasks.get(position);
			holder.bindTask(task);
		}

		@Override
		public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			View view = layoutInflater.inflate(R.layout.list_item_tasks, parent, false);
			return new TaskHolder(view);
		}

        public void setTasks(List<Task> tasks) {
           this.tasks = tasks;
        }


    }
	
	public void onResume() {
		super.onResume();
		updateUI();
	}



	
	
}

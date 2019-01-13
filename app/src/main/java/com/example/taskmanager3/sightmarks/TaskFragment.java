package com.example.taskmanager3.sightmarks;

import java.util.UUID;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.taskmanager3.R;


public class TaskFragment extends Fragment{
	private Task task;
	private EditText taskTitleTextView;
	private EditText distanceTextView;
	private EditText sightmarkTextView;
	private EditText extensionTextView;
	private Button submitButton;

	public static final String EXTRA_TASK_ID = "extra_task_id";

	/** Experimental code - from here
	 * 
	 */
    OnTaskUpdatedListener taskCallback;
	
	// Container Activity must implement this interface
    public interface OnTaskUpdatedListener {
         void onTaskUpdated(Task task);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
        	taskCallback = (OnTaskUpdatedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTaskUpdatedListener");
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
	
    private void updateTask() {
    	TaskList.get(getActivity()).updateTask(task);
    	taskCallback.onTaskUpdated(task);
    }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//UUID taskId = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_TASK_ID);
		
		UUID taskId = (UUID) getArguments().getSerializable(EXTRA_TASK_ID);

		task =  TaskList.get(getActivity()).getTask(taskId);
	}

	public static TaskFragment newInstance (UUID taskId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_TASK_ID, taskId);
		
		TaskFragment fragment = new TaskFragment();
		fragment.setArguments(args);
		return fragment;
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_task, container, false);
		taskTitleTextView = (EditText) view.findViewById(R.id.task_title);
		taskTitleTextView.setText(task.getTaskTitle());

		taskTitleTextView.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				task.setTaskTitle(s.toString());
				updateTask();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});

		distanceTextView = (EditText) view.findViewById(R.id.task_title);
		distanceTextView.setText(task.getDistance());
		distanceTextView.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				task.setDistance(s.toString());
				updateTask();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		sightmarkTextView = (EditText) view.findViewById(R.id.sightmark_detail);
		sightmarkTextView.setText("");
		sightmarkTextView.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String sm = s.toString();
				task.setSightmark(Double.parseDouble(sm));
				updateTask();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		extensionTextView = (EditText) view.findViewById(R.id.extension_detail);
		extensionTextView.setText("");
		extensionTextView.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try{
					String sm = s.toString();
					task.setExtension(Integer.parseInt(sm));
					updateTask();
				}
				catch(NumberFormatException n){
					extensionTextView.setText("");
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});





		submitButton = (Button) view.findViewById(R.id.task_submit);
		submitButton.setText(R.string.submit_button);
		submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


		return view;
	}

	
}

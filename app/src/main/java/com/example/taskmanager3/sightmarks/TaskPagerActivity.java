package com.example.taskmanager3.sightmarks;

import java.util.List;
import java.util.UUID;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.taskmanager3.R;

public class TaskPagerActivity extends AppCompatActivity implements TaskFragment.OnTaskUpdatedListener{
    ViewPager taskViewPager;
    List<Task> tasks;
    
    public static Intent newIntent(Context packageContext, UUID taskId) {
        Intent intent = new Intent(packageContext, TaskPagerActivity.class);
        intent.putExtra(TaskFragment.EXTRA_TASK_ID, taskId);
        return intent;
    }

    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_pager);
		
		//from the layout file, using viewpager's id there
		taskViewPager = (ViewPager) findViewById(R.id.activity_task_view_pager);
		
		//you need to know all the tasks in order to page through them
		tasks = TaskList.get(this).getTasks();
		
		//your page viewer need to know how many items are there in the list and which one it is on at the moment (so it can do one less and one more for
		//swiping. for this it uses the setup provided by FragmentManager and FragmentStatePagerAdapter. See below.
			
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		taskViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			
			@Override
			public int getCount() {
				return tasks.size();
			}
			
			@Override
			public Fragment getItem(int pos) {
				Task task = tasks.get(pos);
				return TaskFragment.newInstance(task.getTaskId());
			}
		});
		
		UUID taskId = (UUID) getIntent().getSerializableExtra(TaskFragment.EXTRA_TASK_ID);
		for(int i=0;i<tasks.size();i++) {
			if(tasks.get(i).getTaskId().equals(taskId)) {
				taskViewPager.setCurrentItem(i);
				break;
			}
		}
		

	}


	@Override
	public void onTaskUpdated(Task task) {
		// TODO Auto-generated method stub
		
	}


	

}

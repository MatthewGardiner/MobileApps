package com.example.taskmanager3.sightmarks;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.taskmanager3.R;


public class TaskListActivity extends SimpleFragmentActivity implements TaskListFragment.OnTaskSelectedListener, TaskFragment.OnTaskUpdatedListener {

    @Override
    protected int getLayoutId() {
        return R.layout.list_detail_layout;
    }

    @Override
    protected Fragment createFragment() {
        return new TaskListFragment();
    }

    @Override
    public void onTaskSelected(Task task) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = TaskPagerActivity.newIntent(this, task.getTaskId());
            startActivity(intent);
        } else {
            Fragment newDetail = TaskFragment.newInstance(task.getTaskId());
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container, newDetail).commit();
        }
    }

    @Override
    public void onTaskUpdated(Task task) {
        TaskListFragment taskListFragment = (TaskListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        taskListFragment.updateUI();
    }

}

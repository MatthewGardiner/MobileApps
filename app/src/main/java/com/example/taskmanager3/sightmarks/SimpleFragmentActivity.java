package com.example.taskmanager3.sightmarks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.taskmanager3.R;

public abstract class SimpleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for mobile it is activity_task
        setContentView(getLayoutId());
		
		
		/*
		//for tablet it is activity_tablet_layout
		setContentView(R.layout.activity_tablet_layout);
		*/
        FragmentManager fm = getSupportFragmentManager();


        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {

            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

    }


    protected int getLayoutId() {
        return R.layout.activity_task;
    }

}

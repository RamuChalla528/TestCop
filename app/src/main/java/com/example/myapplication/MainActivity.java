package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private RelativeLayout mCLayout;
//    private FloatingActionButton mFab;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        // Get the widget reference from XML layout
        mCLayout = (RelativeLayout) findViewById(R.id.coordinator_layout);
//        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Define a layout for RecyclerView
        mLayoutManager = new GridLayoutManager(mActivity,1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize a new adapter for RecyclerView
        mAdapter = new UninstallAppsAdapter(
                mContext,
                new AppsManager(mContext).getInstalledPackages()
        );

        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        // Set a click listener for floating action button
    /*    mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recreate the adapter with installed apps list
                mAdapter = new UninstallAppsAdapter(
                        mContext,
                        new AppsManager(mContext).getInstalledPackages()
                );

                // Set the adapter for Recycler view
                // Refresh recycler view with updated data
                mRecyclerView.setAdapter(mAdapter);
            }
        });*/
    }
}

package com.example.becky.test;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ArrivalScreenActivity extends ActionBarActivity {
    // TODO Need to populate the contact list from the first tier contacts
    private RecyclerView.Adapter rvAdapter;

    private String[] contactsList = {"Mom", "Dad", "The Dog", "Sister", "Uncle Phil", "Uncle Heime", "Cousin James", "Aunt Susan"};
    private Button homescreenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival_screen);

        RecyclerView contactsView = (RecyclerView) findViewById(R.id.contactsView);
        homescreenBtn = (Button) findViewById(R.id.homescreenBtn);

        backToStartScreen();

        // use this setting to improve performance if you know that changes
        // in content to do change the layout size of the RecyclerView
        contactsView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager rvLayoutManager = new LinearLayoutManager(this);
        contactsView.setLayoutManager(rvLayoutManager);

        // specify an adaoter
        rvAdapter = new HSAdapter(contactsList);
        contactsView.setAdapter(rvAdapter);
    }

    /* Ends the timer for the trip, taking the user automatically to the arrival screen.
* */
    private void backToStartScreen() {
        homescreenBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}



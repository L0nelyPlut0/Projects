package com.example.workoutapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import com.example.workoutapp.WorkoutDetailFragment;



public class MainActivity extends AppCompatActivity implements WorkoutListFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    // decide what to do if item in list is clicked, if there is a fragment container in the layout (only tablet layout has this) then add the clicked fragment to the side of the screen
    // if not, the user is on a phone, so just open activity detail when the list item is clicked and display it in a separate activity
    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            // if there is a fragment container make a new workoutDetail fragment
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            // start a new transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // set the new workout fragment to the ID of what ever workout has been clicked
            details.setWorkout(id);
            // replaces the current frag container with the newly set fragment
            ft.replace(R.id.fragment_container, details);
            // adds a transition
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            // commits the transaction
            ft.commit();
            }
        else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int) id);
            startActivity(intent);
        }
    }
}
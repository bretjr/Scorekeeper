package com.example.scorekeeper;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    // variables to hold my scores
    private int score1;
    private int score2;

    // variables to hold TextView refs
    TextView score1TextView;
    TextView score2TextView;

    // variable to be used in onInstanceSaved
    static final String STATE_SCORE_1 = "Team 1 score";
    static final String STATE_SCORE_2 = "Team 2 score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate TextView variables
        score1TextView = findViewById(R.id.score_1);
        score2TextView = findViewById(R.id.score_2);

        if (savedInstanceState != null){
            score1 = savedInstanceState.getInt("STATE_SCORE_1");
            score2 = savedInstanceState.getInt("STATE_SCORE_2");
            // set the scores TextViews
            score1TextView.setText(String.valueOf(score1));
            score2TextView.setText(String.valueOf(score2));
        }
    }

    /**
     * method to handle the onClick for the score decrement buttons
     * @param view the button view clicked
     */
    public void decreaseScore(View view) {
        // get the ID of the button clicked
        int viewID = view.getId();
        switch (viewID) {
            // if team 1
            case R.id.decreaseTeam1:
                // decrement the team score and update TextView
                score1--;
                score1TextView.setText(String.valueOf(score1));
                break;
            // if team 2
            case R.id.decreaseTeam2:
                // decrement the team score and update TextView
                score2--;
                score2TextView.setText(String.valueOf(score2));
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // check if the correct item is clicked
        if (item.getItemId() == R.id.night_mode){
            // get the nightmode state of the app
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            // set the theme mode for the started activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                // recreate the activity for the theme change to take affect
                recreate();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // change the label of the menu based on the state of the app
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    /**
     * method to handle the onClick for the score increment buttons
     * @param view the button view clicked
     */
    public void increaseScore(View view) {
        // get the ID of the button clicked
        int viewID = view.getId();
        switch (viewID) {
            // if team 1
            case R.id.increaseTeam1:
                // decrement the team score and update TextView
                score1++;
                score1TextView.setText(String.valueOf(score1));
                break;
            // if team 2
            case R.id.increaseTeam2:
                // decrement the team score and update TextView
                score2++;
                score2TextView.setText(String.valueOf(score2));
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // save the scores
        outState.putInt("STATE_SCORE_1", score1);
        outState.putInt("STATE_SCORE_2", score2);
        super.onSaveInstanceState(outState);
    }
}

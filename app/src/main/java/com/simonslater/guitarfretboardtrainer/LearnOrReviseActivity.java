package com.simonslater.guitarfretboardtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LearnOrReviseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_or_revise);
    }

    public void beginnerMode( View v ) {
        Intent my_intent = new Intent( this, BeginnerStringSelectionActivity.class );
        startActivity( my_intent );
    }

    public void expertMode( View v ) {
        Intent my_intent = new Intent( this, MainActivity.class );
        startActivity( my_intent );
    }
}
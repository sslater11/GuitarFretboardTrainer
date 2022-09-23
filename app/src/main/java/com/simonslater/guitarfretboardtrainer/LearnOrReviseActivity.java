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
        //Flashcards deck = new Flashcards( guitar_strings, fret_numbers, question_mode, repititions );
        //Intent study_mode = new Intent( this, StudyModeActivity.class );
        //study_mode.putExtra( "flashcards", deck );

        //startActivity( study_mode );
        System.out.println("yay");
    }

    public void expertMode( View v ) {
        System.out.println( "woooo" );
        Intent study_mode = new Intent( this, MainActivity.class );
        startActivity( study_mode );
    }
}
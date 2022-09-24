package com.simonslater.guitarfretboardtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class IntermediateSingleStringSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_single_string_selection);
    }

    public void studySingleGuitarString( int guitar_string ) {
        ArrayList<Flashcards> all_study_decks = new ArrayList<Flashcards>();

        int fret_numbers[] = { 1,  2,  3,  4,
                               5,  6,  7,  8,
                               9, 10, 11, 12 };

        int repetitions = 3;
        int guitar_strings[] = { guitar_string };

        all_study_decks.add( new Flashcards(guitar_strings, fret_numbers, Flashcards.ASK_NOTE,        repetitions, true ) );
        all_study_decks.add( new Flashcards(guitar_strings, fret_numbers, Flashcards.ASK_FRET_NUMBER, repetitions, true ) );

        Intent study_mode = new Intent( this, StudyModeActivity.class );
        study_mode.putExtra( "flashcards", all_study_decks.get(0) );
        study_mode.putExtra( "all_study_decks", all_study_decks );
        study_mode.putExtra( "is_ordered_review_message_shown", false );

        startActivity( study_mode );
        finish();
    }

    public void StudySingleGuitarStringE( View v ) {
        studySingleGuitarString( GuitarString.INDEX_NOTE_E );
    }

    public void StudySingleGuitarStringA( View v ) {
        studySingleGuitarString( GuitarString.INDEX_NOTE_A );
    }

    public void StudySingleGuitarStringD( View v ) {
        studySingleGuitarString( GuitarString.INDEX_NOTE_D );
    }

    public void StudySingleGuitarStringG( View v ) {
        studySingleGuitarString( GuitarString.INDEX_NOTE_G );
    }

    public void StudySingleGuitarStringB( View v ) {
        studySingleGuitarString( GuitarString.INDEX_NOTE_B );
    }
}
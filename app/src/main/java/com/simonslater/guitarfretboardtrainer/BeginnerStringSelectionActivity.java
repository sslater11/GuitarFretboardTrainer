package com.simonslater.guitarfretboardtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class BeginnerStringSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner_string_selection);
    }

    public void StudyGuitarString( int guitar_string ) {
        ArrayList<Flashcards> all_study_decks = new ArrayList<Flashcards>();

        int fret_numbers_1[] = { 1,  2,  3,  4 };
        int fret_numbers_2[] = { 5,  6,  7,  8 };
        int fret_numbers_3[] = { 9, 10, 11, 12 };

        int fret_numbers_4[] = { 1,  2,  3,  4,
                                 5,  6,  7,  8,
                                 9, 10, 11, 12 };

        int repetitions = 3;

        int guitar_strings[] = { guitar_string };


        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_1, Flashcards.ASK_NOTE,        repetitions ) );
        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_1, Flashcards.ASK_FRET_NUMBER, repetitions ) );

        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_2, Flashcards.ASK_NOTE,        repetitions ) );
        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_2, Flashcards.ASK_FRET_NUMBER, repetitions ) );

        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_3, Flashcards.ASK_NOTE,        repetitions ) );
        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_3, Flashcards.ASK_FRET_NUMBER, repetitions ) );

        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_4, Flashcards.ASK_NOTE,        repetitions ) );
        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_4, Flashcards.ASK_FRET_NUMBER, repetitions ) );


    }

    public void StudyGuitarStringE( View v ) {
        StudyGuitarString( GuitarString.INDEX_NOTE_E );
    }

    public void StudyGuitarStringA( View v ) {
        StudyGuitarString( GuitarString.INDEX_NOTE_A );
    }

    public void StudyGuitarStringD( View v ) {
        StudyGuitarString( GuitarString.INDEX_NOTE_D );
    }

    public void StudyGuitarStringG( View v ) {
        StudyGuitarString( GuitarString.INDEX_NOTE_G );
    }

    public void StudyGuitarStringB( View v ) {
        StudyGuitarString( GuitarString.INDEX_NOTE_B );
    }
}
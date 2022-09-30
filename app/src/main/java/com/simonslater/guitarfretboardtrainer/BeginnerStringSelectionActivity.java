/* © Copyright 2022, Simon Slater

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, version 2 of the License.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package com.simonslater.guitarfretboardtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class BeginnerStringSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner_string_selection);
    }

    public void studyGuitarString( int guitar_string ) {
        ArrayList<Flashcards> all_study_decks = new ArrayList<Flashcards>();
        ArrayList<Flashcards> all_study_decks_in_order = new ArrayList<Flashcards>();

        int fret_numbers_1[] = { 1,  2,  3,  4 };
        int fret_numbers_2[] = { 5,  6,  7,  8 };
        int fret_numbers_3[] = { 9, 10, 11, 12 };

        int fret_numbers_4[] = { 1,  2,  3,  4,
                                 5,  6,  7,  8,
                                 9, 10, 11, 12 };

        int repetitions = 3;
        int ordered_repetitions = 2;

        int guitar_strings[] = { guitar_string };


        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_1, Flashcards.ASK_NOTE,        repetitions, true ) );
        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_1, Flashcards.ASK_FRET_NUMBER, repetitions, true ) );

        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_2, Flashcards.ASK_NOTE,        repetitions, true ) );
        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_2, Flashcards.ASK_FRET_NUMBER, repetitions, true ) );

        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_3, Flashcards.ASK_NOTE,        repetitions, true ) );
        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_3, Flashcards.ASK_FRET_NUMBER, repetitions, true ) );

        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_4, Flashcards.ASK_NOTE,        repetitions, true ) );
        all_study_decks.add ( new Flashcards( guitar_strings, fret_numbers_4, Flashcards.ASK_FRET_NUMBER, repetitions, true ) );

        all_study_decks_in_order.add ( new Flashcards( guitar_strings, fret_numbers_1, Flashcards.ASK_NOTE,        ordered_repetitions, false ) );
        all_study_decks_in_order.add ( new Flashcards( guitar_strings, fret_numbers_1, Flashcards.ASK_FRET_NUMBER, ordered_repetitions, false ) );

        all_study_decks_in_order.add ( new Flashcards( guitar_strings, fret_numbers_2, Flashcards.ASK_NOTE,        ordered_repetitions, false ) );
        all_study_decks_in_order.add ( new Flashcards( guitar_strings, fret_numbers_2, Flashcards.ASK_FRET_NUMBER, ordered_repetitions, false ) );

        all_study_decks_in_order.add ( new Flashcards( guitar_strings, fret_numbers_3, Flashcards.ASK_NOTE,        ordered_repetitions, false ) );
        all_study_decks_in_order.add ( new Flashcards( guitar_strings, fret_numbers_3, Flashcards.ASK_FRET_NUMBER, ordered_repetitions, false ) );

        Intent intent = new Intent( this, ChromaticScaleActivity.class );
        intent.putExtra( "all_study_decks", all_study_decks );
        intent.putExtra( "all_study_decks_in_order", all_study_decks_in_order );

        startActivity( intent );
        finish();

    }

    public void studyGuitarStringE( View v ) {
        studyGuitarString( GuitarString.INDEX_NOTE_E );
    }

    public void studyGuitarStringA( View v ) {
        studyGuitarString( GuitarString.INDEX_NOTE_A );
    }

    public void studyGuitarStringD( View v ) {
        studyGuitarString( GuitarString.INDEX_NOTE_D );
    }

    public void studyGuitarStringG( View v ) {
        studyGuitarString( GuitarString.INDEX_NOTE_G );
    }

    public void studyGuitarStringB( View v ) {
        studyGuitarString( GuitarString.INDEX_NOTE_B );
    }
}

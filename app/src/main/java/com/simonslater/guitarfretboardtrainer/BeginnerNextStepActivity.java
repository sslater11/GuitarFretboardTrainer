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
import android.widget.TextView;

import java.util.ArrayList;

public class BeginnerNextStepActivity extends AppCompatActivity {
    ArrayList<Flashcards> all_study_decks;
    ArrayList<Flashcards> all_study_decks_in_order;
    String str_total_cards_answered_correctly;
    String str_total_cards_answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner_next_step);


        // Load the remaining decks passed from previous study sessions.
        Intent intent = getIntent();

        all_study_decks = (ArrayList<Flashcards>) intent.getSerializableExtra( "all_study_decks");
        all_study_decks_in_order = (ArrayList<Flashcards>) intent.getSerializableExtra( "all_study_decks_in_order");
        str_total_cards_answered_correctly = (String) intent.getSerializableExtra( "total_cards_answered_correctly");
        str_total_cards_answered = (String) intent.getSerializableExtra( "total_cards_answered");


        TextView txt_current_string= findViewById( R.id.txt_current_string );
        TextView txt_beginner_explanation= findViewById( R.id.txt_beginner_explanation );
        txt_current_string.setText( getString(R.string.txt_guitar_string_heading) + ": " + all_study_decks.get(0).getCard().getGuitarStringName() );

        // Hide the text telling the user they'll practice the notes in order first.
        // We hide this when they're about to review the whole string.
        if( all_study_decks_in_order == null || all_study_decks_in_order.size() == 0) {
            txt_beginner_explanation.setText( "" );
        }

        if( all_study_decks.size() > 0 ) {
            TextView txt_learn_frets= findViewById( R.id.txt_learn_frets );
            TextView txt_guess= findViewById( R.id.txt_guess );
            int fret_numbers[] = all_study_decks.get(0).getAllFretNumbers();

            if (fret_numbers.length == 4) {
                if (fret_numbers[0] == 1) {
                    txt_learn_frets.setText( getString(R.string.txt_learn_frets) + " 1 - 4" );
                    txt_beginner_explanation.setText( getString(R.string.txt_beginner_explanation_start) + " (1 2 3 4)" + getString(R.string.txt_beginner_explanation_end) );
                } else if (fret_numbers[0] == 5) {
                    txt_learn_frets.setText( getString(R.string.txt_learn_frets) + " 5 - 8" );
                    txt_beginner_explanation.setText( getString(R.string.txt_beginner_explanation_start) + " (5 6 7 8)" + getString(R.string.txt_beginner_explanation_end) );
                } else if (fret_numbers[0] == 9) {
                    txt_learn_frets.setText( getString(R.string.txt_learn_frets) + " 9 - 12" );
                    txt_beginner_explanation.setText( getString(R.string.txt_beginner_explanation_start) + " (9 10 11 12)" + getString(R.string.txt_beginner_explanation_end) );
                }
            } else if (fret_numbers.length == 12) {
                if( all_study_decks.size() == 1 ) {
                    txt_learn_frets.setText( getString(R.string.txt_last_stage) );
                } else {
                    txt_learn_frets.setText( getString(R.string.txt_study_all_notes) );
                }
            }

            if( all_study_decks.get(0).getQuestionMode() == Flashcards.ASK_FRET_NUMBER ) {
                txt_guess.setText( getString(R.string.txt_guess_fret_number) );

            } else if( all_study_decks.get(0).getQuestionMode() == Flashcards.ASK_NOTE ) {
                txt_guess.setText( getString(R.string.txt_guess_note) );
            }
        }
    }


    public void letsGo( View v ) {
        Intent study_mode = new Intent( this, StudyModeActivity.class );
        study_mode.putExtra( "flashcards", all_study_decks.get(0) );
        study_mode.putExtra( "all_study_decks", all_study_decks );
        study_mode.putExtra( "all_study_decks_in_order", all_study_decks_in_order );

        if(str_total_cards_answered != null ) {
            study_mode.putExtra( "total_cards_answered", str_total_cards_answered );
        }
        if( str_total_cards_answered_correctly != null ) {
            study_mode.putExtra( "total_cards_answered_correctly", str_total_cards_answered_correctly );
        }

        startActivity( study_mode );
        finish();
    }
}

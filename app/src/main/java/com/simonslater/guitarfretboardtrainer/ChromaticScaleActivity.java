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

public class ChromaticScaleActivity extends AppCompatActivity {

    ArrayList<Flashcards> all_study_decks;
    ArrayList<Flashcards> all_study_decks_in_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chromatic_scale);

        // Get the decks, ready to pass on when the user presses the button.
        Intent intent = getIntent();
        all_study_decks = (ArrayList<Flashcards>) intent.getSerializableExtra( "all_study_decks");
        all_study_decks_in_order = (ArrayList<Flashcards>) intent.getSerializableExtra( "all_study_decks_in_order");
    }

    public void startStudying( View v ) {
        // pass on the decks to the next activity.
        Intent beginner_next_step = new Intent( this, BeginnerNextStepActivity.class );
        beginner_next_step.putExtra( "all_study_decks", all_study_decks );
        beginner_next_step.putExtra( "all_study_decks_in_order", all_study_decks_in_order );

        startActivity( beginner_next_step );
        finish();

    }
}

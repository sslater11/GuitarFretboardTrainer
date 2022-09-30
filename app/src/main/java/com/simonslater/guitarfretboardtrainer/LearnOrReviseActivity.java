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

    public void intermediateMode( View v ) {
        Intent my_intent = new Intent( this, IntermediateSingleStringSelection.class );
        startActivity( my_intent );
    }

    public void expertMode( View v ) {
        Intent my_intent = new Intent( this, MainActivity.class );
        startActivity( my_intent );
    }
}

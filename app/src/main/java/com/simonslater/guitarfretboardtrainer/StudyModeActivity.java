/* © Copyright 2022, Simon Slater

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, version 2 of the License.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package com.simonslater.guitarfretboardtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudyModeActivity extends AppCompatActivity {
	private boolean is_answer_visible = false;
	private int ANSWER_FEEDBACK_DELAY = 300;
	private Flashcards deck;
	private Flashcards deck_in_order;
	private ArrayList<Flashcards> all_study_decks;
	private ArrayList<Flashcards> all_study_decks_in_order;
	private TextView txt_remaining_notes;
	private TextView txt_guitar_string;
	private TextView txt_fret_number;
	private TextView txt_note;
	private TextView txt_hints;

	private Button btn_show_answer;
	private Button btn_right_answer;
	private Button btn_wrong_answer;

	private ImageView img_answer_feedback;
	private Thread answer_feedback_thread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_study_mode);

		txt_remaining_notes = findViewById( R.id.txt_study_mode_remaining_notes );
		txt_guitar_string   = findViewById( R.id.txt_study_mode_guitar_string );
		txt_fret_number     = findViewById( R.id.txt_study_mode_fret );
		txt_note            = findViewById( R.id.txt_study_mode_note );
		txt_hints           = findViewById( R.id.txt_hints );

		btn_show_answer  = findViewById( R.id.btn_show_answer  );
		btn_right_answer = findViewById( R.id.btn_right_answer );
		btn_wrong_answer = findViewById( R.id.btn_wrong_answer );

		btn_right_answer.setEnabled( false );
		btn_wrong_answer.setEnabled( false );

		img_answer_feedback = findViewById( R.id.img_answer_feedback );
		img_answer_feedback.setImageResource( R.drawable.empty );

		Intent intent = getIntent();

		all_study_decks = (ArrayList<Flashcards>) intent.getSerializableExtra( "all_study_decks" );
		all_study_decks_in_order = (ArrayList<Flashcards>) intent.getSerializableExtra( "all_study_decks_in_order" );
		String str_total_cards_answered_correctly = (String) intent.getSerializableExtra( "total_cards_answered_correctly");
		String str_total_cards_answered = (String) intent.getSerializableExtra( "total_cards_answered");


		deck = (Flashcards) intent.getSerializableExtra( "flashcards");
		if( all_study_decks_in_order != null ) {
			if( all_study_decks_in_order.size() > 0 ) {
				deck_in_order = all_study_decks_in_order.get(0);
			} else {
				deck_in_order = null;
			}
		} else {
			deck_in_order = null;
		}
		if( str_total_cards_answered != null ) {
			int num = Integer.parseInt( str_total_cards_answered );
			deck.setTotalCardsAnswered( num );
		}
		if( str_total_cards_answered_correctly != null ) {
			deck.setTotalCardsAnsweredCorrectly( Integer.parseInt( str_total_cards_answered_correctly) );
		}

		displayQuestion();
	}
	public void displayQuestion() {
		is_answer_visible = false;
		txt_hints.setVisibility(View.INVISIBLE);

		// Show the ordered deck first, it's used to show the user the notes on the
		// fretboard in order, so they can get the notes into their head.
		if( all_study_decks_in_order != null
		 && all_study_decks_in_order.size() > 0
		 && all_study_decks_in_order.get(0).hasNext() )
		{
			int total_remaining = deck.size() + deck_in_order.size();
			txt_remaining_notes.setText( getString(R.string.txt_remaining_notes) + total_remaining );
			if (deck_in_order.hasNext()) {
				switch (deck_in_order.getQuestionMode()) {
					case Flashcards.ASK_GUITAR_STRING:
						txt_guitar_string.setText("");
						txt_fret_number.setText(deck_in_order.getCard().getFretNumber() + "");
						txt_note.setText(deck_in_order.getCard().getNote());

						break;

					case Flashcards.ASK_FRET_NUMBER:
						txt_guitar_string.setText(deck_in_order.getCard().getGuitarStringName());
						txt_fret_number.setText("");
						txt_note.setText(deck_in_order.getCard().getNote());

						break;

					case Flashcards.ASK_NOTE:
						txt_guitar_string.setText(deck_in_order.getCard().getGuitarStringName());
						txt_fret_number.setText(deck_in_order.getCard().getFretNumber() + "");
						txt_note.setText("");

						break;
				}
			}
		} else {
			// Study the randomized deck.
			txt_remaining_notes.setText(getString(R.string.txt_remaining_notes) + deck.size());

			if (deck.hasNext()) {
				switch (deck.getQuestionMode()) {
					case Flashcards.ASK_GUITAR_STRING:
						txt_guitar_string.setText("");
						txt_fret_number.setText(this.deck.getCard().getFretNumber() + "");
						txt_note.setText(this.deck.getCard().getNote());

						break;

					case Flashcards.ASK_FRET_NUMBER:
						txt_guitar_string.setText(this.deck.getCard().getGuitarStringName());
						txt_fret_number.setText("");
						txt_note.setText(this.deck.getCard().getNote());

						break;

					case Flashcards.ASK_NOTE:
						txt_guitar_string.setText(this.deck.getCard().getGuitarStringName());
						txt_fret_number.setText(this.deck.getCard().getFretNumber() + "");
						txt_note.setText("");

						break;
				}
			} else {
				// We've finished all cards.
				if( all_study_decks != null ) {
					all_study_decks.remove( 0 );
					if( all_study_decks_in_order != null && all_study_decks_in_order.size() > 0 ) {
						all_study_decks_in_order.remove(0);
					}

					if( all_study_decks.size() == 0 ) {
						// It's finished, so go to the final stats page.
						Intent intent = new Intent(this, FinishedActivity.class);
						intent.putExtra("completed_flashcards", deck);
						startActivity(intent);
						finish();
					} else {
						// We have more flashcards to learn, so go to the next step activity.
						Intent intent = new Intent(this, BeginnerNextStepActivity.class);
						intent.putExtra( "all_study_decks", all_study_decks );
						if( all_study_decks_in_order != null ) {
							intent.putExtra("all_study_decks_in_order", all_study_decks_in_order);
						}
						intent.putExtra( "total_cards_answered", deck.getTotalCardsAnswered() + "" );
						intent.putExtra( "total_cards_answered_correctly", deck.getTotalCardsAnsweredCorrectly() + "" );

						startActivity(intent);
						finish();

					}
				} else {
					// It's finished, so go to the final stats page.
					Intent intent = new Intent(this, FinishedActivity.class);
					intent.putExtra("completed_flashcards", deck);
					startActivity(intent);
					finish();
				}
			}
		}
	}

	public void showAnswer( View v ) {
		is_answer_visible = true;

		btn_show_answer .setEnabled( false  );
		btn_wrong_answer.setEnabled( true );
		if( txt_hints.getVisibility() == View.INVISIBLE ) {
			btn_right_answer.setEnabled(true);
		}

		if( deck_in_order != null && deck_in_order.hasNext() ) {
			txt_guitar_string.setText( deck_in_order.getCard().getGuitarStringName() );
			txt_fret_number.setText( deck_in_order.getCard().getFretNumber() + "" );
			txt_note.setText( deck_in_order.getCard().getNote() );

		} else {
			txt_guitar_string.setText( deck.getCard().getGuitarStringName() );
			txt_fret_number.setText( deck.getCard().getFretNumber() + "" );
			txt_note.setText( deck.getCard().getNote() );
		}
	}

	public void answeredCorrectly( View v ) {
		// get the next question.
		if( deck_in_order != null && deck_in_order.hasNext() ) {
			deck_in_order.answeredCorrectly();
		} else {
			deck.answeredCorrectly();
		}
		showTick();

		btn_show_answer .setEnabled( true  );
		btn_wrong_answer.setEnabled( false );
		btn_right_answer.setEnabled( false );

		displayQuestion();
	}

	public void answeredIncorrectly( View v ) {
		// get the next question.
		if( deck_in_order != null && deck_in_order.hasNext() ) {
			// Mark as incorrect and move on to the next card without shuffling.
			deck_in_order.answeredIncorrectly( false );
		} else {
			// Mark as incorrect, repopulate the list with cards and shuffle.
			deck.answeredIncorrectly( true );
		}
		showCross();

		btn_show_answer .setEnabled( true  );
		btn_right_answer.setEnabled( false );
		btn_wrong_answer.setEnabled( false );

		displayQuestion();
	}

	public void showTick() {
		if( answer_feedback_thread != null && answer_feedback_thread.isAlive() ) {
			answer_feedback_thread.interrupt();
		}
		answer_feedback_thread = new ShowImageThread( R.drawable.tick, R.drawable.empty, ANSWER_FEEDBACK_DELAY );
		answer_feedback_thread.start();
	}
	public void showCross() {
		if( answer_feedback_thread != null && answer_feedback_thread.isAlive() ) {
			answer_feedback_thread.interrupt();
		}
		answer_feedback_thread = new ShowImageThread( R.drawable.cross, R.drawable.empty, ANSWER_FEEDBACK_DELAY );
		answer_feedback_thread.start();
	}

	public void showHints( View v ) {
		String hints = "";
		int guitar_string = deck.getCard().getGuitarStringIndex();
		int fret_number   = deck.getCard().getFretNumber();
		if( (fret_number >= 1) && (fret_number <= 4) ) {
		    hints = makeHints( guitar_string, 1, 4 );
		}
		if( (fret_number >= 5) && (fret_number <= 8) ) {
			hints = makeHints( guitar_string, 5, 8 );

		}
		if( (fret_number >= 9) && (fret_number <= 12) ) {
			hints = makeHints( guitar_string, 9, 12 );

		}
		if( (fret_number >= 13) && (fret_number <= 16) ) {
			hints = makeHints( guitar_string, 13, 16 );

		}
		if( (fret_number >= 17) && (fret_number <= 20) ) {
			hints = makeHints( guitar_string, 17, 20 );
		}

		txt_hints.setText( hints );
		txt_hints.setVisibility( View.VISIBLE );
	}

	/**
	 * Make a string with a line of text for each fret number from start_fret and up to and including end_fret
	 * e.g. makeHints( guitar_string_index, 1, 4 )
	 * E 1 F
	 * E 2 F#
	 * E 3 G
	 * E 4 G#
	 * @param guitar_string_index
	 * @param start_fret
	 * @param end_fret
	 * @return
	 */
	public String makeHints( int guitar_string_index, int start_fret, int end_fret ) {
		String hint = "";
		for( int fret = start_fret; fret <= end_fret; fret++ ) {
			String note = GuitarString.getNoteAtFretNumber( guitar_string_index, fret );
			String guitar_string_note = GuitarString.getGuitarStringName( guitar_string_index );

			hint += guitar_string_note + " " + fret + " " + note + "\n";
		}

		return hint;
	}

	class ShowImageThread extends Thread {
		private long delay;
		private int img_first, img_last;

		ShowImageThread(int img_first, int img_last, long delay) {
			super();
			this.img_first = img_first;
			this.img_last = img_last;
			this.delay = delay;
		}

		public void run() {
			img_answer_feedback.setImageResource(img_first);
			try {
				Thread.sleep(delay);
				img_answer_feedback.setImageResource(img_last);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

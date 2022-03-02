package com.simonslater.guitarfretboardtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {
	private CheckBox chk_guitar_string_e;
	private CheckBox chk_guitar_string_a;
	private CheckBox chk_guitar_string_d;
	private CheckBox chk_guitar_string_g;
	private CheckBox chk_guitar_string_b;

	private CheckBox chk_frets_one_four;
	private CheckBox chk_frets_five_eight;
	private CheckBox chk_frets_nine_twelve;
	private CheckBox chk_frets_thirteen_sixteen;
	private CheckBox chk_frets_seventeen_twenty;

	private RadioButton radio_ask_guitar_string;
	private RadioButton radio_ask_fret;
	private RadioButton radio_ask_note;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		chk_guitar_string_e = (CheckBox) findViewById( R.id.chk_guitar_string_e );
		chk_guitar_string_a = (CheckBox) findViewById( R.id.chk_guitar_string_a );
		chk_guitar_string_d = (CheckBox) findViewById( R.id.chk_guitar_string_d );
		chk_guitar_string_g = (CheckBox) findViewById( R.id.chk_guitar_string_g );
		chk_guitar_string_b = (CheckBox) findViewById( R.id.chk_guitar_string_b );

		chk_frets_one_four         = (CheckBox) findViewById( R.id.chk_frets_one_four );
		chk_frets_five_eight       = (CheckBox) findViewById( R.id.chk_frets_five_eight );
		chk_frets_nine_twelve      = (CheckBox) findViewById( R.id.chk_frets_nine_twelve );
		chk_frets_thirteen_sixteen = (CheckBox) findViewById( R.id.chk_frets_thirteen_sixteen );
		chk_frets_seventeen_twenty = (CheckBox) findViewById( R.id.chk_frets_seventeen_twenty );

		radio_ask_guitar_string = (RadioButton) findViewById( R.id.radio_guitar_string);
		radio_ask_fret          = (RadioButton) findViewById( R.id.radio_fret);
		radio_ask_note          = (RadioButton) findViewById( R.id.radio_note);

		loadSettings();
	}

	public int[] ArrayListToIntArray( ArrayList<Integer> arr ) {
		// convert it to an array of ints.
		int[] final_array = new int[arr.size()];

		for( int i = 0; i < arr.size(); i++ ) {
			final_array[i] = arr.get( i );
		}

		return final_array;
	}

	public void startStudying( View v ) {
		int[] guitar_strings = getSelectedGuitarStrings();
		int[] fret_numbers   = getSelectedFretNumbers();
		int repititions      = getSelectedRepititions();
		int question_mode    = getSelectedQuestionMode();

		if( getSelectedFretNumbers().length > 0 && getSelectedGuitarStrings().length > 0 ) {
			saveSettings();
			Flashcards deck = new Flashcards( guitar_strings, fret_numbers, question_mode, repititions );
			Intent study_mode = new Intent( this, StudyModeActivity.class );
			study_mode.putExtra( "flashcards", deck );

			startActivity( study_mode );
		}
		if( ((CheckBox)findViewById(R.id.chk_guitar_string_e )).isChecked() ) {
			Log.d("mydebuginfo", "yes it's been ticked");
		} else {

			Log.d("mydebuginfo", "nope");
		}
	}
	public void loadSettings() {
		Properties properties = new Properties();

		try(InputStream config_file = this.openFileInput( "guitar_fretboard_trainer.conf" ) ) {
			properties.load(config_file);

			properties.keySet();
			Enumeration e = properties.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = properties.getProperty(key);
				boolean boolean_value = Boolean.parseBoolean( value );
				System.out.println("Key : " + key + ", Value : " + value);
				switch( key ) {
					case( "chk_guitar_string_e"):
						chk_guitar_string_e.setChecked( boolean_value );
						break;
					case( "chk_guitar_string_a"):
						chk_guitar_string_a.setChecked( boolean_value );
						break;
					case( "chk_guitar_string_d"):
						chk_guitar_string_d.setChecked( boolean_value );
						break;
					case( "chk_guitar_string_g"):
						chk_guitar_string_g.setChecked( boolean_value );
						break;
					case( "chk_guitar_string_b"):
						chk_guitar_string_b.setChecked( boolean_value );
						break;
					case( "chk_frets_one_four"):
						chk_frets_one_four.setChecked( boolean_value );
						break;
					case( "chk_frets_five_eight"):
						chk_frets_five_eight.setChecked( boolean_value );
						break;
					case( "chk_frets_nine_twelve"):
						chk_frets_nine_twelve.setChecked( boolean_value );
						break;
					case( "chk_frets_thirteen_sixteen"):
						chk_frets_thirteen_sixteen.setChecked( boolean_value );
						break;
					case( "chk_frets_seventeen_twenty"):
						chk_frets_seventeen_twenty.setChecked( boolean_value );
						break;
					case( "radio_ask_guitar_string"):
						radio_ask_guitar_string.setChecked( boolean_value );
						break;
					case( "radio_ask_fret"):
						radio_ask_fret.setChecked( boolean_value );
						break;
					case( "radio_ask_note"):
						radio_ask_note.setChecked( boolean_value );
						break;
				}
			}
		} catch( IOException ex ) {
			// Probably no config file, so just load the default settings.
			chk_guitar_string_e.setChecked( true );
			//chk_guitar_string_a.setChecked( true );
			//chk_guitar_string_d.setChecked( true );
			//chk_guitar_string_g.setChecked( true );
			//chk_guitar_string_b.setChecked( true );

			radio_ask_note.setChecked( true );

			chk_frets_one_four   .setChecked( true );
			chk_frets_five_eight .setChecked( true );
			chk_frets_nine_twelve.setChecked( true );
		}

		updateExample( null );
	}

	public void saveSettings() {
		try (OutputStreamWriter config_file = new OutputStreamWriter( this.openFileOutput( "guitar_fretboard_trainer.conf", Context.MODE_PRIVATE ) ) ) {
			Properties properties = new Properties();

			// set the properties value
			properties.setProperty( "chk_guitar_string_e", new Boolean(chk_guitar_string_e.isChecked()).toString() );
			properties.setProperty( "chk_guitar_string_a", new Boolean(chk_guitar_string_a.isChecked()).toString() );
			properties.setProperty( "chk_guitar_string_d", new Boolean(chk_guitar_string_d.isChecked()).toString() );
			properties.setProperty( "chk_guitar_string_g", new Boolean(chk_guitar_string_g.isChecked()).toString() );
			properties.setProperty( "chk_guitar_string_b", new Boolean(chk_guitar_string_b.isChecked()).toString() );

			properties.setProperty( "chk_frets_one_four",         new Boolean(chk_frets_one_four.isChecked()).toString() );
			properties.setProperty( "chk_frets_five_eight",       new Boolean(chk_frets_five_eight.isChecked()).toString() );
			properties.setProperty( "chk_frets_nine_twelve",      new Boolean(chk_frets_nine_twelve.isChecked()).toString() );
			properties.setProperty( "chk_frets_thirteen_sixteen", new Boolean(chk_frets_thirteen_sixteen.isChecked()).toString() );
			properties.setProperty( "chk_frets_seventeen_twenty", new Boolean(chk_frets_seventeen_twenty.isChecked()).toString() );

			properties.setProperty( "radio_ask_guitar_string", new Boolean(radio_ask_guitar_string.isChecked()).toString() );
			properties.setProperty( "radio_ask_fret",          new Boolean(radio_ask_fret.isChecked()).toString() );
			properties.setProperty( "radio_ask_note",          new Boolean(radio_ask_note.isChecked()).toString() );

			// save properties.
			properties.store(config_file, "Basic settings for the main menu.");
		} catch(IOException ex ) {
			ex.printStackTrace();
		}
	}

	public int[] getSelectedGuitarStrings() {
		ArrayList<Integer> guitar_strings = new ArrayList<Integer>();
		CheckBox chk_guitar_string_e = (CheckBox) findViewById( R.id.chk_guitar_string_e );
		CheckBox chk_guitar_string_a = (CheckBox) findViewById( R.id.chk_guitar_string_a );
		CheckBox chk_guitar_string_d = (CheckBox) findViewById( R.id.chk_guitar_string_d );
		CheckBox chk_guitar_string_g = (CheckBox) findViewById( R.id.chk_guitar_string_g );
		CheckBox chk_guitar_string_b = (CheckBox) findViewById( R.id.chk_guitar_string_b );

		if( chk_guitar_string_e.isChecked() ) {
			guitar_strings.add( GuitarString.INDEX_NOTE_E );
		}

		if( chk_guitar_string_a.isChecked() ) {
			guitar_strings.add( GuitarString.INDEX_NOTE_A );
		}

		if( chk_guitar_string_d.isChecked() ) {
			guitar_strings.add( GuitarString.INDEX_NOTE_D );
		}

		if( chk_guitar_string_g.isChecked() ) {
			guitar_strings.add( GuitarString.INDEX_NOTE_G );
		}

		if( chk_guitar_string_b.isChecked() ) {
			guitar_strings.add( GuitarString.INDEX_NOTE_B );
		}

		return ArrayListToIntArray( guitar_strings );
	}

	public int getSelectedQuestionMode() {
		RadioButton radio_ask_guitar_string = (RadioButton) findViewById( R.id.radio_guitar_string);
		RadioButton radio_ask_fret          = (RadioButton) findViewById( R.id.radio_fret);
		RadioButton radio_ask_note          = (RadioButton) findViewById( R.id.radio_note);
		if( radio_ask_fret.isChecked() ) {
			return Flashcards.ASK_FRET_NUMBER;
		}
		else if ( radio_ask_guitar_string.isChecked() ) {
			return Flashcards.ASK_GUITAR_STRING;
		}
		else if ( radio_ask_note.isChecked() ) {
			return Flashcards.ASK_NOTE;
		}
		else {
			// Default to this.
			return Flashcards.ASK_NOTE;
		}
	}
	public int[] getSelectedFretNumbers() {
		ArrayList<Integer> frets = new ArrayList<Integer>();
		CheckBox chk_frets_one_four         = (CheckBox) findViewById( R.id.chk_frets_one_four );
		CheckBox chk_frets_five_eight       = (CheckBox) findViewById( R.id.chk_frets_five_eight );
		CheckBox chk_frets_nine_twelve      = (CheckBox) findViewById( R.id.chk_frets_nine_twelve );
		CheckBox chk_frets_thirteen_sixteen = (CheckBox) findViewById( R.id.chk_frets_thirteen_sixteen );
		CheckBox chk_frets_seventeen_twenty = (CheckBox) findViewById( R.id.chk_frets_seventeen_twenty );

		if ( chk_frets_one_four.isChecked() ) {
			frets.add( 1 );
			frets.add( 2 );
			frets.add( 3 );
			frets.add( 4 );
		}
		if ( chk_frets_five_eight.isChecked() ) {
			frets.add( 5 );
			frets.add( 6 );
			frets.add( 7 );
			frets.add( 8 );
		}
		if ( chk_frets_nine_twelve.isChecked() ) {
			frets.add( 9  );
			frets.add( 10 );
			frets.add( 11 );
			frets.add( 12 );
		}
		if ( chk_frets_thirteen_sixteen.isChecked() ) {
			frets.add( 13 );
			frets.add( 14 );
			frets.add( 15 );
			frets.add( 16 );
		}
		if ( chk_frets_seventeen_twenty.isChecked() ) {
			frets.add( 17 );
			frets.add( 18 );
			frets.add( 19 );
			frets.add( 20 );
		}

		return ArrayListToIntArray( frets );

	}
	public int getSelectedRepititions() {
		int repititions = 3;
		return repititions;
	}

	public void updateExample( View v ) {
		TextView lbl_guitar_string = (TextView) findViewById( R.id.txt_guitar_string );
		TextView lbl_fret          = (TextView) findViewById( R.id.txt_fret );
		TextView lbl_note          = (TextView) findViewById( R.id.txt_note );

		if( radio_ask_guitar_string.isChecked() ) {
			lbl_guitar_string.setText("?");
			lbl_fret         .setText("5");
			lbl_note         .setText("A");
		} else if( radio_ask_fret.isChecked() ) {
			lbl_guitar_string.setText("E");
			lbl_fret         .setText("?");
			lbl_note         .setText("A");

		} else if( radio_ask_note.isChecked() ) {
			lbl_guitar_string.setText("E");
			lbl_fret         .setText("5");
			lbl_note         .setText("?");

		}
		//switch( v.getId() ) {
		//	case R.id.radio_guitar_string:
		//		lbl_guitar_string.setText("?");
		//		lbl_fret         .setText("5");
		//		lbl_note         .setText("A");
		//		break;
		//	case R.id.radio_fret:
		//		lbl_guitar_string.setText("E");
		//		lbl_fret         .setText("?");
		//		lbl_note         .setText("A");
		//		break;
		//	case R.id.radio_note:
		//	    lbl_guitar_string.setText("E");
		//		lbl_fret         .setText("5");
		//		lbl_note         .setText("?");
		//		break;
		//}
	}
}
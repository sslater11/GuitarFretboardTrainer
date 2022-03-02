package com.simonslater.guitarfretboardtrainer;

import android.util.Log;

public abstract class GuitarString {
	public static int INDEX_NOTE_A        =  0;
	public static int INDEX_NOTE_A_SHARP  =  1;
	public static int INDEX_NOTE_B        =  2;
	public static int INDEX_NOTE_C        =  3;
	public static int INDEX_NOTE_C_SHARP  =  4;
	public static int INDEX_NOTE_D        =  5;
	public static int INDEX_NOTE_D_SHARP  =  6;
	public static int INDEX_NOTE_E        =  7;
	public static int INDEX_NOTE_F        =  8;
	public static int INDEX_NOTE_F_SHARP  =  9;
	public static int INDEX_NOTE_G        = 10;
	public static int INDEX_NOTE_G_SHARP  = 11;

	private static int INDEX_NOTE_MAX     = 12;
	private static String[] notes = { "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#" };

	public static String getNoteAtFretNumber( Card c ) {
		return getNoteAtFretNumber( c.getGuitarStringIndex(), c.getFretNumber() );
	}

	public static String getNoteAtFretNumber( int guitar_string, int fret_num ) {
		if( (guitar_string < 0) && (guitar_string >= INDEX_NOTE_MAX) ) {
		    System.out.println( "This should never happen. whooops.");
			return null;
		}
		else if( ( fret_num < 0 ) )
		{
			System.out.println( "This should never happen. whooops.");
			return null;
		}

		// Using modulus 12 allows us to pass any positive fret number and string offset,
		// and makes it a nice quick calculation.
		int note_index = (guitar_string + (fret_num % 12)) % 12;
		return notes[note_index];
	}

	public static String getGuitarStringName( Card card ) {
		return getGuitarStringName( card.getGuitarStringIndex() );
	}
	public static String getGuitarStringName( int guitar_string ) {
		return notes[ guitar_string ];
	}
}

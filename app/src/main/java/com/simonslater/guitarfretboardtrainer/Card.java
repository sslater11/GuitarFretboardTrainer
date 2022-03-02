package com.simonslater.guitarfretboardtrainer;

import java.io.Serializable;

public class Card implements Serializable {
	int guitar_string, fret_number;
	public Card( int guitar_string, int fret_number ) {
		this.guitar_string = guitar_string;
		this.fret_number   = fret_number;
	}

	public int getGuitarStringIndex() {
		return this.guitar_string;
	}

	public int getFretNumber() {
		return this.fret_number;
	}

	public String getGuitarStringName() {
		return GuitarString.getGuitarStringName( this );
	}

	public String getNote() {
	    try {
			return GuitarString.getNoteAtFretNumber(this);
		} catch( Exception ex ) {
			return "";
		}
	}

	public boolean isSame( Card c ) {
		return ( getGuitarStringIndex() == c.getGuitarStringIndex() ) && ( getFretNumber() == c.getFretNumber() );
	}
}

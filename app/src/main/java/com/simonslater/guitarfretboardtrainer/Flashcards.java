package com.simonslater.guitarfretboardtrainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Flashcards implements Serializable {
	public static final int ASK_GUITAR_STRING = 0;
	public static final int ASK_FRET_NUMBER   = 1;
	public static final int ASK_NOTE          = 2;

	private int total_cards_answered = 0;
	private int total_cards_answered_correctly = 0;
	private int question_mode;
	private int num_of_repetitions;
	ArrayList<Card> cards = new ArrayList<Card>();

	Flashcards( int[] guitar_strings, int[] fret_numbers, int question_mode, int num_of_repetitions ) {
		// Make an array that contains every note on the fretboard that we have added.
		// Repeat adding all those notes for the same string x times.
		this.question_mode = question_mode;
		this.num_of_repetitions = num_of_repetitions;
		for( int i = 0; i < num_of_repetitions; i++ ) {
			for( int gs : guitar_strings ) {
				for( int fn : fret_numbers ) {
					Card c = new Card( gs, fn );
					cards.add( c );
				}
			}
		}

		Collections.shuffle( cards );
	}

	public boolean hasNext() {
		if( cards.size() > 0 ) {
			return true;
		}
		else {
			return false;
		}
	}

	public int getQuestionMode() {
		return question_mode;
	}
	public Card getCard() {
		return cards.get(0);
	}

	public int getTotalCardsAnswered() {
		return total_cards_answered;
	}

	public int getTotalCardsAnsweredCorrectly() {
		return total_cards_answered_correctly;
	}

	public int size() {
		return cards.size();
	}

	public void answeredCorrectly() {
		total_cards_answered++;
		total_cards_answered_correctly++;
		cards.remove(0);
	}

	public void answeredIncorrectly() {
		total_cards_answered++;

		// We need to now remove all instances of this card, then add it again by the amount of repetitions
		// so the user can practice this card more.
        for( int i = 1; i < cards.size(); i++ ) {
        	if( cards.get(i).isSame( cards.get(0) ) ) {
        		cards.remove( i );
        		i--;
			}
		}

        for( int i = 1; i < num_of_repetitions; i++ ) {
        	int guitar_string =  cards.get(0).getGuitarStringIndex();
			int fret_number   =  cards.get(0).getFretNumber();
			cards.add( new Card( guitar_string, fret_number ) );
		}

		shuffle();
	}

	public void shuffle() {
		Collections.shuffle( cards );
	}
}

/* © Copyright 2022, Simon Slater

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, version 2 of the License.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
*/


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

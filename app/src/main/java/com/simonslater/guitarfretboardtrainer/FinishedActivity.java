package com.simonslater.guitarfretboardtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinishedActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finished);

		Intent intent = getIntent();
		Flashcards deck = (Flashcards) intent.getSerializableExtra( "completed_flashcards" );
		int total_answered = deck.getTotalCardsAnswered();
		int total_answered_correctly = deck.getTotalCardsAnsweredCorrectly();
		double percent = ((double) total_answered_correctly / total_answered);
		percent = Math.round(percent * 100);

		String stats1 = getString( R.string.txt_stats );
		String stats2 = getString( R.string.txt_stats_percent );
		stats1 = stats1.replaceAll( "total_answered_correctly", (int)total_answered_correctly + "" );
		stats1 = stats1.replaceAll( "total_answered", (int)total_answered + "" );
		stats2 = stats2.replaceAll( "percent", (int)percent + "" );

		TextView txt_stats = (TextView)findViewById( R.id.txt_stats );
		txt_stats.setText( stats1 + "\n\n" + stats2 );


	}

	public void mainMenu( View v ) {
		finish();
	}

	public void loadLeadbootsSite( View v ) {
		Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.leadboots.co.uk") );
		startActivity(intent);
	}
}
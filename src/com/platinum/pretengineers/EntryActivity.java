package com.platinum.pretengineers;

import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class EntryActivity extends ActionBarActivity {

	Parser parser;
	TextView entryType, entryModule, entryName, entryLocation;
	int currentPeriod;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry);
		entryType = (TextView) findViewById(R.id.entry_type);
		entryModule = (TextView) findViewById(R.id.entry_module);
		entryName = (TextView) findViewById(R.id.entry_name);
		entryLocation = (TextView) findViewById(R.id.entry_location);
		currentPeriod = Common.hour - 9;
		if(currentPeriod > 0 || currentPeriod > 17){
			currentPeriod = 0;
		}
		parser = new Parser();
		Common.currentClasses = parser.getClasses();
	}

	@Override
	public void onResume(){
		super.onResume();
		entryType.setText(Common.currentClasses[currentPeriod][0]);
		entryModule.setText("Module: " + Common.currentClasses[currentPeriod][1]);
		entryName.setText("Class: " + Common.currentClasses[currentPeriod][2]);
		entryLocation.setText("Location: " + Common.currentClasses[currentPeriod][3]);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entry, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

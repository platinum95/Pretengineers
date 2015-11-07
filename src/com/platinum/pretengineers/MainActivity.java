package com.platinum.pretengineers;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	TextView mainTextGroup;
	TextView mainTextCourse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
	}
	@Override
	protected void onResume(){
		super.onResume();
		SharedPreferences sharedPrefs = getSharedPreferences("options", 0);
		if(!sharedPrefs.contains("initialised")){
			Intent initOptionsIntent = new Intent(this, InitOptions.class);
			startActivity(initOptionsIntent);
		
		}
		else{
			int groupInt= sharedPrefs.getInt("group", -1);
			int courseInt = sharedPrefs.getInt("course", -1);
			Common.group = groupInt;
			Common.course = courseInt;
			Intent entryIntent = new Intent(this, EntryActivity.class);
			entryIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(entryIntent);
			finish();
		}
		
		
		
		
	}


}

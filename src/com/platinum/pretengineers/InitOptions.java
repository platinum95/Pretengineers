package com.platinum.pretengineers;

import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InitOptions extends AppCompatActivity {
	
	private Spinner course_spinner;
	private Spinner group_spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_init_options);
		course_spinner = (Spinner) findViewById(R.id.init_options_course);
		group_spinner = (Spinner) findViewById(R.id.init_options_group);
		ArrayAdapter<CharSequence> courses_adapter = ArrayAdapter.createFromResource(this,
		        R.array.courses_array, android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> group_adapter = ArrayAdapter.createFromResource(this,
		        R.array.group_letter_array, android.R.layout.simple_spinner_item);
		courses_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		group_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		course_spinner.setAdapter(courses_adapter);
		group_spinner.setAdapter(group_adapter);
		
	}

	public void next(View view){
		int course_selection = course_spinner.getSelectedItemPosition();
		int group_selection = group_spinner.getSelectedItemPosition();
		if(course_selection == 0 || group_selection == 0){
			
			
		}
		else{
			SharedPreferences sharedPrefs = getSharedPreferences("options", 0);
			SharedPreferences.Editor editor = sharedPrefs.edit();
			editor.putInt("course", course_selection);
			editor.putInt("group", group_selection);
			editor.putBoolean("initialised", true);
			editor.commit();
			finish();
		}
	
	}
}

package com.example.android.imagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.android.imagesearch.models.Settings;

public class SettingsActivity extends Activity {

	private Settings settings;
	private EditText etSite;
	private Spinner sbColor;
	private Spinner sbType;
	private Spinner sbSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		settings = (Settings) getIntent().getParcelableExtra("settings");
		setupViews();
	}

	private void setupViews() {
		etSite = (EditText) findViewById(R.id.etSite);
		etSite.setText(settings.getSite());
		
		sbType = (Spinner) findViewById(R.id.sbType);
		setSpinnerToValue(sbType, settings.getType());
		
		sbColor = (Spinner) findViewById(R.id.sbColor);
		setSpinnerToValue(sbColor, settings.getColor());
		
		sbSize = (Spinner) findViewById(R.id.sbSize);
		setSpinnerToValue(sbSize, settings.getSize());
	}
	//there ideally should be a better spinner that takes a collection and the selected value, optionally with a matching function for selected value
	private void setSpinnerToValue(Spinner spinner, String value) {
		int index = 0;
		SpinnerAdapter adapter = spinner.getAdapter();
		for (int i = 0; i < adapter.getCount(); i++) {
			if (adapter.getItem(i).equals(value)) {
				index = i;
			}
		}
		spinner.setSelection(index);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	public void onSaveSettings(View v) {
//		Toast.makeText(getApplicationContext(), "request code", Toast.LENGTH_LONG).show();
		settings.setSite(etSite.getText().toString());
		settings.setType(sbType.getSelectedItem().toString());
		settings.setColor(sbColor.getSelectedItem().toString());
		settings.setSize(sbSize.getSelectedItem().toString());
		Intent i = new Intent(getApplicationContext(),MainActivity.class);
		i.putExtra("settings", settings);
		setResult(RESULT_OK,i);
		finish();
	}
}

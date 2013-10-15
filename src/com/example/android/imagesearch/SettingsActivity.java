package com.example.android.imagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.android.imagesearch.models.Settings;

public class SettingsActivity extends Activity {

	private Settings settings;
	private EditText etSite;

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
		Intent i = new Intent(getApplicationContext(),MainActivity.class);
		i.putExtra("settings", settings);
		setResult(RESULT_OK,i);
		finish();
	}
}

package com.example.android.imagesearch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.android.imagesearch.R;
import com.example.android.imagesearch.models.Image;
import com.loopj.android.image.SmartImageView;

public class ImageDisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		Image image = (Image) getIntent().getParcelableExtra("url");
		SmartImageView view = (SmartImageView) findViewById(R.id.ivDisplay);
//		Log.d("DEBUG", "url is " + url);
		view.setImageUrl(image.getUrl());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
	}

}

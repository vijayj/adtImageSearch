package com.example.android.imagesearch;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends Activity {

	private EditText etQuery;
	private GridView gvResults;
	private Button btnSearch;
	private ArrayList<Image> imageResults = new ArrayList<Image>();
	private ImageListAdapter imageListAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupViewHandlers();
		imageListAdapter = new ImageListAdapter(this, imageResults);
		gvResults.setAdapter(imageListAdapter);
		gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View parent, int position,long rowId) {
				Intent i = new Intent(getApplicationContext(),ImageDisplayActivity.class);
				Image image = imageResults.get(position);
				i.putExtra("url", image);
				startActivity(i);
			}
		});
	}

	private void setupViewHandlers() {
		etQuery = (EditText) findViewById(R.id.etQuery);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		gvResults = (GridView) findViewById(R.id.gvResults);
	}

	public void onImageSearch(View v) {
		String query = etQuery.getText().toString();
		
		AsyncHttpClient client = new AsyncHttpClient();
		// https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=fuzzy%20monkey&imgcolor=blue&imgsz=medium&imgtype=photo&rsz=8&start=3&as_sitesearch=photobucket.com
//		String url = new Uri.Builder()
//				.scheme("https")
//				.path("//ajax.googleapis.com/ajax/services/search/images")
//				.appendQueryParameter("v","1.0")
//				.appendQueryParameter("rsz", "8")
//				.appendQueryParameter("start", "0")
//				.appendQueryParameter("imgsz", "medium")
//				.appendQueryParameter("q", query).toString();

		Toast.makeText(this, "Search for " + query, Toast.LENGTH_SHORT).show();
		String url = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&rsz=8&start=0";
		RequestParams params = new RequestParams();
		params.put("imgsz", "medium");
		params.put("q", Uri.encode(query));
		Log.d("DEBUG", url);		
		client.get(url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				try {
					 JSONArray results = response.getJSONObject("responseData")
							.getJSONArray("results");
					imageResults.clear();
//					imageResults.addAll(Image.fromJSONArray(results));
//					imageListAdapter.notifyDataSetChanged();
					imageListAdapter.addAll(Image.fromJSONArray(results));
					Log.d("DEBUG", imageResults.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

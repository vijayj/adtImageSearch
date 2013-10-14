package com.example.android.imagesearch;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Image implements Serializable {
	
	private static final long serialVersionUID = 752647115123776147L;

	private String url;
	private String thumbnailUrl;
	private String title;

	public Image(JSONObject object) throws JSONException {
		this.url = object.getString("url");
		this.thumbnailUrl = object.getString("tbUrl");
		this.title = object.getString("title");
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	// public void setThumbnailUrl(String thumbnailUrl) {
	// this.thumbnailUrl = thumbnailUrl;
	// }

	public String getTitle() {
		return title;
	}

	// public void setTitle(String title) {
	// this.title = title;
	// }

	@Override
	public String toString() {
		return "Image =" + url ;
	}

	public String getUrl() {
		return url;
	}

	public static ArrayList<Image> fromJSONArray(JSONArray results) {
		ArrayList<Image> images = new ArrayList<Image>(results.length());
		for (int i = 0; i < results.length(); i++) {
			try {
				images.add(new Image(results.getJSONObject(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return images;
	}

	// public void setUrl(String url) {
	// this.url = url;
	// }

}

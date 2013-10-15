package com.example.android.imagesearch.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
	
	
	private String url;
	private String thumbnailUrl;
	private String title;
	private static final String DEFAULT = "http://cdn.panasonic.com/images/imageNotFound400.jpg";

	public Image(JSONObject object) throws JSONException {
		this.url = object.getString("url") == null ? DEFAULT : object.getString("url");
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
	
	protected Image(Parcel in) {
        url = in.readString();
        thumbnailUrl = in.readString();
        title = in.readString();
    }

    @Override
	public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(thumbnailUrl);
        dest.writeString(title);
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
		public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
		public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}

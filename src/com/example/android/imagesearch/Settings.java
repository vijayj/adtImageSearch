package com.example.android.imagesearch;

import android.os.Parcel;
import android.os.Parcelable;

public class Settings implements Parcelable {

	private String color = "";
	private String size = "";
	private String type = "";
	private String site = "";

	protected Settings(Parcel source) {
		this.color = source.readString();
		this.size = source.readString();
		this.type = source.readString();
		this.site = source.readString();
	}

	public Settings() {
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(color);
		dest.writeString(size);
		dest.writeString(type);
		dest.writeString(site);
	}
	
	 public static final Parcelable.Creator<Settings> CREATOR = new Parcelable.Creator<Settings>() {

		@Override
		public Settings createFromParcel(Parcel source) {
			return new Settings(source);
		}

		@Override
		public Settings[] newArray(int size) {
			return new Settings[size];
		}
		 
	 };

	public String getColor() {
		return color;
	}

	public String getSize() {
		return size;
	}

	public String getType() {
		return type;
	}

	public String getSite() {
		return site;
	}

}

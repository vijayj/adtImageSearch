package com.example.android.imagesearch;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ImageListAdapter extends ArrayAdapter<Image> {

	public ImageListAdapter(Context context, List<Image> images) {
		super(context, R.layout.image, images);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Image item = this.getItem(position);
		SmartImageView ivImage;
		if(convertView == null){
			LayoutInflater inflator = LayoutInflater.from(getContext());
			ivImage = (SmartImageView) inflator.inflate(R.layout.image, parent, false);
			
		} else {
			ivImage  = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		
		ivImage.setImageUrl(item.getThumbnailUrl());
		return ivImage;
	}
}

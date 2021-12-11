package com.walkwithme;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageListAdapter extends ArrayAdapter<String> {
    private Activity mContext;
    private ArrayList<Integer> mImages;
    private ArrayList<String> mImagesUrls;

    public ImageListAdapter(
            Activity context, ArrayList<String> listOfValues, ArrayList<String> images) {
        // The listOfValues is used when you make item click to get value
        // Each image must to have a text value
        super(context, R.layout.imagelist_item, listOfValues);
        mContext = context;
        mImagesUrls = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View returnView = inflater.inflate(R.layout.imagelist_item, null);
        ImageView imageView = (ImageView) returnView.findViewById(R.id.previewImage);
        String myUrl = mImagesUrls.get(position);
        new DownloadImageTask(imageView).execute(myUrl);
        return returnView;
    }
}

package com.walkwithme;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendListAdapter extends ArrayAdapter<String> {
    private Activity mContext;
    private ArrayList<Integer> mIds;
    private ArrayList<String> mImagesUrls;
    private ArrayList<String> mNames;

    public FriendListAdapter(
            Activity context,
            ArrayList<String> listOfValues,
            ArrayList<String> images,
            ArrayList<Integer> ids) {
        // The listOfValues is used when you make item click to get value
        // Each image must to have a text value
        super(context, R.layout.friendlist_item, listOfValues);
        mContext = context;
        mImagesUrls = images;
        mIds = ids;
        mNames = listOfValues;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View returnView = inflater.inflate(R.layout.friendlist_item, null);
        ImageView imageView = (ImageView) returnView.findViewById(R.id.friendAvatar);
        TextView nameView = (TextView) returnView.findViewById(R.id.friendName);
        returnView.setTag(mIds.get(position));
        nameView.setText(mNames.get(position));
        nameView.setId(mIds.get(position));
        String myUrl = mImagesUrls.get(position);
        new DownloadImageTask(imageView).execute(myUrl);
        imageView.setId(mIds.get(position));
        return returnView;
    }
}

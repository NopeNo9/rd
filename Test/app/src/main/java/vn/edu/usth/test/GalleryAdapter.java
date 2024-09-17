package vn.edu.usth.test;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;
public class GalleryAdapter extends BaseAdapter {
    private List<Integer> imageList; // List of image resources
    private Context context;
    private LayoutInflater inflater;

    public GalleryAdapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the layout for each item in the ListView
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_gallery, parent, false);
        }

        // Get the ImageView and set the image resource
        ImageView imageView = convertView.findViewById(R.id.gallery_image);
        imageView.setImageResource(imageList.get(position));

        return convertView;
    }
}

package vn.edu.usth.test;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

public class GalleryFragment extends Fragment {

    public GalleryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery_layout, container, false);

        ListView listView = view.findViewById(R.id.gallery_list_view);

        // List of image resources
        List<Integer> images = Arrays.asList(
                R.drawable.cat_1,
                R.drawable.cozy_1,
                R.drawable.coffe_1,
                R.drawable.love_1,
                R.drawable.pasta_2,
                R.drawable.pasta_1,
                R.drawable.snow_1,
                R.drawable.sun_flower,
                R.drawable.sunny,
                R.drawable.snow_1,
                R.drawable.baseline_account_circle_24,
                R.drawable.baseline_account_circle_24
        );

        // Set up the custom adapter
        GalleryAdapter adapter = new GalleryAdapter(getContext(), images);
        listView.setAdapter(adapter);

        return view;
    }
}
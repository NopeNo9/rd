package vn.edu.usth.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FeedListFragment extends Fragment {

    private ListView feedListView;
    private FeedListAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_feed_list_adapter, container, false);

        feedListView = view.findViewById(R.id.feeds_list_view);

        // Example data for Feed List
        ArrayList<Feed> feedList = new ArrayList<>();
        feedList.add(new Feed("Feed Item 1", R.drawable.x_logo));
        feedList.add(new Feed("Feed Item 2", R.drawable.baseline_account_circle_24));
        feedList.add(new Feed("Feed Item 3", R.drawable.baseline_account_circle_white_24));
        feedList.add(new Feed("Feed Item 4", R.drawable.google_logo));
        feedList.add(new Feed("Feed Item 5", R.drawable.baseline_remove_red_eye_24));

        // Setting up the FeedListAdapter for Feed List
        adapter = new FeedListAdapter(getActivity(), feedList);
        feedListView.setAdapter(adapter);

        return view;
    }

}









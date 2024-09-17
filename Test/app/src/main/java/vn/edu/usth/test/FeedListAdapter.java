package vn.edu.usth.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class FeedListAdapter extends ArrayAdapter<Feed> {



    public FeedListAdapter(Context context, List<Feed> feedList) {
        super(context,0, feedList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Feed feed = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_feed_item, parent, false);
        }

        // Lookup view for data population
        ImageView imageView = convertView.findViewById(R.id.feed_image);
        TextView titleTextView = convertView.findViewById(R.id.feed_title);
        ImageButton upButton = convertView.findViewById(R.id.icon_up);
        ImageButton downButton = convertView.findViewById(R.id.icon_down);
        ImageButton commentButton = convertView.findViewById(R.id.icon_comment);


        TextView upvoteCount = convertView.findViewById(R.id.upvote_count);
        TextView downvoteCount = convertView.findViewById(R.id.downvote_count);
        TextView commentCount = convertView.findViewById(R.id.comment_count);

        // Populate data (example)

        // Populate the data into the template view using the data object
        imageView.setImageResource(feed.getImageResource());
        titleTextView.setText(feed.getTitle());


        upvoteCount.setText(String.valueOf(feed.getUpvotes()));
        downvoteCount.setText(String.valueOf(feed.getDownvotes()));
        commentCount.setText(String.valueOf(feed.getComments().size()));

        // Handle upvote button click
        upButton.setOnClickListener(v -> {
            feed.incrementUpvotes();
            upvoteCount.setText(String.valueOf(feed.getUpvotes()));  // Update the count
        });

        // Handle downvote button click
        downButton.setOnClickListener(v -> {
            feed.incrementDownvotes();
            downvoteCount.setText(String.valueOf(feed.getDownvotes()));  // Update the count
        });

        // Handle comment button click
        commentButton.setOnClickListener(v -> {
            // Pass the feed object to CommentHelper
            CommentHelper commentHelper = new CommentHelper(getContext(), feed);
            commentHelper.showComments(feed.getComments());
        });

        // Return the completed view to render on screen
        return convertView;
    }
}


package vn.edu.usth.test;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;

public class CommentHelper {

    private Context context;
    private Feed feed;

    public CommentHelper(Context context, Feed feed) {
        this.context = context;
        this.feed = feed;
    }

    public void showComments(List<String> comments) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context); // Use context from constructor
        builder.setTitle("Comments");

        // Create a ListView to show the comments
        ListView commentsListView = new ListView(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, comments);
        commentsListView.setAdapter(adapter);

        builder.setView(commentsListView);

        // Add a button to allow users to add new comments
        builder.setPositiveButton("Add Comment", (dialog, which) -> {
            // Show an input dialog to add a comment
            showAddCommentDialog();
        });

        builder.setNegativeButton("Close", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showAddCommentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Comment");

        final EditText input = new EditText(context);
        builder.setView(input);

        builder.setPositiveButton("Submit", (dialog, which) -> {
            String comment = input.getText().toString();
            // Add the comment to the feed and update UI
            feed.addComment(comment);  // Adding comment to feed
            showComments(feed.getComments());  // Refresh the comments list after adding
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
}
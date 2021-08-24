package be.leeroy.studentapp.view.main.comment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.models.Comment;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    public TextView author;
    public TextView content;

    private Comment comment;

    public CommentViewHolder(@NonNull View itemView, @IdRes Integer profileClickDestination) {
        super(itemView);

        author = itemView.findViewById(R.id.comment_author);
        content = itemView.findViewById(R.id.comment_content);

        /* Profile click */
        itemView.findViewById(R.id.comment_profile).setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("user", comment.getUser().getEmail());

            Navigation.findNavController(view).navigate(profileClickDestination, bundle);
        });
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}

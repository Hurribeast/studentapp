package be.leeroy.studentapp.view.main.comment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.models.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private List<Comment> comments;

    @IdRes
    private final Integer profileClickDestination;

    public CommentAdapter(@IdRes Integer profileClickDestination) {
        this.profileClickDestination = profileClickDestination;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_element, parent, false);

        return new CommentViewHolder(v, profileClickDestination);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = comments.get(position);

        holder.setComment(comment);

        String author = comment.getUser().getFirstname() + " " + comment.getUser().getLastname();
        String content = comment.getContent();

        holder.author.setText(author);
        holder.content.setText(content);
    }

    @Override
    public int getItemCount() {
        return comments == null ? 0 : comments.size();
    }

    public void setComments(List<Comment> comments){
        this.comments = comments;
        notifyDataSetChanged();
    }
}

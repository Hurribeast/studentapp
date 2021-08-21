package be.leeroy.studentapp.view.main.publication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import be.leeroy.studentapp.R;

public class PublicationViewHolder extends RecyclerView.ViewHolder {

    public TextView author;
    public TextView content;
    public TextView date;
    public TextView nbComments;

    public PublicationViewHolder(@NonNull View itemView) {
        super(itemView);

        author = itemView.findViewById(R.id.publication_author);
        content = itemView.findViewById(R.id.publication_content);
        date = itemView.findViewById(R.id.publication_date);
        nbComments = itemView.findViewById(R.id.publication_nbComments);
    }
}

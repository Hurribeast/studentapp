package be.leeroy.studentapp.view.main.publication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.models.Publication;

public class PublicationViewHolder extends RecyclerView.ViewHolder {

    public TextView author;
    public TextView content;
    public TextView date;
    public TextView nbComments;

    private Publication publication;

    public PublicationViewHolder(@NonNull View itemView, @IdRes Integer profileClickDestination, @IdRes Integer commentsClickNavigation) {
        super(itemView);

        author = itemView.findViewById(R.id.publication_author);
        content = itemView.findViewById(R.id.publication_content);
        date = itemView.findViewById(R.id.publication_date);
        nbComments = itemView.findViewById(R.id.publication_nbComments);

        itemView.findViewById(R.id.publication_comment_button).setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("publiId", publication.getId());

            Navigation.findNavController(view).navigate(commentsClickNavigation, bundle);
        });

        if (profileClickDestination != null) {
            /* Profile click */
            itemView.findViewById(R.id.publication_profile).setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putString("user", publication.getUser().getEmail());

                Navigation.findNavController(view).navigate(profileClickDestination, bundle);
            });
        }
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}

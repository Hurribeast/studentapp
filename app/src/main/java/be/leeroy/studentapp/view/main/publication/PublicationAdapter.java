package be.leeroy.studentapp.view.main.publication;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.models.Publication;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationViewHolder> {

    private Publication[] publications;

    @NonNull
    @Override
    public PublicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.publication_element, parent, false);

        return new PublicationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PublicationViewHolder holder, int position) {
        Publication publication = publications[position];

        String author = publication.getUser().getFirstname() + " " + publication.getUser().getLastname();
        String nbComments = publication.getNbComments() + " comments";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.FRENCH);
        String date = dateFormat.format(publication.getDate().getTime());

        holder.setPublication(publication);

        holder.content.setText(publication.getContent());
        holder.author.setText(author);
        holder.date.setText(date);
        holder.nbComments.setText(nbComments);
    }

    @Override
    public int getItemCount() {
        return publications == null ? 0 : publications.length;
    }

    public void setPublications(Publication[] publications){
        this.publications = publications;
        notifyDataSetChanged();
    }
}

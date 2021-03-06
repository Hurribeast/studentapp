package be.leeroy.studentapp.dataaccess.mappers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import be.leeroy.studentapp.dataaccess.dto.CommentDTO;
import be.leeroy.studentapp.dataaccess.dto.PublicationDTO;
import be.leeroy.studentapp.models.Comment;
import be.leeroy.studentapp.models.Publication;
import be.leeroy.studentapp.models.User;

public class PublicationMapper {

    private static PublicationMapper instance = null;

    public static PublicationMapper getInstance() {
        if (instance == null) instance = new PublicationMapper();

        return instance;
    }

    public Publication mapToPublication(PublicationDTO publicationDTO) {
        User user = new User(publicationDTO.getEmail(), publicationDTO.getLastname(), publicationDTO.getFirstname());

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH);
        Date date = null;

        try {
            date = df.parse(publicationDTO.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gregDate = new GregorianCalendar();

        if (date != null) {
            gregDate.setTime(date);
        }

        return new Publication(publicationDTO.getId(), user, publicationDTO.getContent(), gregDate, publicationDTO.getNblikes(), publicationDTO.getNbreports(), publicationDTO.getNbcomments());
    }

    public Comment mapToComment(CommentDTO commentDTO) {
        User user = new User(commentDTO.getUser(), commentDTO.getLastname(), commentDTO.getFirstname());

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH);
        Date date = null;

        try {
            date = df.parse(commentDTO.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar gregDate = new GregorianCalendar();

        if (date != null) {
            gregDate.setTime(date);
        }
        return new Comment(commentDTO.getId(), user, commentDTO.getContent(), gregDate);
    }

}

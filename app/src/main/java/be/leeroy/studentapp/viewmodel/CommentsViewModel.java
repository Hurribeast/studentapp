package be.leeroy.studentapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import be.leeroy.studentapp.dataaccess.PublicationDataAccess;
import be.leeroy.studentapp.dataaccess.mappers.PublicationMapper;
import be.leeroy.studentapp.models.Comment;
import be.leeroy.studentapp.models.Publication;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.services.RetrofitConfigurationService;

public class CommentsViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Comment>> _comments = new MutableLiveData<>();
    private final LiveData<List<Comment>> comments = _comments;

    private final MutableLiveData<Errors> _error = new MutableLiveData<>();
    private final LiveData<Errors> error = _error;

    private final PublicationDataAccess publicationDataAccess;
    private final PublicationMapper publicationMapper;


    public CommentsViewModel(@NonNull Application application) {
        super(application);
        this.publicationDataAccess = RetrofitConfigurationService.getInstance(application).publicationDataAccess();
        this.publicationMapper = PublicationMapper.getInstance();
    }
}

package be.leeroy.studentapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.leeroy.studentapp.dataaccess.PublicationDataAccess;
import be.leeroy.studentapp.dataaccess.dto.CommentDTO;
import be.leeroy.studentapp.dataaccess.mappers.PublicationMapper;
import be.leeroy.studentapp.exceptions.NoConnectivityException;
import be.leeroy.studentapp.models.Comment;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import be.leeroy.studentapp.utils.ApiUtils;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void loadComments(Integer publiId, String token) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("publiID", publiId);

        RequestBody requestBody = ApiUtils.ToRequestBody(body);

        publicationDataAccess.getCommentsFromAPublication(token, requestBody).enqueue(new Callback<List<CommentDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<CommentDTO>> call, @NonNull Response<List<CommentDTO>> response) {
                if(response.isSuccessful()) {
                    List<CommentDTO> commentsDTO = response.body();
                    List<Comment> comments = new ArrayList<>();

                    for(CommentDTO commentDTO : commentsDTO) {
                        comments.add(publicationMapper.mapToComment(commentDTO));
                    }

                    _comments.setValue(comments);
                    _error.setValue(null);
                } else if(response.code() == 401) {
                    _error.setValue(Errors.TOKEN_EXPIRED);
                } else {
                    _error.setValue(Errors.REQUEST_ERROR);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CommentDTO>> call, @NonNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _error.setValue(Errors.NO_CONNECTION);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }
        });
    }
    public void addComment(String content, Integer id, Integer publiID, String token) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("content", content);
        body.put("id", id);
        body.put("publiID", publiID);
        RequestBody requestBody = ApiUtils.ToRequestBody(body);
        publicationDataAccess.createPublication(token, requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    _error.setValue(null);
                } else if(response.code() == 401) {
                    _error.setValue(Errors.TOKEN_EXPIRED);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _error.setValue(Errors.NO_CONNECTION);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }
        });

    }
    public LiveData<Errors> getError() {
        return error;
    }
    public LiveData<List<Comment>> getComments() {
        return comments;
    }
}

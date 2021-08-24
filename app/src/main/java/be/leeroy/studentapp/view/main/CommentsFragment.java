package be.leeroy.studentapp.view.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentCommentsBinding;
import be.leeroy.studentapp.view.ExtendFragment;
import be.leeroy.studentapp.view.main.comment.CommentAdapter;
import be.leeroy.studentapp.viewmodel.CommentsViewModel;


public class CommentsFragment extends ExtendFragment {
    public CommentsFragment() {}

    private FragmentCommentsBinding binding;
    private CommentsViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCommentsBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(CommentsViewModel.class);

        Integer publiId = getArguments().getInt("publiId");

        CommentAdapter commentAdapter = new CommentAdapter(R.id.commentsFragment_to_profileFragment);
        RecyclerView commentsRv = binding.commentsRecyclerView;
        commentsRv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        commentsRv.setAdapter(commentAdapter);

        /* Content */
        binding.commentsContentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                binding.commentsPublishButton.setEnabled(charSequence.length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        /* Publish button */
        binding.commentsPublishButton.setOnClickListener(view -> {
            viewModel.addComment(binding.commentsContentEditText.getText().toString(), publiId, getBearerAuth());
        });

        /* Back button */
        binding.commentsBackButton.setOnClickListener(view -> {
            navigateToBackFragment();
            hideKeyboard(getView());
        });

        /* Load comments */
        viewModel.getComments().observe(getViewLifecycleOwner(), commentAdapter::setComments);

        /* New comment */
        viewModel.isCommented().observe(getViewLifecycleOwner(), commented -> {
            Log.d("debug", "commented : " + commented);

            if (commented) {
                binding.commentsContentEditText.setText("");
                viewModel.loadComments(publiId, getBearerAuth());
                hideKeyboard(getView());
                viewModel.setNotCommented();
            }
        });

        viewModel.getError().observe(getViewLifecycleOwner(), this::displayError);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        Integer publiId = getArguments().getInt("publiId");
        viewModel.loadComments(publiId, getBearerAuth());
    }
}
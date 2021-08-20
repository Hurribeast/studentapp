package be.leeroy.studentapp.view.main;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import be.leeroy.studentapp.databinding.FragmentFeedBinding;
import be.leeroy.studentapp.utils.PreferencesUtils;
import be.leeroy.studentapp.view.connection.ConnectionActivity;
import be.leeroy.studentapp.view.ExtendFragment;
import be.leeroy.studentapp.viewmodel.FeedViewModel;

public class FeedFragment extends ExtendFragment {

    public FeedFragment() {
    }

    private FragmentFeedBinding binding;
    private FeedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFeedBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(FeedViewModel.class);

        binding.feedLoadUserButton.setOnClickListener(view -> {
            viewModel.loadPublications("Bearer " + PreferencesUtils.getToken(getActivity()));
        /*    PreferencesUtils.removeToken(getActivity());
            navigateToActivity(ConnectionActivity.class); */
        });

        return binding.getRoot();
    }
}
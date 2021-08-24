package be.leeroy.studentapp.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentFeedBinding;
import be.leeroy.studentapp.utils.PreferencesUtils;
import be.leeroy.studentapp.view.ExtendFragment;
import be.leeroy.studentapp.view.main.publication.PublicationAdapter;
import be.leeroy.studentapp.viewmodel.FeedViewModel;

public class FeedFragment extends ExtendFragment {

    public FeedFragment() {}

    private FragmentFeedBinding binding;
    private FeedViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFeedBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(FeedViewModel.class);

        /* List Publications */
        PublicationAdapter publicationAdapter = new PublicationAdapter(R.id.feedFragment_to_profileFragment, R.id.feedFragment_to_commentsFragment);
        viewModel.getPublications().observe(getViewLifecycleOwner(), publications -> {
            publicationAdapter.setPublications(publications);
            binding.feedRefreshLayout.setRefreshing(false);
        });
        RecyclerView publicationsRv = binding.feedPublicationsRv;
        publicationsRv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        publicationsRv.setAdapter(publicationAdapter);

        /* Create publication */
        binding.feedCreatePublicationButton.setOnClickListener(view -> navigateToFragment(view, R.id.feedFragment_to_newPublicationFragment));

        /* My profile button */
        binding.feedProfileButton.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("user", PreferencesUtils.get("userEmail", requireActivity()));
            navigateToFragment(view, R.id.feedFragment_to_profileFragment, bundle);
        });

        /* Refresh publications */
        binding.feedRefreshLayout.setOnRefreshListener(() -> viewModel.loadPublications(getBearerAuth()));

        /* Errors */
        viewModel.getError().observe(getViewLifecycleOwner(), this::displayError);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.loadPublications(getBearerAuth());
    }
}
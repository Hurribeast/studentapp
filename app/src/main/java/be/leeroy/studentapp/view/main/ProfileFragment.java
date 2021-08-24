package be.leeroy.studentapp.view.main;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentProfileBinding;
import be.leeroy.studentapp.utils.PreferencesUtils;
import be.leeroy.studentapp.view.ExtendFragment;
import be.leeroy.studentapp.view.main.publication.PublicationAdapter;
import be.leeroy.studentapp.viewmodel.ProfileViewModel;

public class ProfileFragment extends ExtendFragment {

    public ProfileFragment() {
    }

    private FragmentProfileBinding binding;
    private ProfileViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        /* Create publication recyclerView */
        PublicationAdapter publicationAdapter = new PublicationAdapter(null, R.id.profileFragment_to_commentsFragment);
        RecyclerView publicationsRv = binding.profilePublicationsRv;
        publicationsRv.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        publicationsRv.setAdapter(publicationAdapter);

        /* Load user */
        String email = getArguments().getString("user");
        viewModel.loadUserProfile(email, getBearerAuth());

        /* Back button */
        binding.profileBackButton.setOnClickListener(view -> navigateToBackFragment());

        /* Settings button */
        binding.profileSettingsButton.setOnClickListener(view -> navigateToFragment(view, R.id.profileFragment_to_settingsFragment));

        /* Load all information */
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            String name = user.getFirstname() + " " + user.getLastname();
            binding.profileUserTitle.setText(name);

            /* Settings button */
            if(user.getEmail().equalsIgnoreCase(PreferencesUtils.get("userEmail", requireActivity()))){
                binding.profileSettingsButton.setVisibility(View.VISIBLE);
            }

            /* School info */
            if(user.getOption() == null) {
                binding.profileSchoolInfoLabel.setVisibility(View.GONE);
            } else {
                String schoolInfo = "Studying <i>" + user.getOption().getName() + "</i> at <b>" + user.getOption().getSchool().getName() + "</b>";
                binding.profileSchoolInfoLabel.setText(Html.fromHtml(schoolInfo));
            }

            /* Birthday info */
            String birthdayFormat = new SimpleDateFormat("d MMMM yyyy", Locale.FRENCH).format(user.getBirthday().getTime());
            binding.profileBirthdayLabel.setText(birthdayFormat);

            viewModel.loadUserPublications(user.getEmail(), getBearerAuth());

        });

        /* List Publications */
        viewModel.getUserPublications().observe(getViewLifecycleOwner(), publications -> {
            publicationAdapter.setPublications(publications);

            /* Display page */
            binding.profileLoading.setVisibility(View.GONE);
            binding.profilePage.setVisibility(View.VISIBLE);

        //    binding.feedRefreshLayout.setRefreshing(false);
        });


        /* Errors */
        viewModel.getError().observe(getViewLifecycleOwner(), this::displayError);

        return binding.getRoot();
    }
}
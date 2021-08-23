package be.leeroy.studentapp.view.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import be.leeroy.studentapp.databinding.FragmentProfileBinding;
import be.leeroy.studentapp.utils.PreferencesUtils;
import be.leeroy.studentapp.view.ExtendFragment;
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

        /* Load user */
        String email = getArguments().getString("user");
        viewModel.loadUserProfile(email, getBearerAuth());

        /* Back button */
        binding.profileBackButton.setOnClickListener(view -> navigateToBackFragment());

        /* Load all information */
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            String name = user.getFirstname() + " " + user.getLastname();
            binding.profileUserTitle.setText(name);

            if(user.getEmail().equalsIgnoreCase(PreferencesUtils.get("userEmail", requireActivity()))){
                binding.profileSettingsButton.setVisibility(View.VISIBLE);
            }

            /* Display page */
            binding.profileLoading.setVisibility(View.GONE);
            binding.profilePage.setVisibility(View.VISIBLE);
        });

        return binding.getRoot();
    }
}
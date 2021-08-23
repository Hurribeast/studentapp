package be.leeroy.studentapp.view.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentSettingsBinding;
import be.leeroy.studentapp.utils.PreferencesUtils;
import be.leeroy.studentapp.view.ExtendFragment;
import be.leeroy.studentapp.view.connection.ConnectionActivity;

public class SettingsFragment extends ExtendFragment {

    public SettingsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentSettingsBinding binding = FragmentSettingsBinding.inflate(inflater, container, false);

        binding.settingsBackButton.setOnClickListener(view -> navigateToBackFragment());

        binding.settingsChangePasswordButton.setOnClickListener(view -> navigateToFragment(view, R.id.settingsFragment_to_changePasswordFragment));

        binding.settingsChangeSchoolButton.setOnClickListener(view -> Toast.makeText(getActivity(), "Soon...", Toast.LENGTH_LONG).show());

        binding.settingsDisconnectButton.setOnClickListener(view -> {
            PreferencesUtils.remove("token", requireActivity());
            PreferencesUtils.remove("userEmail", requireActivity());

            navigateToActivity(ConnectionActivity.class);
        });

        return binding.getRoot();
    }
}
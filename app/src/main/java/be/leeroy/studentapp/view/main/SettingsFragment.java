package be.leeroy.studentapp.view.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentSettingsBinding;
import be.leeroy.studentapp.view.ExtendFragment;

public class SettingsFragment extends ExtendFragment {

    public SettingsFragment() {}

    private FragmentSettingsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }
}
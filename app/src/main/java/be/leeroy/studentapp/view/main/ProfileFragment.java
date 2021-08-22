package be.leeroy.studentapp.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.leeroy.studentapp.databinding.FragmentProfileBinding;
import be.leeroy.studentapp.view.ExtendFragment;

public class ProfileFragment extends ExtendFragment {

    public ProfileFragment() {
    }

    private FragmentProfileBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
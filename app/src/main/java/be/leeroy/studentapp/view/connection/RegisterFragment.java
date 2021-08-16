package be.leeroy.studentapp.view.connection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.leeroy.studentapp.databinding.FragmentRegisterBinding;

public class RegisterFragment extends ExtendFragment {

    public RegisterFragment() {
    }

    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        binding.registerFormBackButton.setOnClickListener(view -> navigateToBackFragment());

        return binding.getRoot();
    }
}
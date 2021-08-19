package be.leeroy.studentapp.view.connection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import be.leeroy.studentapp.databinding.FragmentRegisterBinding;
import be.leeroy.studentapp.viewmodel.LoginViewModel;
import be.leeroy.studentapp.viewmodel.RegisterViewModel;

public class RegisterFragment extends ExtendFragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel viewModel;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding.registerFormBackButton.setOnClickListener(view -> navigateToBackFragment());

        return binding.getRoot();
    }
}
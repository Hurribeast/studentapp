package be.leeroy.studentapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import be.leeroy.studentapp.databinding.FragmentErrorBinding;

public class ErrorFragment extends ExtendFragment {
    public ErrorFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentErrorBinding binding = FragmentErrorBinding.inflate(inflater, container, false);

        String errorMessage = getArguments().getString("error");
        binding.errorMessageLabel.setText(errorMessage);

        binding.errorRetryButton.setOnClickListener(view -> navigateToBackFragment());

        return binding.getRoot();
    }
}
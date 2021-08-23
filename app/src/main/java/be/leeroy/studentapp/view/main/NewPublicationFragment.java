package be.leeroy.studentapp.view.main;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentNewPublicationBinding;
import be.leeroy.studentapp.view.ExtendFragment;
import be.leeroy.studentapp.viewmodel.NewPublicationViewModel;


public class NewPublicationFragment extends ExtendFragment {

    public NewPublicationFragment() {
    }

    private FragmentNewPublicationBinding binding;
    private NewPublicationViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNewPublicationBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(NewPublicationViewModel.class);

        /* Back button */
        binding.newPublicationBackButton.setOnClickListener(view -> {
            hideKeyboard(view);
            navigateToBackFragment();
        });

        /* Publish button */
        binding.newPublicationPublishButton.setOnClickListener(view -> {
            viewModel.publishPublication(binding.newPublicationContentEditText.getText().toString(), getBearerAuth());
        });

        /* Content */
        binding.newPublicationContentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                binding.newPublicationPublishButton.setEnabled(charSequence.length() != 0);

                String nbCharText = charSequence.length() + "/100";
                binding.newPublicationMaxCharText.setText(nbCharText);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        viewModel.isPublished().observe(getViewLifecycleOwner(), published -> {
            navigateToBackFragment();
        });
        viewModel.getError().observe(getViewLifecycleOwner(), this::displayError);

        return binding.getRoot();
    }
}
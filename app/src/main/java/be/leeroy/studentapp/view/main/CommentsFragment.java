package be.leeroy.studentapp.view.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentCommentsBinding;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import be.leeroy.studentapp.viewmodel.CommentsViewModel;


public class CommentsFragment extends Fragment {

    private FragmentCommentsBinding binding;
    private CommentsViewModel viewModel;


    public CommentsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCommentsBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(CommentsViewModel.class);

        return binding.getRoot();
    }
}
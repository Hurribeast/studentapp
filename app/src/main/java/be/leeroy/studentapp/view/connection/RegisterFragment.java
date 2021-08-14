package be.leeroy.studentapp.view.connection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
    }

    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        binding.registerFormBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.registerFragment_to_loginFragment);
            }
        });

        return binding.getRoot();
    }
}
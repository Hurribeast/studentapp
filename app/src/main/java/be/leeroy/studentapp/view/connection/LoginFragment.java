package be.leeroy.studentapp.view.connection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    public LoginFragment() {
    }

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        binding.loginFormRegisterHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.loginFragment_to_registerFragment);
            }
        });

        return binding.getRoot();
    }
}
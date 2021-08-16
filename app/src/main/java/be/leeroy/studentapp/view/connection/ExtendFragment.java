package be.leeroy.studentapp.view.connection;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.models.NetworkError;

public class ExtendFragment extends Fragment {

    public void displayError(NetworkError error) {
        if(error != null) {
            Toast.makeText(getActivity(), error.getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void navigateToActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void navigateToFragment(View view, @IdRes int resId){
        Navigation.findNavController(view).navigate(resId);
    }

    public void navigateToBackFragment(){
        getActivity().onBackPressed();
    }

}

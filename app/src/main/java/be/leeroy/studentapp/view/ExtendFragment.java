package be.leeroy.studentapp.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.utils.PreferencesUtils;

public class ExtendFragment extends Fragment {

    public void displayError(Errors error) {
        if(error != null) {
            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
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

    public void hideKeyboard(View view){
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public String getBearerAuth(){
        return "Bearer " + PreferencesUtils.getToken(getActivity());
    }

}

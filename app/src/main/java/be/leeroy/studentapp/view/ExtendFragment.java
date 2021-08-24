package be.leeroy.studentapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.utils.PreferencesUtils;
import be.leeroy.studentapp.view.connection.ConnectionActivity;

public class ExtendFragment extends Fragment {

    public void displayError(Errors error) {
        if (error == null) return;

        if(error == Errors.TOKEN_EXPIRED){
            Toast.makeText(getActivity(), getString(R.string.error_reconnect), Toast.LENGTH_LONG).show();
            navigateToActivity(ConnectionActivity.class);
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("error", error.getMessage());

        navigateToFragment(getView(), R.id.errorFragment, bundle);
    }

    public void navigateToActivity(Class<?> cls) {
        Intent intent = new Intent(getActivity(), cls);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        requireActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void navigateToFragment(View view, @IdRes int resId){
        this.navigateToFragment(view, resId, null);
    }

    public void navigateToFragment(View view, @IdRes int resId, Bundle bundle){
        Navigation.findNavController(view).navigate(resId, bundle);
    }


    public void navigateToBackFragment(){
        requireActivity().onBackPressed();
    }

    public void hideKeyboard(View view){
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public String getBearerAuth(){
        return "Bearer " + PreferencesUtils.get("token", requireActivity());
    }

}

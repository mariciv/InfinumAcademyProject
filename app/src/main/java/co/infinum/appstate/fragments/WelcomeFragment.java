package co.infinum.appstate.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.infinum.appstate.R;
import co.infinum.appstate.interfaces.LoginListener;

/**
 * Created by Ivan on 09/07/15.
 */
public class WelcomeFragment extends Fragment {

    private View goToLogin;

    private LoginListener loginListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof LoginListener) {
            loginListener = (LoginListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goToLogin = view.findViewById(R.id.welcome);
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginListener != null) {
                    loginListener.onNavigateToLogin();
                }
            }
        });
    }
}

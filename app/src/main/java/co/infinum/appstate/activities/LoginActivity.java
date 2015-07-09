package co.infinum.appstate.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import co.infinum.appstate.R;
import co.infinum.appstate.fragments.LoginFragment;
import co.infinum.appstate.fragments.WelcomeFragment;
import co.infinum.appstate.interfaces.LoginListener;

public class LoginActivity extends Activity implements LoginListener {

    private static final String SHOW_COUNT = "SHOW COUNT";

    private static final String WELCOME_VISIBILITY = "WELCOME VISIBILITY";

    private int showCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fragments);

        if (!PreferenceManager.getDefaultSharedPreferences(this).getString("USERNAME", "").isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return; // Not to show toast message
        }

        if (savedInstanceState != null) {
            showCount = savedInstanceState.getInt(SHOW_COUNT);
        } else {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.container, new WelcomeFragment());
            ft.commit();
        }

        // Just for demonstration
        showCount++;
        Toast.makeText(this, "Login show count " + showCount, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SHOW_COUNT, showCount);
    }

    @Override
    public void onNavigateToLogin() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, new LoginFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}

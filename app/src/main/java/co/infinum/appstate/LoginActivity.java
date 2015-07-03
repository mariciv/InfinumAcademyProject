package co.infinum.appstate;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String SHOW_COUNT = "SHOW COUNT";

    private static final String WELCOME_VISIBILITY = "WELCOME VISIBILITY";

    private TextWatcher textChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!passwordEditText.getText().toString().isEmpty() && !usernameEditText.getText().toString().isEmpty()) {
                loginButton.setEnabled(true);
            } else {
                loginButton.setEnabled(false);
            }
        }
    };

    private View welcome;

    private View loginLayout;

    private View welcomeLayout;

    private EditText passwordEditText;

    private EditText usernameEditText;

    private Button loginButton;

    private CheckBox remeberMe;

    private int showCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (!PreferenceManager.getDefaultSharedPreferences(this).getString("USERNAME", "").isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return; // Not to show toast message
        }

        // Inject views
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        loginButton = (Button) findViewById(R.id.login_button);
        welcome = findViewById(R.id.welcome);
        loginLayout = findViewById(R.id.login_layout);
        welcomeLayout = findViewById(R.id.welcome_layout);
        remeberMe = (CheckBox) findViewById(R.id.remember_check_box);

        // Setup text watchers
        passwordEditText.addTextChangedListener(textChange);
        usernameEditText.addTextChangedListener(textChange);

        // Set click listeners
        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLayout.setVisibility(View.VISIBLE);
                welcomeLayout.setVisibility(View.GONE);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (remeberMe.isChecked()) {
                    PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit()
                            .putString("USERNAME", usernameEditText.getText().toString()).apply();
                }

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("USERNAME", usernameEditText.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        if (savedInstanceState != null) {
            showCount = savedInstanceState.getInt(SHOW_COUNT);
            if (savedInstanceState.getBoolean(WELCOME_VISIBILITY, true)) {
                welcomeLayout.setVisibility(View.VISIBLE);
                loginLayout.setVisibility(View.GONE);
            } else {
                welcomeLayout.setVisibility(View.GONE);
                loginLayout.setVisibility(View.VISIBLE);
            }
        }

        // Just for demonstration
        showCount++;
        Toast.makeText(this, "Login show count " + showCount, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SHOW_COUNT, showCount);
        outState.putBoolean(WELCOME_VISIBILITY, welcomeLayout.isShown());
    }
}

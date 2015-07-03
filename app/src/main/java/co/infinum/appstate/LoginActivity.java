package co.infinum.appstate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

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
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("USERNAME", usernameEditText.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        // Just for demonstration
        showCount++;
        Toast.makeText(this, "Login show count " + showCount, Toast.LENGTH_SHORT).show();
    }
}

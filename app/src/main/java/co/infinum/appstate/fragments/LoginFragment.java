package co.infinum.appstate.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import co.infinum.appstate.R;
import co.infinum.appstate.activities.MainActivity;

/**
 * Created by Ivan on 09/07/15.
 */
public class LoginFragment extends Fragment {

    private EditText passwordEditText;

    private EditText usernameEditText;

    private Button loginButton;

    private CheckBox remeberMe;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        passwordEditText = (EditText) view.findViewById(R.id.password_edit_text);
        usernameEditText = (EditText) view.findViewById(R.id.username_edit_text);
        loginButton = (Button) view.findViewById(R.id.login_button);
        remeberMe = (CheckBox) view.findViewById(R.id.remember_check_box);

        // Setup text watchers
        passwordEditText.addTextChangedListener(textChange);
        usernameEditText.addTextChangedListener(textChange);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                        .putString("USERNAME", usernameEditText.getText().toString()).apply();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("USERNAME", usernameEditText.getText().toString());
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}

package co.infinum.appstate;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Ivan on 02/07/15.
 */
public class MainActivity extends AppCompatActivity {

    private TextView usernameTextView;

    private String username = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Inject views
        usernameTextView = (TextView) findViewById(R.id.username_text);

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey("USERNAME")) {
            username = getIntent().getStringExtra("USERNAME");
        } else {
            username = PreferenceManager.getDefaultSharedPreferences(this).getString("USERNAME", "");
        }

        usernameTextView.setText(username);
    }
}

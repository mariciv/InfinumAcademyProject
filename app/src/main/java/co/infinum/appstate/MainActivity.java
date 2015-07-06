package co.infinum.appstate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ivan on 02/07/15.
 */
public class MainActivity extends AppCompatActivity {

    private TextView usernameTextView;

    private ImageView selfieImageView;

    private Button listExampleBtn;

    private String username = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Inject views
        usernameTextView = (TextView) findViewById(R.id.username_text);
        selfieImageView = (ImageView) findViewById(R.id.selfie_image_view);
        listExampleBtn = (Button) findViewById(R.id.list_example_btn);

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey("USERNAME")) {
            username = getIntent().getStringExtra("USERNAME");
        }

        usernameTextView.setText(username);
    }
}

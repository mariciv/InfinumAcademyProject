package co.infinum.appstate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ivan on 02/07/15.
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1010;

    private TextView usernameTextView;

    private String username = "";

    private ImageView selfieImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Inject views
        usernameTextView = (TextView) findViewById(R.id.username_text);
        selfieImageView = (ImageView) findViewById(R.id.selfie_image_view);

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey("USERNAME")) {
            username = getIntent().getStringExtra("USERNAME");
        } else {
            username = PreferenceManager.getDefaultSharedPreferences(this).getString("USERNAME", "");
        }
        usernameTextView.setText(username);

        selfieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            selfieImageView.setImageBitmap(imageBitmap);
        }
    }
}

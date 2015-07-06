package co.infinum.appstate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Ivan on 02/07/15.
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1010;

    private TextView usernameTextView;

    private String username = "";

    private ImageView selfieImageView;

    private Bitmap imageBitmap;

    private Button listExampleBtn;

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
        } else {
            username = PreferenceManager.getDefaultSharedPreferences(this).getString("USERNAME", "");
        }
        usernameTextView.setText(username);

        decodeSelfieBitmap();
        if (imageBitmap != null) {
            selfieImageView.setImageBitmap(imageBitmap);
        }

        selfieImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File selfiePhoto = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String imageName = "selfie.jpg";
                File image = new File(selfiePhoto, imageName);

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    if (image != null) {
                        Log.d("LOG", "File: " + image.getAbsolutePath());
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
                    }
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }

            }
        });

        listExampleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecycleActivity.class));
            }
        });
    }

    private void decodeSelfieBitmap() {
        String imagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/selfie.jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = 8;
        imageBitmap = BitmapFactory.decodeFile(imagePath, options);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data == null) {
                decodeSelfieBitmap();
            } else {
                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
            }

            if (imageBitmap != null) {
                selfieImageView.setImageBitmap(imageBitmap);
            }

        }
    }
}

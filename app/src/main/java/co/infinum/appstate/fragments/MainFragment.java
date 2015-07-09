package co.infinum.appstate.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import co.infinum.appstate.R;
import co.infinum.appstate.RecycleActivity;

/**
 * Created by Ivan on 09/07/15.
 */
public class MainFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1010;

    private TextView usernameTextView;

    private String username = "";

    private ImageView selfieImageView;

    private Bitmap imageBitmap;

    private Button listExampleBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Inject views
        usernameTextView = (TextView) view.findViewById(R.id.username_text);
        selfieImageView = (ImageView) view.findViewById(R.id.selfie_image_view);
        listExampleBtn = (Button) view.findViewById(R.id.list_example_btn);

        username = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("USERNAME", "");

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
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
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
                startActivity(new Intent(getActivity(), RecycleActivity.class));
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
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

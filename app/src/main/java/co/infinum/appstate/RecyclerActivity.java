package co.infinum.appstate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Ivan on 06/07/15.
 */
public class RecyclerActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

}

package co.infinum.appstate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by Ivan on 06/07/15.
 */
public class ListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.list_view);
    }
}

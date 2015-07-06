package co.infinum.appstate;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ivan on 03/07/15.
 */
public class ListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(new NameAdapter(this, "Infinum student academy android"));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this, "Clicked item " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class NameAdapter extends BaseAdapter {

        private final Context context;

        private char[] charArray;

        public NameAdapter(Context context, String name) {

            charArray = name.toCharArray();
            this.context = context;
        }

        @Override
        public int getCount() {
            return charArray.length;
        }

        @Override
        public Character getItem(int position) {
            return charArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
                viewHolder = new ViewHolder(convertView);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(getItem(position).toString());
            return convertView;
        }

        public class ViewHolder {

            TextView textView;

            public ViewHolder(View view) {
                textView = (TextView) view.findViewById(R.id.list_item_text);
                view.setTag(this);
            }

        }


    }
}

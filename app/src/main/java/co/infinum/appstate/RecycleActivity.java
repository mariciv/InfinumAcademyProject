package co.infinum.appstate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ivan on 03/07/15.
 */
public class RecycleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NameAdapter("Infinum academy android"));

    }

    public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder> {

        private char[] charArray;

        public NameAdapter(String name) {
            charArray = name.toCharArray();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
            View convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
            return new ViewHolder(convertView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.textView.setText(String.valueOf(charArray[position]));
            viewHolder.textView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return charArray.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                textView = (TextView) itemView.findViewById(R.id.list_item_text);
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(textView.getContext(), "Clicked item " + textView.getTag(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}

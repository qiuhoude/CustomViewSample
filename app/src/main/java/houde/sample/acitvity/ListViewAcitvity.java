package houde.sample.acitvity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import houde.sample.R;
import houde.sample.utils.Cheeses;
import houde.sample.widget.ResilientListView;

public class ListViewAcitvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View headView = inflater.inflate(R.layout.view_header, null);
        final ImageView imageView = (ImageView) headView.findViewById(R.id.iv);
        final ResilientListView lv = (ResilientListView) findViewById(R.id.lv);
        lv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                lv.setParallaxImage(imageView);
            }
        });


        lv.addHeaderView(headView);

        lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, Cheeses.NAMES) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view;
                text.setTextColor(Color.BLACK);
                return view;
            }
        });

    }
}

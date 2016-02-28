package houde.sample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import houde.sample.acitvity.DragViewGroupActivity;
import houde.sample.acitvity.ListViewAcitvity;
import houde.sample.widget.TabPageIndicator;


public class MainActivity extends ListActivity {


    private List<String> listDatas;
    private List<Class> listACtivty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Black_NoTitleBar);
        fillList();
        fillActivity();
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>
                        (getApplicationContext(),
                                android.R.layout.simple_list_item_1, listDatas);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), listACtivty.get(position));
                startActivity(intent);
            }
        });

    }

    private void fillActivity() {
        listACtivty = new ArrayList<>();
        listACtivty.add(DragViewGroupActivity.class);
        listACtivty.add(ListViewAcitvity.class);
        listACtivty.add(TabPageIndicator.class);
    }

    private void fillList() {
        listDatas = new ArrayList<>();
        listDatas.add("DragViewGroupActivity");
        listDatas.add("ListViewAcitvity");
        listDatas.add("TabPageIndicator");
    }


}

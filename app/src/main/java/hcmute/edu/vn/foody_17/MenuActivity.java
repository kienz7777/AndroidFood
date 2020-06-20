package hcmute.edu.vn.foody_17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<Menu>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listView = (ExpandableListView) findViewById(R.id.exp_Menu);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);

    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Bò Mỹ Nhúng Ớt");
        listDataHeader.add("Bún Đậu");
        listDataHeader.add("Món Thêm");

        List<Menu> bo = new ArrayList<>();

        bo.add(new Menu("Bò Mỹ Nhúng Ớt Vừa","119,000"));
        bo.add(new Menu("Bò Mỹ Nhúng Ớt Vừa","119,000"));


        listHash.put(listDataHeader.get(0),bo);

    }
}

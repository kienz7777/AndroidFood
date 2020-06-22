package hcmute.edu.vn.foody_17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    //List<MenuGroup> menuGroups;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<Menu>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Intent intent = getIntent();
        int id = intent.getIntExtra("IdFood",0);
        //String id1 = Integer.toString(id);
        //Toast.makeText(getApplicationContext(),id1, Toast.LENGTH_SHORT).show();

        //menuGroups = DatabaseAccess.getInstance(MenuActivity.this).getListMenuGroup(id);

        listView = (ExpandableListView) findViewById(R.id.exp_Menu);
        initData(id);


        //Toast.makeText(getApplicationContext(),menuGroups.get(0).getNameGroup(), Toast.LENGTH_SHORT).show();


        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);


    }

    private void initData(int id) {
        List<MenuGroup> menuGroups;
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        menuGroups = DatabaseAccess.getInstance(MenuActivity.this).getListMenuGroup(id);

        for(int x = 0; x < menuGroups.size(); x++){
            listDataHeader.add(menuGroups.get(x).getNameGroup().toString());
        }

//        listDataHeader.add("Bò Mỹ Nhúng Ớt");
//        listDataHeader.add("Bún Đậu");
//        listDataHeader.add("Món Thêm");

        List<Menu> bo = new ArrayList<>();

        bo.add(new Menu("Bò Mỹ Nhúng Ớt Vừa","119,000"));
        bo.add(new Menu("Bò Mỹ Nhúng Ớt Vừa","119,000"));


        listHash.put(listDataHeader.get(0),bo);

    }
}

package hcmute.edu.vn.foody_17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    List<Menu> menu;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<Menu>> listHash;

    TextView txt_Anh,txt_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Intent intent = getIntent();
        final int idf = intent.getIntExtra("IdFood",0);

        txt_Name = (TextView) findViewById(R.id.txt_Name);
        txt_Name.setText(intent.getStringExtra("Title"));
        //String id1 = Integer.toString(id);
        //Toast.makeText(getApplicationContext(),id1, Toast.LENGTH_SHORT).show();

        //menu = DatabaseAccess.getInstance(MenuActivity.this).getListMenuItem(1);

        listView = (ExpandableListView) findViewById(R.id.exp_Menu);
        initData(idf);


        //Toast.makeText(getApplicationContext(),menuGroups.get(0).getNameGroup(), Toast.LENGTH_SHORT).show();


        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);


        //Image
        txt_Anh = (TextView) findViewById(R.id.txt_Anh);
        txt_Anh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,ImageGroupActivity.class);
                intent.putExtra("IdFood",idf);
                startActivity(intent);
            }
        });

    }

    private void initData(int id) {
        List<MenuGroup> menuGroups;
        List<Menu> menu;
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        menuGroups = DatabaseAccess.getInstance(MenuActivity.this).getListMenuGroup(id);

        for(int x = 0; x < menuGroups.size(); x++){
            listDataHeader.add(menuGroups.get(x).getNameGroup().toString());
        }

//        listDataHeader.add("Bò Mỹ Nhúng Ớt");
//        listDataHeader.add("Bún Đậu");
//        listDataHeader.add("Món Thêm");

//        List<Menu> bo = new ArrayList<>();
//
//        bo.add(new Menu("Bò Mỹ Nhúng Ớt Vừa","119,000"));
//        bo.add(new Menu("Bò Mỹ Nhúng Ớt Vừa","119,000"));


        for(int y = 0; y < menuGroups.size(); y++){

            menu = DatabaseAccess.getInstance(MenuActivity.this).getListMenuItem(menuGroups.get(y).getIdMenuGroup());

            if(menu == null){
                return;
            }
            listHash.put(listDataHeader.get(y),menu);

        }

//        listHash.put(listDataHeader.get(0),bo);

    }
}

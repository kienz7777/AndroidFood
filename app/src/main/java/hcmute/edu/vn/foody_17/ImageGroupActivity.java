package hcmute.edu.vn.foody_17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class ImageGroupActivity extends AppCompatActivity {

    List<MenuGroup> menuGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_group);

        Intent intent = getIntent();
        int id = intent.getIntExtra("IdFood",0);

        menuGroups = DatabaseAccess.getInstance(ImageGroupActivity.this).getListMenuGroup(id);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapterImageGroup recycleViewAdapter = new RecyclerViewAdapterImageGroup(this,menuGroups);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(recycleViewAdapter);
    }


}

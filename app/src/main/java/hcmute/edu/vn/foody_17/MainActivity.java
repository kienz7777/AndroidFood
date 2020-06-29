package hcmute.edu.vn.foody_17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Food> lstFood;
    TextView txtProvince;
    EditText edt_Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lstFood = DatabaseAccess.getInstance(MainActivity.this).getListFood();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter recycleViewAdapter = new RecyclerViewAdapter(this,lstFood);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(recycleViewAdapter);


        txtProvince = (TextView) findViewById(R.id.txt_Province);
        txtProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProvicesActivity.class);
                startActivity(intent);
            }

        });

        //set text cho tỉnh
//        Intent i = getIntent();
//        if(i.getStringExtra("data") != null){
//            txtProvince.setText(i.getStringExtra("data"));
//
//        }

        //set text cho tỉnh
        SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String datas = sharedPref.getString("data", "Đồng Nai");

        if(datas != null){
            txtProvince.setText(datas);

        }

        edt_Search = (EditText) findViewById(R.id.search_Index);
        edt_Search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                    intent.putExtra("food",edt_Search.getText().toString().trim());
                    intent.putExtra("province",txtProvince.getText().toString().trim());  //
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });


    }

}

package hcmute.edu.vn.foody_17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
    TextView txtProvince, txtProvincess;
    EditText edt_Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        lstFood = new ArrayList<>();
//        lstFood.add(new Food("Bánh Mì","Category Book","Mã khuyến mại, có người mới đặt giao tận nơi",R.drawable.banhmi));
//        lstFood.add(new Food("Trà Sữa Lu","Category Book","Mã khuyến mại, có người mới đặt giao tận nơi",R.drawable.trasualu));
//        lstFood.add(new Food("Bún riêu cô bảo","Category Book","Mã khuyến mại, có người mới đặt giao tận nơi",R.drawable.bunrieu));
//        lstFood.add(new Food("Heo cuộn sốt me","Category Book","Mã khuyến mại, có người mới đặt giao tận nơi",R.drawable.heocuon));
//        lstFood.add(new Food("Chân gà chiên nước nắm","Category Book","Mã khuyến mại, có người mới đặt giao tận nơi",R.drawable.changachien));
//        lstFood.add(new Food("Ngũ ốc cô phú","Category Book","Mã khuyến mại, có người mới đặt giao tận nơi",R.drawable.nguoc));
//        lstFood.add(new Food("Cháo hành chí phèo","Category Book","Description",R.drawable.chao));
//        lstFood.add(new Food("Pizza Kienz","Category Book","Mã khuyến mại, có người mới đặt giao tận nơi",R.drawable.piza));

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
        Intent i = getIntent();
        if(i.getStringExtra("data") != null){
            txtProvince.setText(i.getStringExtra("data"));

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

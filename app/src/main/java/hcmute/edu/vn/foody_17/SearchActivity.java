package hcmute.edu.vn.foody_17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    List<Food> lstSearch;
    EditText edt_Search;
    TextView txt_Provincess;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        lstSearch = new ArrayList<>();
//        lstSearch.add(new SearchFood("Bánh mì","A94B Hùng Vương, Thị xã Long Khánh, Đồng Nai","Quán ăn","Đồng Nai",R.drawable.banhmi));
//        lstSearch.add(new SearchFood("Bánh mì pewpew","A94B Hùng Vương, Thị xã Long Khánh, TP. HCM","Quán ăn","TP. HCM",R.drawable.banhmi));
//        lstSearch.add(new SearchFood("Trà sữa Lu","A94B Hùng Vương, Thị xã Long Khánh, Đồng Nai","Quán ăn","Đồng Nai",R.drawable.trasualu));
//        lstSearch.add(new SearchFood("Trà sữa Lu","A94B Hùng Vương, Thị xã Long Khánh, Tp. HCM","Quán ăn","TP. HCM",R.drawable.trasualu));
//        lstSearch.add(new SearchFood("Bún riêu cô Bảo","A94B Hùng Vương, Thị xã Long Khánh, Đồng Nai","Quán ăn","Đồng Nai",R.drawable.bunrieu));
//        lstSearch.add(new SearchFood("Heo cuộn sốt me","A94B Hùng Vương, Thị xã Long Khánh, Đồng Nai","Quán ăn","Đồng Nai",R.drawable.heocuon));
//        lstSearch.add(new SearchFood("Chân gà chiên nước nắm","A94B Hùng Vương, Thị xã Long Khánh, Đồng Nai","Quán ăn","Đồng Nai",R.drawable.changachien));
//        lstSearch.add(new SearchFood("Ngũ ốc cô phú","A94B Hùng Vương, Thị xã Long Khánh, Đồng Nai","Quán ăn","Đồng Nai",R.drawable.nguoc));
//        lstSearch.add(new SearchFood("Cháo hành chí phèo","A94B Hùng Vương, Thị xã Long Khánh, Đồng Nai","Quán ăn","Đồng Nai",R.drawable.chao));
//        lstSearch.add(new SearchFood("Pizza kienz","A94B Hùng Vương, Thị xã Long Khánh, Đồng Nai","Quán ăn","Đồng Nai",R.drawable.piza));

        lstSearch = DatabaseAccess.getInstance(SearchActivity.this).getListFood();


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_idss);
        final RecyclerViewAdapterSearch recycleViewAdapter = new RecyclerViewAdapterSearch(this,lstSearch);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(recycleViewAdapter);


        Intent i = getIntent();

        //Set textView cho tỉnh
        txt_Provincess = (TextView) findViewById(R.id.txt_Provincess);
        if(i.getStringExtra("province") != null){
           txt_Provincess.setText(i.getStringExtra("province"));
        }

        edt_Search = (EditText) findViewById(R.id.edt_Searched);
        //set text cho search
        if(i.getStringExtra("food") != null){
            edt_Search.setText(i.getStringExtra("food"));
        }


        //Xóa các food khác tỉnh
        for (int x = 0; x < lstSearch.size(); x++){
           if(!txt_Provincess.getText().toString().equals(lstSearch.get(x).getProvince())){
                lstSearch.remove(x);
                x--;
            }

        }


        //Toast.makeText(getApplicationContext(),String.valueOf(c),Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),txt_Provincess.getText().toString().getClass().getName(),Toast.LENGTH_SHORT).show();
        //Search success
        recycleViewAdapter.getFilter().filter(edt_Search.getText().toString());

        edt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recycleViewAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Nút back
        back = (ImageView) findViewById(R.id.imgv_Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

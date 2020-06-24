package hcmute.edu.vn.foody_17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ProvicesActivity extends AppCompatActivity {

    //ListView listView;
    List<Province> lstProvince;
    TextView txt_Remove,txt_Data;
    TextView txt_ChooseProvince;
    EditText search_Province;
    String choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provices);


        lstProvince = DatabaseAccess.getInstance(ProvicesActivity.this).getListProvince();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_pro);
        final RecyclerViewAdapterProvince recycleViewAdapter = new RecyclerViewAdapterProvince (this,lstProvince);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(recycleViewAdapter);


        txt_ChooseProvince = findViewById(R.id.txt_ChooseProvince);
        txt_ChooseProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProvicesActivity.this,MainActivity.class);
                //intent.putExtra("data",txt_Data.getText().toString());
                startActivity(intent);
            }
        });

        //Search success
        search_Province = (EditText) findViewById(R.id.search_Province);
        recycleViewAdapter.getFilter().filter(search_Province.getText().toString());

        search_Province.addTextChangedListener(new TextWatcher() {
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

        txt_Remove = findViewById(R.id.txt_Remove);
        txt_Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProvicesActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    
    
}

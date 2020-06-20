package hcmute.edu.vn.foody_17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProvicesActivity extends AppCompatActivity {

    ListView listView;
    TextView txt_Remove;
    TextView txt_ChooseProvince;
    String choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provices);

        listView = findViewById(R.id.list_Province);

        txt_Remove = findViewById(R.id.txt_Remove);
        txt_Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProvicesActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        String province1 = "TP. HCM";
        String province2 = "Hà Nội";
        String province3 = "Đà Nẵng";
        String province4 = "Hải Phòng";
        String province5 = "Cần Thơ";
        String province6 = "Đồng Nai";
        String province7 = "Vũng Tàu";
        String province8 = "An Giang";
        String province9 = "Bạc Liêu";
        String province10 = "Bắc Kạn";
        String province11 = "Bắc Giang";
        String province12 = "Bắc Ninh";
        String province13 = "Bến Tre";
        String province14 = "Bình Dương";
        String province15 = "Bình Định";
        String province16 = "Bình Phước";
        String province17 = "Bình Thuận";
        String province18 = "Cà Mau";
        String province19 = "Cao Bằng";
        String province20 = "Đắk Lắk";
        String province21 = "Đắk Nông";
        String province22 = "Điện Biên";
        String province23 = "Đồng Tháp";
        String province24 = "Gia Lai";
        String province25 = "Hà Giang";
        String province26 = "Hà Nam";
        String province27 = "Hà Tĩnh";
        String province28 = "Hải Dương";
        String province29 = "Hậu Giang";
        String province30 = "Hòa Bình";
        String province31 = "Hưng Yên";
        String province32 = "Khánh Hòa";
        String province33 = "Kiên Giang";
        String province34 = "Kon Tum";
        String province35 = "Lai Châu";
        String province36 = "Lạng Sơn";
        String province37 = "Lào Cai";
        String province38 = "Lâm Đồng";
        String province39 = "Long An";
        String province40 = "Nam Định";
        String province41 = "Nghệ An";
        String province42 = "Ninh Bình";
        String province43 = "Ninh Thuận";
        String province44 = "Phú Thọ";
        String province45 = "Phú Yên";
        String province46 = "Quảng Bình";
        String province47 = "Quảng Nam";
        String province48 = "Quảng Ngãi";
        String province49 = "Quảng Ninh";
        String province50 = "Quảng Trị";
        String province51 = "Sóc Trăng";
        String province52 = "Sơn La";
        String province53 = "Tây Ninh";
        String province54 = "Thái Bình";
        String province55 = "Thái Nguyên";
        String province56 = "Thanh Hóa";
        String province57 = "Huế";
        String province58 = "Tiền Giang";
        String province59 = "Trà Vinh";
        String province60 = "Tuyên Quang";
        String province61 = "Vĩnh Long";
        String province62 = "Vĩnh Phúc";
        String province63 = "Yên Bái";
        String province64 = "Phú Quốc";



        String[] province = new String[]{province1, province2, province3, province4, province5, province6, province7, province8, province9, province10,
                province11, province12, province13, province14, province15, province16, province17, province18, province19, province20,
                province21, province22, province23, province24, province25, province26, province27, province28, province29, province30,
                province31, province32, province33, province34, province35, province36, province37, province38, province39, province40,
                province41, province42, province43, province44, province45, province46, province47, province48, province49, province50,
                province51, province52, province53, province54, province55, province56, province57, province58, province59, province60,
                province61, province62, province63, province64};

        //Hiển thị list
        final ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,province);
        listView.setAdapter(itemsAdapter);

        //Lưu giá trị item khi click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               choose =  itemsAdapter.getItem(position);

            }
        });

        //Chuyển dữ liệu item đã click qua main
        txt_ChooseProvince = findViewById(R.id.txt_ChooseProvince);
        txt_ChooseProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProvicesActivity.this,MainActivity.class);
                intent.putExtra("data",choose);
                startActivity(intent);
            }
        });
    }
    
    
}

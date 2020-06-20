package hcmute.edu.vn.foody_17;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    TextView txt_Name,txt_Address,txt_ProvinceShow,txt_AddWifi,txt_Money;
    TextView txt_Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txt_Name = (TextView) findViewById(R.id.txt_NameStore);
        txt_Address = (TextView) findViewById(R.id.txt_Address);
        txt_ProvinceShow = (TextView) findViewById(R.id.txt_ProvinceShow);
        txt_Money = (TextView) findViewById(R.id.txt_Money);

        // Recieve data
        Intent intent = getIntent();
        //String Title = intent.getExtras().getString("Title");

        // Setting values
        txt_Name.setText(intent.getStringExtra("Title"));
        txt_Address.setText(intent.getStringExtra("Address"));
        txt_ProvinceShow.setText(intent.getStringExtra("Province"));
        txt_Money.setText(intent.getStringExtra("Price"));


        //Menu
        txt_Menu = (TextView) findViewById(R.id.txt_Menu);
        txt_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        });


        //Wifi
        txt_AddWifi = (TextView) findViewById(R.id.txt_AddWifi);
        txt_AddWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DetailActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_wifi,null);
                final EditText mAccount = (EditText) mView.findViewById(R.id.edt_account);        // lấy id từ layout dialog(layout khác)
                final EditText mPassword = (EditText) mView.findViewById(R.id.edt_password);
                Button mUpdate = (Button) mView.findViewById(R.id.btn_update);

                mUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!mAccount.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()){
                            Toast.makeText(DetailActivity.this,"Update Successful",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(DetailActivity.this,"Update failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }
}

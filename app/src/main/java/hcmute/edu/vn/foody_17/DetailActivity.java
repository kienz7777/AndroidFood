package hcmute.edu.vn.foody_17;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {

    TextView txt_Name,txt_Address,txt_ProvinceShow,txt_AddWifi,txt_Money;
    TextView txt_Menu;
    TextView txt_Distance;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION = 1;

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
        final int id = intent.getIntExtra("IdFood",0);

        txt_Menu = (TextView) findViewById(R.id.txt_Menu);
        txt_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,MenuActivity.class);
                intent.putExtra("IdFood",id);
                intent.putExtra("Title",txt_Name.getText().toString());
                startActivity(intent);
            }
        });

//        String id1 = Integer.toString(id);
//        Toast.makeText(getApplicationContext(),id1,Toast.LENGTH_SHORT).show();

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


        //Distance

        txt_Distance = (TextView) findViewById(R.id.txt_Distance);
        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        double lat = intent.getDoubleExtra("Lat",0.0);
        double longi = intent.getDoubleExtra("Long",0.0);
        getLocation(lat,longi);


    }

    private double meterDistanceBetweenPoints(double lat_a, double lng_a, double lat_b, double lng_b) {
        double pk = (double) (180.f/Math.PI);

        double a1 = lat_a / pk;
        double a2 = lng_a / pk;
        double b1 = lat_b / pk;
        double b2 = lng_b / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 6366000 * tt;
    }

    private void getLocation(double lat,double longi) {
        DecimalFormat df2 = new DecimalFormat("#.##");

        if (ActivityCompat.checkSelfPermission(
                DetailActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                DetailActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double latLng = locationGPS.getLatitude();
                double longLng = locationGPS.getLongitude();

                //showLocation.setText("Your Location: " + "\n" + "Latitude: " + latLng + "\n" + "Longitude: " + longLng);
                double distance = Double.parseDouble(df2.format(meterDistanceBetweenPoints(lat,longi,latLng,longLng)/1000));

                String str1  = Double.toString(distance) + " km";
                txt_Distance.setText(str1);

            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

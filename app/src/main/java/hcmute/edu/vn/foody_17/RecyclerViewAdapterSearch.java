package hcmute.edu.vn.foody_17;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterSearch extends RecyclerView.Adapter<RecyclerViewAdapterSearch.MyViewHolder> implements Filterable {
    private Context mContext;
    private List<Food> mData;
    private List<Food> mDataFilter;

    public RecyclerViewAdapterSearch(Context mContext, List<Food> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFilter = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_search,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Bitmap img = null;
        AssetManagers assetManagers = new AssetManagers();
        try {
            img = assetManagers.getBitmap(mData.get(position).getThumbnail(),mContext);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SharedPreferences sharedPref = mContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Float LatLng = sharedPref.getFloat("latGPS", (float) 10.970110);
        Float LongLng = sharedPref.getFloat("longGPS", (float) 106.894530);

        Location locationA = new Location("point A");
        double lat = mDataFilter.get(position).getLatiTude();
        float Lat = (float)lat;
        double longi = mDataFilter.get(position).getLongiTude();
        float Long = (float)longi;

        locationA.setLatitude(Lat);
        locationA.setLongitude(Long);

        Location locationB = new Location("point B");

        locationB.setLatitude(LatLng);
        locationB.setLongitude(LongLng);
        DecimalFormat df2 = new DecimalFormat("#.##");
        String distance = df2.format(locationA.distanceTo(locationB)/1000);
        String str  = Float.toString(Float.parseFloat(distance)) + " km";

        holder.food_title.setText(mDataFilter.get(position).getTitle());
        holder.address.setText(mDataFilter.get(position).getAddress());
        holder.type_Store.setText(mDataFilter.get(position).getTypeStore());
        holder.txt_Dis.setText(str);
        holder.food_thumb.setImageBitmap(img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra("IdFood",mDataFilter.get(position).getIdFood());
                intent.putExtra("Title",mDataFilter.get(position).getTitle());
                intent.putExtra("Address",mDataFilter.get(position).getAddress());
                intent.putExtra("Province",mDataFilter.get(position).getProvince());
                //intent.putExtra("Thumb",mDataFilter.get(position).getThumbnail());
                intent.putExtra("Price",mDataFilter.get(position).getPrice());
                intent.putExtra("Lat",mDataFilter.get(position).getLatiTude());
                intent.putExtra("Long",mDataFilter.get(position).getLongiTude());
                intent.putExtra("Account",mDataFilter.get(position).getAccount());
                intent.putExtra("Pass",mDataFilter.get(position).getPass());
                intent.putExtra("Sdt",mDataFilter.get(position).getSdt());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //return mData.size();
        return mDataFilter.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                String Key = constraint.toString();
//                List<Food> lstFiltered = new ArrayList<>();
//
//                if(Key.isEmpty()){
//                    mDataFilter = (mData);
//                }
//                else{
//
//                    if(mDataFilter != null){
//                        List<Food> lst = new ArrayList<>();
//                        mDataFilter = lst;
//                    }
//
//                    String key = constraint.toString().toLowerCase().trim();
//
//
//                    for(Food row : mData){
//
//                        if(row.getTitle().toLowerCase().contains(key)){
//
//                                lstFiltered.add(row);
//                        }
//
//                    }
//
//                    mDataFilter.addAll(lstFiltered);
//
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = mDataFilter;
//                return  filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//
//                mDataFilter=((List<Food>) results.values);
//                notifyDataSetChanged();
//            }
//        };

        return new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence constraint){
                List<Food> lstFiltered = new ArrayList<>();

                if (constraint == null || constraint.length() == 0){

                    lstFiltered.addAll(mData);
                }else {
                    String key = constraint.toString().toLowerCase().trim();

                    for(Food row : mData){
                        if(row.getTitle().toLowerCase().contains(key)){
                            lstFiltered.add(row);
                        }
                    }

                }
                FilterResults results = new FilterResults();
                results.values = lstFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results){
                mDataFilter.clear();
                mDataFilter.addAll((List<Food>) results.values);
                notifyDataSetChanged();
            }
        };
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView food_title, address, type_Store,txt_Dis;
        ImageView food_thumb;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            food_title = (TextView) itemView.findViewById(R.id.txt_Name);
            address = (TextView) itemView.findViewById(R.id.txt_address);
            type_Store = (TextView) itemView.findViewById(R.id.txt_typeStore);
            txt_Dis = (TextView) itemView.findViewById(R.id.txt_Dis);
            food_thumb = (ImageView) itemView.findViewById(R.id.imgv_food);
            cardView = (CardView) itemView.findViewById(R.id.cardview_idss);

        }
    }
}
